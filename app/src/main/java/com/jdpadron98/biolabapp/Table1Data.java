package com.jdpadron98.biolabapp;

public class Table1Data {

    private String userIdBiolab; //The three letters of the ID
    private String numIdBiolab; //The four numbers of the ID
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
     * Method that gets the whole id of biolab
     * @return The three letters of the ID plus the four numbers of the ID
     */
    public String getAllID() {
        return userIdBiolab + numIdBiolab;
    }

    public String getUserIdBiolab() {
        return userIdBiolab;
    }

    public void setUserIdBiolab(String userIdBiolab) {
        this.userIdBiolab = userIdBiolab;
    }

    public String getNumIdBiolab() {
        return numIdBiolab;
    }

    public void setNumIdBiolab(String numIdBiolab) {
        this.numIdBiolab = numIdBiolab;
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
