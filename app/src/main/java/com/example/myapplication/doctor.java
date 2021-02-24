package com.example.myapplication;

public class doctor {
    private String docName;
    private String docProf;
    private String descriptionDoc;
    private String dockey;
    private String availability;
    public doctor(){

    }

    public doctor(String docName, String docProf, String descriptionDoc, String dockey) {
        this.docName = docName;
        this.docProf = docProf;
        this.descriptionDoc = descriptionDoc;
        this.dockey = dockey;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocProf() {
        return docProf;
    }

    public void setDocProf(String docProf) {
        this.docProf = docProf;
    }

    public String getDescriptionDoc() {
        return descriptionDoc;
    }

    public void setDescriptionDoc(String descriptionDoc) {
        this.descriptionDoc = descriptionDoc;
    }

    public String getDockey() {
        return dockey;
    }

    public void setDockey(String dockey) {
        this.dockey = dockey;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
