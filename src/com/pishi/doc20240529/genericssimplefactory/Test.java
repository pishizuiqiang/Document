package com.pishi.doc20240529.genericssimplefactory;

import com.pishi.doc20240529.vo.JYMessageVo;

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
