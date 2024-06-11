package com.pishi.config;

import com.pishi.utils.DateTimeUtils;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;

/**
 * @author pishi
 * @description: 时间字符串转 LocalDateTime
 * @date 2023年08月17日 09:46
 */
public class StringToLocalDate implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(String str) {
        return DateTimeUtils.parseDate(str);
    }


}
