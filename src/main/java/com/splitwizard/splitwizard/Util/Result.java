package com.splitwizard.splitwizard.Util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter @Setter
public class Result {

    private Integer code;
    private String message;
    private Object result;

    private Result setResult(int code, String message, Object data){

        this.code = code;
        this.message = message;
        this.result = data;

        return this;
    }

    public Result success(Object data){
        return setResult(200, "Success", data);
    }

    public Result fail(String message){
        return setResult(400, message, null);
    }

}
