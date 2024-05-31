package com.pishi.doc20240530.service.impl;

import com.pishi.doc20240530.constant.AnalysisEnum;
import com.pishi.doc20240530.service.AnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DoubleAnalysisServiceImpl implements AnalysisService<Double> {

    private final ConversionService conversionService;

    @Override
    public void analysis(Double value) {

    }


    @Override
    public Double analysis(String value) {

        final Double convert = conversionService.convert(value, Double.class);


        return convert;
    }

    @Override
    public AnalysisEnum support() {
        return AnalysisEnum.MONEY;
    }
}
