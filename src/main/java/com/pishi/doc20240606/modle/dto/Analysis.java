package com.pishi.doc20240606.modle.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class Analysis {
    private String anaId;
    private String back1;
    private String back2;
    private String back3;
    private String batchCode;
    private String brandCode;
    private String clock;
    private boolean deleted;
    private String grade;
    private Integer integral;
    private String ironLadleCode;
    private Boolean isCorrect;
    private String matPileNo;
    private String mtSampleCode;
    private String note;
    private String prodUnitCode;
    private String productionLineCode;
    private String productionLineName;
    private String sampleId;
    private LocalDateTime sampleTime;
    private String samplingLocationCode;
    private String samplingLocationName;
    private LocalDateTime sendTime;
    private String subProductionLineCode;
    private String subProductionLineName;
    private String supplierCode;
    private String supplierName;
    private String workShift;
    private String workTeam;

    // Getters and setters
    // ...
}
