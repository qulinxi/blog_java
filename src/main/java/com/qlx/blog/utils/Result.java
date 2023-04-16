package com.qlx.blog.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
    private String msg;
    private Object data;
    private Integer code;
}
