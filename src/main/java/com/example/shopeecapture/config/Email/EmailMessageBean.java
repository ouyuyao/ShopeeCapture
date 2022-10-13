package com.example.shopeecapture.config.Email;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EmailMessageBean {
    private String timeStamp;
    private String className;
    private String threadName;
    private String message;

    public EmailMessageBean(String className,String threadName,String message){
        this.timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
        this.className = className;
        this.threadName = threadName;
        this.message = message;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append('{');
        sb.append("\"timeStamp\":\"").append(timeStamp).append('\"');
        sb.append(",\"className\":\"").append(className).append('\"');
        sb.append(",\"threadName\":\"").append(threadName).append('\"');
        sb.append(",\"message\":\"").append(message).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
