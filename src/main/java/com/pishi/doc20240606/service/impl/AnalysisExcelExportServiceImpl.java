package com.pishi.doc20240606.service.impl;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import com.pishi.doc20240606.api.MaterialApi;
import com.pishi.doc20240606.modle.dto.Analysis;
import com.pishi.doc20240606.modle.dto.AnalysisItem;
import com.pishi.doc20240606.modle.dto.MaterialAnalysis;
import com.pishi.doc20240606.modle.dto.MaterialDto;
import com.pishi.doc20240606.service.AnalysisExcelExportService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class AnalysisExcelExportServiceImpl implements AnalysisExcelExportService {


    private final MaterialApi materialApi;

    @Override
    public void export() {

        final List<MaterialDto> materialTree = materialApi.getMaterialTree();


        materialTree.forEach(materialDto -> {
            final String name = materialDto.getName();
            if (materialDto.getChildList() == null || materialDto.getChildList().isEmpty()) {
                return;
            }

            materialDto.getChildList().forEach(child -> {
                final String typeName = child.getName();

                final String format = MessageFormat.format("检化验/{0}/{1}", name, typeName);

                File file = new File(format);
                file.mkdirs();

                if (child.getChildList() == null || child.getChildList().isEmpty()) {
                    return;
                }
                child.getChildList().forEach(child1 -> {

                    final String code = child1.getCode();
                    importMaterial(name, typeName, code, child1.getName());


                });
            });
        });


    }

    private void importMaterial(String name, String typeName, String materialCode, String materialName) {
        final List<AnalysisItem> analysisItems = materialApi.getByCode(materialCode);


        final List<MaterialAnalysis> materialAnalyses = materialApi.analysisValueByCode(materialCode);

        if (CollectionUtils.isEmpty(materialAnalyses)) {
            return;
        }


        // 写入Excel

        final String format = MessageFormat.format("检化验/{0}/{1}/{2}-{3}.xlsx", name, typeName, materialCode, StringUtils.replace(materialName, "/", ""));

        File file = new File(format);
        ExcelWriter writer = EasyExcelFactory
                .write()
                .file(file)
                .build();


        importAnalysis(analysisItems, materialAnalyses, writer);


        importAnalysisAvg(analysisItems, materialAnalyses, writer);

        // 关闭writer
        writer.finish();
    }

    private static void importAnalysisAvg(List<AnalysisItem> analysisItems, List<MaterialAnalysis> materialAnalyses, ExcelWriter writer) {
        List<List<String>> heads = new ArrayList<>();
        heads.add(Collections.singletonList("日期"));

        analysisItems.stream()
                .map(AnalysisItem::getAnaItemName)
                .map(Collections::singletonList)
                .forEach(heads::add)
        ;

        final Map<LocalDate, Map<String, Double>> collect = materialAnalyses
                .stream()
                .collect(Collectors.groupingBy(i -> i.getSampletime().toLocalDate(), Collectors.collectingAndThen(Collectors.toList(), list -> {
                    final List<Map<String, String>> analysisVal = list
                            .stream()
                            .map(MaterialAnalysis::getValues)
                            .filter(MapUtils::isNotEmpty)
                            .collect(Collectors.toList());


                    return analysisItems.stream()
                            .map(AnalysisItem::getAnaItemCode)
                            .collect(Collectors.toMap(Function.identity(),
                                    (itemCode -> {
                                        final OptionalDouble average = analysisVal.stream()
                                                .map(item -> item.get(itemCode))
                                                .filter(NumberUtils::isCreatable)
                                                .mapToDouble(NumberUtils::createDouble)
                                                .average();

                                        return average.orElse(0d);
                                    })
                            ));

                })));

        final List<List<Object>> data = collect.entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .map(entry -> {
                    final List<Object> row = new ArrayList<>();
                    row.add(LocalDateTimeUtil.format(entry.getKey(), "yyyy-MM-dd"));

                    final Map<String, Double> value = entry.getValue();

                    analysisItems.forEach(item -> {
                        final String anaItemCode = item.getAnaItemCode();

                        final Double v = value.get(anaItemCode);
                        row.add(Optional.ofNullable(v)
                                .filter(i -> !i.equals(0d))
                                .map(i -> String.format("%.4f", i))
                                .orElse(""));
                    });
                    return row;
                }).collect(Collectors.toList());

        extracted(heads, "检化验平均值", 1, writer, data);
    }

    private static void importAnalysis(List<AnalysisItem> analysisItems, List<MaterialAnalysis> materialAnalyses, ExcelWriter writer) {
        //region 表头
        List<List<String>> heads = new ArrayList<>();
        heads.add(Collections.singletonList("检化验号"));
        heads.add(Collections.singletonList("取样时间"));
        heads.add(Collections.singletonList("取样地点"));


        analysisItems.stream()
                .map(AnalysisItem::getAnaItemName)
                .map(Collections::singletonList)
                .forEach(heads::add)
        ;
        //endregion


        final List<List<Object>> data = materialAnalyses
                .stream()
                .sorted(Comparator.comparing(MaterialAnalysis::getSampletime))
                .map(materialAnalys -> {
                    final List<Object> row = new ArrayList<>();
                    final Analysis analysis = materialAnalys.getAnalysis();
                    row.add(analysis.getSampleId());
                    row.add(analysis.getSampleTime());
                    row.add(analysis.getSamplingLocationName());
                    final Map<String, String> values = materialAnalys.getValues();

                    analysisItems.forEach(i -> {
                        final String anaItemCode = i.getAnaItemCode();
                        row.add(values.get(anaItemCode));

                    });


                    return row;
                }).collect(Collectors.toList());


        extracted(heads, "检化验", 0, writer, data);
    }

    private static void extracted(List<List<String>> heads,
                                  String sheetName,
                                  int sheetNo,
                                  ExcelWriter writer,
                                  List<List<Object>> data) {
        final WriteTable writeTable = new WriteTable();
        writeTable.setTableNo(0);
        writeTable.setHead(heads);
        final WriteSheet writeSheet = new WriteSheet();
        writeSheet.setSheetName(sheetName);
        writeSheet.setSheetNo(sheetNo);
        writer.write(data, writeSheet, writeTable);
    }
}
