package com.pishi.doc20240606.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class AnalysisExcelExportServiceTest {


    @Autowired
    AnalysisExcelExportService analysisExcelExportService;


    @Test
    void export() {
        analysisExcelExportService.export();
    }
}