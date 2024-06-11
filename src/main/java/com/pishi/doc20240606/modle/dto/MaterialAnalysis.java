package com.pishi.doc20240606.modle.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Accessors(chain = true)
public class MaterialAnalysis {
    private Analysis analysis;
    private String materialCode;
    private String materialName;
    private Double netWeight;
    private LocalDateTime sampletime;
    private Map<String, String> values;

}
