package com.pishi.doc20240529.vo;

/**
 * @author pishi
 * @description: 价格类型
 * @date 2022年07月16日 16:15
 */
public class JGMessageVo extends MessageVo{

    /**
     * 检验结果
     */
    private Double price;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "JGMessageVo{" +
                "uniqueKey='" + this.getUniqueKey() + '\'' +
                ", sendTime=" + this.getSendTime() +
                ", operateFlag='" + this.getOperateFlag() + '\'' +
                ", price=" + this.getPrice() +
                '}';
    }
}
