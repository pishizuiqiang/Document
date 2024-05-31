package com.pishi.doc20240530.constant;

/**
 * 解析类型枚举
 */
public enum AnalysisEnum {

    DATE_TIME("dateTime", "日期时间"),

    MONEY("money", "金额"),
    APP("app", "app"),
    TYPE("type", "类型"),
    WAY("way", "支付渠道"),
    TAG("tag", "标签", false),

    NUMBER("number", "数字"),

    STRING("string", "字符串"),

    ;

    private String type;

    private String desc;


    /**
     * 是否单选
     */
    private boolean singleChoice;


    AnalysisEnum(String type, String desc, boolean singleChoice) {
        this.type = type;
        this.desc = desc;
        this.singleChoice = singleChoice;
    }

    AnalysisEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
        this.singleChoice = true;
    }


    public boolean isSingleChoice() {
        return singleChoice;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
