package com.pishi.config;

import org.springframework.core.convert.converter.Converter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author pishi
 * @description: 时间字符串转 LocalDateTime
 * @date 2023年08月17日 09:46
 */
public class LongToLocalDateTime implements Converter<Long, LocalDateTime> {

    @Override
    public LocalDateTime convert(Long str) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(str), ZoneId.systemDefault());
    }
}
