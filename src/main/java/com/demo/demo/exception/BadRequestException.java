package com.demo.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends  RuntimeException{
    private String CustomMsg;

    public BadRequestException(String customMsg)
    {
        super(customMsg);
        this.CustomMsg = customMsg;
    }

    public String getCustomMsg() {
        return CustomMsg;
    }

    public void setCustomMsg(String customMsg) {
        CustomMsg = customMsg;
    }
}
