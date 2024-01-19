package main.gusev.java24;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InfoWrapper {
    @JsonProperty("columns")
    InfoModel info;
    public InfoModel getInfo() {
        return info;
    }
    public void setInfo(InfoModel info) {
        this.info = info;
    }
}
