package com.berojgar.firebase_mcq;

public class PaperList {
    String paperName;
    String paperID;

    public PaperList(String a,String b){
        this.paperName = a;
        this.paperID = b;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public String getPaperID() {
        return paperID;
    }

    public void setPaperID(String paperID) {
        this.paperID = paperID;
    }
}
