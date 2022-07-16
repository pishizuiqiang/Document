package com.pishi.simplefactory;

import com.pishi.vo.JYMessageVo;

/**
 * @author pishi
 * @description: todo
 * @date 2022年07月16日 16:25
 */
public class Test {

    public static void main(String[] args) {
        JYMessageVo jy = (JYMessageVo)SimpleFactory.createMessage("JY");
        jy.setJudgeResult(true);
        System.out.println(jy);

    }

}
