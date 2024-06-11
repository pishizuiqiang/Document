package com.pishi.doc20240606.modle.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class MaterialDto {
    private String business;
    private List<MaterialDto> childList;
    private String code;
    private String codeOs;
    private boolean deleted;
    private String function;
    private String id;
    private boolean latestTest;
    private int layer;
    private String name;
    private String nameOs;
    private int orderNo;
    private String parentCode;
    private String parentName;
    private String procedure;
    private boolean testStatus;
    private boolean used;
}
