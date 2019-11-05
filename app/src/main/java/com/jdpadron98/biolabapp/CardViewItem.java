package com.jdpadron98.biolabapp;

public class CardViewItem {

    private String cell_line_tv, gL50_tv;

    public CardViewItem(String cell_line, String gL50) {
        cell_line_tv = cell_line;
        gL50_tv = gL50;
    }

    public String getCell_line_tv() {
        return cell_line_tv;
    }

    public String getgL50_tv() {
        return gL50_tv;
    }
}
