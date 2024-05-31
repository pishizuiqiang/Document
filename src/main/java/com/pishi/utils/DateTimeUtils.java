package com.pishi.utils;

import cn.hutool.core.date.LocalDateTimeUtil;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

/**
 * @author pishi
 * @description: 时间工具类
 * @date 2024年02月29日 10:55
 */
public class DateTimeUtils {


    public static LocalDateTime parse(String str) {

        final Date date;
        try {
            date = DateUtils.parseDate(str,
                    "yyyy-MM-dd HH:mm:ss",
                    "yyyy年MM月dd日HH:mm:ss",
                    "yyyy年M月d日HH:mm:ss",
                    "yyyy-MM-dd'T'HH:mm:ssZZ",
                    "yyyy年M月dd日 HH:mm:ss"
            );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        return Optional.ofNullable(date)
                .map(LocalDateTimeUtil::of)
                .orElse(null);
    }


}
