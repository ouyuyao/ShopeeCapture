package com.example.shopeecapture.config;

import com.alibaba.fastjson.annotation.JSONType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//自定义接口返回信息样式
@JSONType(orders={"status","statusCode","responseMsg"})
public class ResponseInfo {
    private int status;
    private String statusCode;
    private Object responseMsg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Object getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(List responseMsg) {
        if (responseMsg.size()>0){
            if(null==responseMsg.get(0)){
                this.status = Constants.NOT_RECORDS;
                this.statusCode = String.valueOf(Constants.STATUS_SUCCESS);
                Map errorMsg = new HashMap();
                errorMsg.put("errorMsg",Constants.NOT_RECORDS_CONTEXT);
                this.responseMsg = errorMsg;
            }else{
                this.responseMsg = responseMsg;
            }
        }else{
            this.responseMsg = responseMsg;
        }
    }

}
