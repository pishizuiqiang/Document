package com.pishi.doc20240606.modle.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author pishi
 * @description: 响应
 * @date 2024年06月06日 08:15
 */
@Data
@Accessors(chain = true)
public class Resp<T> {
    private int code;
    private T data;
    private String message;
    private String msg;

}


