package com.pishi.reflexsimplefactory;

import com.pishi.simplefactory.SimpleFactory;
import com.pishi.vo.JYMessageVo;

/**
 * @author pishi
 * @description: todo
 * @date 2022年07月16日 16:25
 */
public class Test {

    public static void main(String[] args) {
        //每次都需要自行强转回需要的类型
        JYMessageVo jy = (JYMessageVo) ReflexSimpleFactory.createMessage(JYMessageVo.class);
        jy.setJudgeResult(true);
        System.out.println(jy);
    }

}
