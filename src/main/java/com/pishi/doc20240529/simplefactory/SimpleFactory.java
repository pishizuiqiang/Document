package com.pishi.doc20240529.simplefactory;

import com.pishi.doc20240529.vo.JGMessageVo;
import com.pishi.doc20240529.vo.JYMessageVo;
import com.pishi.doc20240529.vo.MessageVo;

import java.util.Date;
import java.util.UUID;

/**
 * @author pishi
 * @description: 简单工厂
 * @date 2022年07月16日 12:25
 */
public class SimpleFactory {

    public static MessageVo createMessage(String type){
        MessageVo vo = null ;
        if ("JY".equals(type))
        {
            vo = new JYMessageVo();
        }else if ("JG".equals(type))
        {
            vo = new JGMessageVo();
        }


        //region 设置公用字段的值
        if (vo != null)
        {
            vo.setUniqueKey(UUID.randomUUID().toString());
            vo.setSendTime(new Date());
            vo.setOperateFlag("N");
        }
        //endregion
        return vo;
    }

}
