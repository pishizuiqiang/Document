package com.pishi.doc20240530.service;

import com.pishi.doc20240530.constant.AnalysisEnum;
import com.pishi.utils.ClassUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class AnalysisServiceTest {

    @Autowired
    private List<AnalysisService> analysisServiceList;

    @Autowired
    private ConversionService conversionService;

    @Test
    void analysis() {

        Map<String, AnalysisEnum> parameter = new HashMap<>();


        parameter.put("2024-05-30 15:04:36", AnalysisEnum.DATE_TIME);
        parameter.put("12.222", AnalysisEnum.MONEY);
        parameter.put("xxxx", AnalysisEnum.STRING);


        parameter.forEach((k, v) -> {
            analysisServiceList.forEach(service -> {
                if (service.support().equals(v)) {

                    // 方案1, 通过实现类手动转换
                    implConvert(k, service);
                    // 方案2, 通过工具类反射转换
                    utilConvert(k, service);
                }

            });

        });
    }

    /**
     * 通过反射转换
     *
     * @param k
     * @param service
     */
    private void utilConvert(String k, AnalysisService service) {
        //获取接口类型实例对象
        final ParameterizedType analysisService = ClassUtils.getParameterizedTypeByClassName(service.getClass(), "AnalysisService");

        //获取泛型类型实例对象
        final Type actualTypeArgument = analysisService.getActualTypeArguments()[0];

        //将字符串转换成泛型类型实例对象
        final Object analysis = conversionService.convert(k, (Class<? extends Object>) actualTypeArgument);

        log.info("analysis = {},\t\ttype={}", analysis, analysis.getClass());
    }

    /**
     * 通过实现类手动转换
     *
     * @param k
     * @param service
     */
    private void implConvert(String k, AnalysisService service) {
        final Object analysis = service.analysis(k);

        log.info("analysis = {},\t\ttype={}", analysis, analysis.getClass());
    }
}