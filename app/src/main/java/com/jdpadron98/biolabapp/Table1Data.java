package com.jdpadron98.biolabapp;

public class Table1Data {

    private String id_biolab; //The three letters of the ID
    private String num_id_biolab; //The four numbers of the ID
    private String id_sample;
    private String smiles;
    private String soluble;
    private String notes;


    public Table1Data(String id_sample, String smiles, String soluble, String notes) {

        this.id_sample = id_sample;
        this.smiles = smiles;
        this.soluble = soluble;
        this.notes = notes;

    }

    public String getId_sample() {
        return id_sample;
    }

    public void setId_sample(String id_sample) {
        this.id_sample = id_sample;
    }

    public String getSmiles() {
        return smiles;
    }

    /**
     *
     * @return
     */
    public String getAllID() {
        return id_biolab + num_id_biolab;
    }

    public String getId_biolab() {
        return id_biolab;
    }

    public void setId_biolab(String id_biolab) {
        this.id_biolab = id_biolab;
    }

    public String getNum_id_biolab() {
        return num_id_biolab;
    }

    public void setNum_id_biolab(String num_id_biolab) {
        this.num_id_biolab = num_id_biolab;
    }

    public void setSmiles(String smiles) {
        this.smiles = smiles;
    }

    public String getSoluble() {
        return soluble;
    }

    public void setSoluble(String soluble) {
        this.soluble = soluble;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


}
