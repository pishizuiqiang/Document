package com.pishi.config;

import com.pishi.utils.DateTimeUtils;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;

/**
 * @author pishi
 * @description: 时间字符串转 LocalDateTime
 * @date 2023年08月17日 09:46
 */
public class StringToLocalDateTime implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String str) {
        return DateTimeUtils.parse(str);
    }
}
