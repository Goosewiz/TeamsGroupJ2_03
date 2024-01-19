package main.gusev.java24;


public class InfoModel {
    private String[] columns;
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(columns[0]);
        return sb.toString();
    }

}
