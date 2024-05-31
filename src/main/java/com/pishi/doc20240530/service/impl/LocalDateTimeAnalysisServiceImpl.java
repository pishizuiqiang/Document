package com.pishi.doc20240530.service.impl;

import com.pishi.doc20240530.constant.AnalysisEnum;
import com.pishi.doc20240530.service.AnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LocalDateTimeAnalysisServiceImpl implements AnalysisService<LocalDateTime> {

    private final ConversionService conversionService;

    @Override
    public void analysis(LocalDateTime value) {

    }

    @Override
    public LocalDateTime analysis(String value) {


        final LocalDateTime convert = conversionService.convert(value, LocalDateTime.class);

        return convert;
    }

    @Override
    public AnalysisEnum support() {
        return AnalysisEnum.DATE_TIME;
    }
}
