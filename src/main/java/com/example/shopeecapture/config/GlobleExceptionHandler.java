package com.example.shopeecapture.config;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobleExceptionHandler {

    @ExceptionHandler(value =Exception.class)
    public ResponseInfo exceptionHandler(Exception e) throws Exception {

        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setStatus(Constants.OPT_FAILED);
        responseInfo.setStatusCode(Constants.SYSTEM_ERROR);
        List listResponseMsg = new ArrayList();
        listResponseMsg.add(e.getMessage());
        e.printStackTrace();
        responseInfo.setResponseMsg(listResponseMsg);

        return responseInfo;
    }

}