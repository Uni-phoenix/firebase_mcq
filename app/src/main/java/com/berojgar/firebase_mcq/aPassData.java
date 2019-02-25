package com.berojgar.firebase_mcq;

import java.io.Serializable;
import java.util.ArrayList;

public class aPassData implements Serializable {

    String qID;
    String qType;
    String qQue;
    String qAns;
    ArrayList<B> optionList;

    public aPassData(String qID, String qType, String qQue, String qAns, ArrayList<B> optionList) {
        this.qID = qID;
        this.qType = qType;
        this.qQue = qQue;
        this.qAns = qAns;
        this.optionList = optionList;
    }

    public String getqID() {
        return qID;
    }

    public void setqID(String qID) {
        this.qID = qID;
    }

    public String getqType() {
        return qType;
    }

    public void setqType(String qType) {
        this.qType = qType;
    }

    public String getqQue() {
        return qQue;
    }

    public void setqQue(String qQue) {
        this.qQue = qQue;
    }

    public String getqAns() {
        return qAns;
    }

    public void setqAns(String qAns) {
        this.qAns = qAns;
    }

    public ArrayList<B> getOptionList() {
        return optionList;
    }

    public void setOptionList(ArrayList<B> optionList) {
        this.optionList = optionList;
    }
}
