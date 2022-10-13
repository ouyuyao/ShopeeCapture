package com.example.shopeecapture.Bean;

import java.io.Serializable;

public class Searchkey implements Serializable {
    private static final long serialVersionUID = -86335334022967577L;
    private Integer id;
    private String keyname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyname() {
        return keyname;
    }

    public void setKeyname(String keyname) {
        this.keyname = keyname;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append('{');
        sb.append("\"id\":").append(id);
        sb.append(",\"keyname\":\"").append(keyname).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
