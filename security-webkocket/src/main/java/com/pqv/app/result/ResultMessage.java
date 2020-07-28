package com.pqv.app.result;

import lombok.Data;

@Data
public class ResultMessage<T> {
    private T data;
    private String message;
    private int status;
}
