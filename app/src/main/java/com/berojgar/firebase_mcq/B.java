package com.berojgar.firebase_mcq;

import java.io.Serializable;

public class B implements Serializable {

    String str;

    public B(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
