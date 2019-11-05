package com.jdpadron98.biolabapp;

import java.util.ArrayList;

public class Table2Data {

    private ArrayList<String> cell_line_list;
    private ArrayList<String> gL50_list;
    private ArrayList<String> id_exp_list;

    public Table2Data(ArrayList<String> cell_line_list, ArrayList<String> gL50_list, ArrayList<String> id_exp_list) {
        this.cell_line_list = cell_line_list;
        this.gL50_list = gL50_list;
        this.id_exp_list = id_exp_list;

    }

    public ArrayList<String> getCell_line_list() {
        return cell_line_list;
    }

    public ArrayList<String> getgL50_list() {
        return gL50_list;
    }

    public ArrayList<String> getId_exp_list() {
        return id_exp_list;
    }

    public void setCell_line_list(ArrayList<String> cell_line_list) {
        this.cell_line_list = cell_line_list;
    }

    public void setgL50_list(ArrayList<String> gL50_list) {
        this.gL50_list = gL50_list;
    }

    public void setId_exp_list(ArrayList<String> id_exp_list) {
        this.id_exp_list = id_exp_list;
    }
}
