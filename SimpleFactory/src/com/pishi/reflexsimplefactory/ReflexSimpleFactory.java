package com.pishi.reflexsimplefactory;

import com.pishi.vo.JGMessageVo;
import com.pishi.vo.JYMessageVo;
import com.pishi.vo.MessageVo;

import java.lang.reflect.InvocationTargetException;
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
