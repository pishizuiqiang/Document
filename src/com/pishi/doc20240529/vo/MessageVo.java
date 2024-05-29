package com.pishi.doc20240529.vo;

import java.util.Date;

/**
 * @author pishi
 * @description: 消息父类
 * @date 2022年07月16日 12:26
 */
public class MessageVo {

    /**
     * 业务唯一主键
     */
    private String uniqueKey;

    /**
     * 发送时间
     */
    private Date sendTime;


    /**
     * 操作标识
     */
    private String operateFlag;


    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getOperateFlag() {
        return operateFlag;
    }

    public void setOperateFlag(String operateFlag) {
        this.operateFlag = operateFlag;
    }


}
