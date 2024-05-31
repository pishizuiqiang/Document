package com.pishi.doc20240530.service;

import com.pishi.doc20240530.constant.AnalysisEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class AnalysisServiceTest {

    @Autowired
    private List<AnalysisService> analysisServiceList;


    @Test
    void analysis() {

        Map<String, AnalysisEnum> parameter = new HashMap<>();


        parameter.put("2024-05-30 15:04:36", AnalysisEnum.DATE_TIME);
        parameter.put("12.222", AnalysisEnum.MONEY);
        parameter.put("xxxx", AnalysisEnum.STRING);


        parameter.forEach((k, v) -> {
            analysisServiceList.forEach(service -> {
                if (service.support().equals(v)) {
                    final Object analysis = service.analysis(k);
                    log.info("analysis = {},\t\ttype={}",analysis,analysis.getClass());
                }

            });

        });
    }
}