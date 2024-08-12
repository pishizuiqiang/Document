package com.pishi.config;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * @author pishi
 * @description: 时间字符串转 LocalDateTime
 * @date 2023年08月17日 09:46
 */
public class StringToLong implements Converter<String, Long> {

    @Override
    public Long convert(String source) {
        return NumberUtils.createLong(source);
    }
}
