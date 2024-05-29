package com.pishi.doc20240529.genericssimplefactory;

import com.pishi.doc20240529.vo.MessageVo;

import java.util.Date;
import java.util.UUID;

/**
 * @author pishi
 * @description: 利用泛型优化简单工厂
 * @date 2022年07月16日 16:35
 */
public class GenericsSimpleFactory {

    //利用范型 让调用时不必强转，且限定了 MessageVo才能使用此工厂
    public static<T extends MessageVo> T  createMessage(Class<T> clazz){
        T vo = null;
        try {
            vo = (T)Class.forName(clazz.getName()).getDeclaredConstructor().newInstance();
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
