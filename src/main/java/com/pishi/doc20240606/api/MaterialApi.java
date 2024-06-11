package com.pishi.doc20240606.api;

import com.pishi.doc20240606.modle.dto.AnalysisItem;
import com.pishi.doc20240606.modle.dto.MaterialAnalysis;
import com.pishi.doc20240606.modle.dto.MaterialDto;

import java.util.List;

public interface MaterialApi {

    List<MaterialDto> getMaterialTree();


    List<AnalysisItem> getByCode(String materialCode);


    List<MaterialAnalysis> analysisValueByCodePage(String materialCode);

    List<MaterialAnalysis> analysisValueByCode(String materialCode);
}
