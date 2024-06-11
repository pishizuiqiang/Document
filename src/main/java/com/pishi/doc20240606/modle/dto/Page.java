package com.pishi.doc20240606.modle.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author pishi
 * @description: todo
 * @date 2024年06月06日 14:46
 */
@Data
@Accessors(chain = true)
public class Page<T> {


    private List<T> records;
    private String rows;
    private String total;

}
