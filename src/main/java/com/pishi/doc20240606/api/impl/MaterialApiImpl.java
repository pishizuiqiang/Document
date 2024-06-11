package com.pishi.doc20240606.api.impl;

import cn.hutool.core.lang.TypeReference;
import com.pishi.doc20240606.api.MaterialApi;
import com.pishi.doc20240606.modle.dto.AnalysisItem;
import com.pishi.doc20240606.modle.dto.MaterialAnalysis;
import com.pishi.doc20240606.modle.dto.MaterialDto;
import com.pishi.doc20240606.modle.dto.Page;
import com.pishi.utils.RestTemplateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author pishi
 * @description: todo
 * @date 2024年06月06日 08:29
 */
@Service
@RequiredArgsConstructor
public class MaterialApiImpl implements MaterialApi {


    private final RestTemplate restTemplate;


    @Override
    public List<MaterialDto> getMaterialTree() {
        return RestTemplateUtils.post(restTemplate, "material/getMaterialTree", new TypeReference<List<MaterialDto>>() {
        });
    }

    @Override
    public List<AnalysisItem> getByCode(String materialCode) {

        final String url = MessageFormat.format("/analysisItemInfo/getByCode?{0}={1}", "materialCode", materialCode);

        List<AnalysisItem> analysisItems = RestTemplateUtils.get(restTemplate, url, new TypeReference<List<AnalysisItem>>() {
        });


        return analysisItems;
    }

    @Override
    public List<MaterialAnalysis> analysisValueByCodePage(String materialCode) {


        // Create a HashMap to store the request and publication information
        Map<String, Object> dataMap = new HashMap<>();

        // Nested maps for 'reqInfo' and 'pubInfo'
        Map<String, Object> reqInfoMap = new HashMap<>();
        Map<String, Object> pubInfoMap = new HashMap<>();

        // Populate 'reqInfo' map
        reqInfoMap.put("startTime", 1711900800000L);
        reqInfoMap.put("endTime", 1717171200000L);
        reqInfoMap.put("codes", new String[]{materialCode});

        // Populate 'pubInfo' map
        pubInfoMap.put("pageNum", 1);
        pubInfoMap.put("pageSize", 999999);

        // Add nested maps to the main map
        dataMap.put("reqInfo", reqInfoMap);
        dataMap.put("pubInfo", pubInfoMap);

        // Print or use the generated map as needed
        System.out.println(dataMap);

        final Page<MaterialAnalysis> post = RestTemplateUtils.post(restTemplate, "analysis/analysisValueByCodePage", dataMap, new TypeReference<Page<MaterialAnalysis>>() {
        });

        return Optional.ofNullable(post)
                .map(Page::getRecords)
                .orElseThrow(() -> new RuntimeException(""));


    }

    @Override
    public List<MaterialAnalysis> analysisValueByCode(String materialCode) {



        Map<String, Object> reqInfoMap = new HashMap<>();

        // Populate 'reqInfo' map
        reqInfoMap.put("startTime", 1711900800000L);
        reqInfoMap.put("endTime", 1717171200000L);
        reqInfoMap.put("codes", new String[]{materialCode});


        final Map<String, List<MaterialAnalysis>> post = RestTemplateUtils.post(restTemplate, "analysis/analysisValueByCode", reqInfoMap, new TypeReference<Map<String, List<MaterialAnalysis>>>() {
        });

        return Optional.ofNullable(post)
                .map(i->i.entrySet().stream().map(Map.Entry::getValue).flatMap(List::stream).collect(Collectors.toList()))
                .orElseThrow(() -> new RuntimeException(""));

    }
}
