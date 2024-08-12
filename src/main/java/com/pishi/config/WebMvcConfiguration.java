package com.pishi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author pishi
 * @description: todo
 * @date 2023年08月17日 10:33
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {

        registry.addConverter(new StringToLong());
        registry.addConverter(new StringToLocalDateTime());
        registry.addConverter(new StringToLocalDate());
        registry.addConverter(new LocalDateToString());
        registry.addConverter(new LongToLocalDateTime());
    }




}
