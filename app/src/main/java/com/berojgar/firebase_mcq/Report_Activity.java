package com.berojgar.firebase_mcq;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class Report_Activity extends AppCompatActivity {

    static ProgressDialog progressDialog;
    ArrayList<String> explain_list;
    public static int request_Code=14;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_);
        explain_list = new ArrayList<String>();
        progressDialog = new ProgressDialog(Report_Activity.this,AlertDialog.THEME_DEVICE_DEFAULT_DARK);
        progressDialog.setMessage("Generating Report..");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.create();
        progressDialog.show();
        new FetchData(Report_Activity.this,request_Code);
    }

    public void update_activity(){
        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

}
