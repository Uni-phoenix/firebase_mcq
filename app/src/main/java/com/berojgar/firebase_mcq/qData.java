package com.berojgar.firebase_mcq;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class qData implements Serializable {

    String qID;
    String qType;
    String qQue;
    String qAns;
    ArrayList<String> optionList;

    public qData(String qID, String qType, String qQue, String qAns, ArrayList<String> optionList) {
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

    public ArrayList<String> getOptionList() {
        return optionList;
    }

    public void setOptionList(ArrayList<String> optionList) {
        this.optionList = optionList;
    }
}


//extra material


/*
@Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(qID);
        dest.writeString(qType);
        dest.writeString(qQue);
        dest.writeString(qAns);
        dest.writeList(optionList);
    }
    private qData(Parcel in){
        qID = in.readString();
        qQue = in.readString();
        qType = in.readString();
        qAns = in.readString();
        in.readList(optionList,this.getClass().getClassLoader());
        //        optionList = in.readArrayList();
    }
    public static final Parcelable.Creator<qData> CREATOR = new Parcelable.Creator<qData>(){

        @Override
        public qData createFromParcel(Parcel source) {
            return new qData(source);
        }

        @Override
        public qData[] newArray(int size) {
            return new qData[size];
        }
    };
 */
