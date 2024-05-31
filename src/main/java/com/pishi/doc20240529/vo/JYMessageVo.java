package com.pishi.doc20240529.vo;

/**
 * @author pishi
 * @description: 检验结果类型
 * @date 2022年07月16日 16:15
 */
public class JYMessageVo extends MessageVo{

    /**
     * 检验结果
     */
    private Boolean judgeResult;

    public Boolean getJudgeResult() {
        return judgeResult;
    }

    public void setJudgeResult(Boolean judgeResult) {
        this.judgeResult = judgeResult;
    }


    @Override
    public String toString() {
        return "JGMessageVo{" +
                "uniqueKey='" + this.getUniqueKey() + '\'' +
                ", sendTime=" + this.getSendTime() +
                ", operateFlag='" + this.getOperateFlag() + '\'' +
                ", judgeResult=" + this.getJudgeResult() +
                '}';
    }
}
