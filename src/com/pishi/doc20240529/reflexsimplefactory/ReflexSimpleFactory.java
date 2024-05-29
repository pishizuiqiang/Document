package com.pishi.doc20240529.reflexsimplefactory;

import com.pishi.doc20240529.vo.MessageVo;

import java.util.Date;
import java.util.UUID;

/**
 * @author pishi
 * @description: 利用反射优化简单工厂
 * @date 2022年07月16日 16:35
 */
public class ReflexSimpleFactory {

    public static MessageVo createMessage(Class clazz){
        MessageVo vo = null;
        try {
            vo = (MessageVo)Class.forName(clazz.getName()).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
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
