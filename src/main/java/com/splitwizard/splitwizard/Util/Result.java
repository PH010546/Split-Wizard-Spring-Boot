package com.splitwizard.splitwizard.Util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter @Setter
public class Result {

    private String status;
    private String message;
    private Object result;

    private Result setResult(String status, String message, Object data){

        this.status = status;
        this.message = message;
        this.result = data;

        return this;
    }

    public Result success(Object data){
        return setResult("success", null, data);
    }

    public Result fail(String message){
        return setResult("error", message, null);
    }

}
