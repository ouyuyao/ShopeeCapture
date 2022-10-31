package com.example.shopeecapture.Bean;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class Processlocks implements Serializable {
    private static final long serialVersionUID = -32948437373971380L;
    private Integer id;
    /**
     * 锁状态
     */
    private String status;
    /**
     * 当前正在处理的逻辑
     */
    private String processlogic;
    /**
     * 最新更新时间
     * 最小最新更新时间
     * 最大最新更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastupdatetimestamp;
    @JsonIgnore
    private Date min_lastupdatetimestamp;
    @JsonIgnore
    private Date max_lastupdatetimestamp;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append('{');
        sb.append("\"id\":").append(id);
        sb.append(",\"status\":\"").append(status).append('\"');
        sb.append(",\"processlogic\":\"").append(processlogic).append('\"');
        sb.append("\"lastupdatetimestamp\":").append(lastupdatetimestamp);
        sb.append("\"min_lastupdatetimestamp\":").append(min_lastupdatetimestamp);
        sb.append("\"max_lastupdatetimestamp\":").append(max_lastupdatetimestamp);
        sb.append('}');
        return sb.toString();
    }
}
