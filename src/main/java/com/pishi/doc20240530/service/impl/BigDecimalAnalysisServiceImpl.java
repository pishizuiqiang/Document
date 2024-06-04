package com.pishi.doc20240530.service.impl;

import com.pishi.doc20240530.constant.AnalysisEnum;
import com.pishi.doc20240530.service.AnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class BigDecimalAnalysisServiceImpl implements AnalysisService<BigDecimal> {

    private final ConversionService conversionService;


    @Override
    public void analysis(BigDecimal value) {

    }

    @Override
    public BigDecimal analysis(String value) {

        final BigDecimal convert = conversionService.convert(value, BigDecimal.class);

        return convert;

    }

    @Override
    public AnalysisEnum support() {
        return AnalysisEnum.MONEY;
    }
}
