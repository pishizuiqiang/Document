package com.pishi.doc20240530.service;


import com.pishi.doc20240530.constant.AnalysisEnum;

public interface AnalysisService<T> {

    void analysis(T value);


    T analysis(String value);


    AnalysisEnum support();

}
