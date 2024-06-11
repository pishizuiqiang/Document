package com.pishi.config;

import cn.hutool.core.date.LocalDateTimeUtil;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;

/**
 * @author pishi
 * @description: 时间字符串转 LocalDateTime
 * @date 2023年08月17日 09:46
 */
public class LocalDateToString implements Converter<LocalDate, String> {


    @Override
    public String convert(LocalDate source) {
        return LocalDateTimeUtil.format(source, "yyyy-MM-dd");
    }
}
