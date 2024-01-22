package main.gusev.java24;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Securities {
    @JsonProperty("columns")
    private String[] columns;
    @JsonProperty("data")
    private Information[] data;

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public Information[] getData() {
        return data;
    }

    public void setData(Information[] data) {
        this.data = data;
    }
   // public String[] getAnswer(){
    //    return data.getInfData();
    //}
}
