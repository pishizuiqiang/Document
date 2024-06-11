package com.pishi.doc20240606.modle.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AnalysisItem {

    private String anaItemCode;
    private String anaItemName;
    private String apiCode;
    private Object defaultValue; // Consider using specific data type if known
    private String id;
    private Object lowerLimit; // Consider using specific data type if known
    private String materialCode;
    private int orderNum;
    private Object permitMiss; // Consider using specific data type if known
    private String tagName;
    private String tagNameDay;
    private String tagObject;
    private String tagObjectPassRate;
    private Object upperLimit; // Consider using specific data type if known

}
