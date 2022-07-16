package com.pishi.genericssimplefactory;

import com.pishi.reflexsimplefactory.ReflexSimpleFactory;
import com.pishi.vo.JYMessageVo;

/**
 * @author pishi
 * @description: todo
 * @date 2022年07月16日 16:25
 */
public class Test {

    public static void main(String[] args) {
        JYMessageVo jy = GenericsSimpleFactory.createMessage(JYMessageVo.class);
        jy.setJudgeResult(true);
        System.out.println(jy);

    }

}
