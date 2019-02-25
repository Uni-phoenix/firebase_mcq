package com.berojgar.firebase_mcq;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class QPaperActivity extends AppCompatActivity {
    static ProgressDialog progressDialog;
    public static final String TAG = "PiyushTag";
    int data_fetch_request = 13;
    public static ArrayList<qData> all_Q_data;
    static ArrayList<aPassData> all_Q_data02;
    static ArrayList<B> tempB;
    static int size=0;
    static AlertDialog.Builder alertDialogBox;
    static GridAdapter gridAdapter;
    static Fragment_questions f_q;
    static FragmentManager fr_mng;
    static FragmentTransaction fr_trans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(QPaperActivity.this, android.app.AlertDialog.THEME_HOLO_DARK);
        setContentView(R.layout.activity_qpaper);
        progressDialog.setMessage("Fetching Questions....");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.create();
        progressDialog.show();
        all_Q_data = new ArrayList<qData>();
        all_Q_data02 = new ArrayList<aPassData>();
        tempB = new ArrayList<B>();
        Toast.makeText(QPaperActivity.this,getIntent().getStringExtra("PaperCode"),Toast.LENGTH_SHORT).show();
        GridView gridView = (GridView)findViewById(R.id.view_qList);
        gridAdapter = new GridAdapter(QPaperActivity.this,all_Q_data);
        gridView.setAdapter(gridAdapter);
        //gridView.setAdapter();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(QPaperActivity.this,"Position : "+view.getId(),Toast.LENGTH_SHORT).show();



               Log.i(TAG,"Clicked on : "+all_Q_data02.get(view.getId()).getqID());

               for(aPassData a: all_Q_data02){
                   Log.i(TAG,"Added Data 04: "+a.getqID()+a.getqType()+a.getqQue()+a.getqAns());
                   for(B b: a.getOptionList()){
                       Log.i(TAG,"Options : "+b.getStr());
                   }
               }
//
//                for(qData d:all_Q_data){
//
//                    for(String s:d.getOptionList()){
//                        tempB.add(new B(s));
//                    }
//                    all_Q_data02.add(new aPassData(d.getqID(),d.getqType(),d.getqQue(),d.getqAns(),tempB));
//                    //all_Q_data02.add(d.getqID(),d.getqType(),d.getqQue(),d.getqAns(),tempB);
//                }
//                Log.i(TAG,"Data Cloned.............................");
//                for(qData a:all_Q_data){
//                    Log.i(TAG,a.getqID()+a.getqType()+a.getOptionList().toString());
//                }





                Bundle bndl = new Bundle();
                bndl.putSerializable("All Data",all_Q_data02);
                bndl.putInt("now_value",view.getId());
                f_q = new Fragment_questions();
                f_q.setArguments(bndl);
                fr_mng = getSupportFragmentManager();
                fr_trans = fr_mng.beginTransaction();
                fr_trans.addToBackStack("FragmentTag01");
                fr_trans.replace(android.R.id.content,f_q);
                fr_trans.commit();
            }
        });
        new FetchData(this,data_fetch_request,getIntent().getStringExtra("PaperCode")).execute();
    }

    @Override
    public void onBackPressed() {
        if(fr_mng.getBackStackEntryCount()>0){
            fr_mng.popBackStackImmediate();
        }else{
            alertDialogBox = new AlertDialog.Builder(this);
            alertDialogBox.setCancelable(false);
            alertDialogBox.setMessage("DO you wanna exit the TEST"+"\n"+"unsaved progress will be lost");
            alertDialogBox.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            QPaperActivity.super.onBackPressed();
                        }
                    });
            alertDialogBox.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            removeDailogBox();
                        }
            });
            alertDialogBox.create().show();

            //do nothing..
            //super.onBackPressed();
        }
    }

    public void removeDailogBox(){
        alertDialogBox.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                //do nothing..
            }
        });
    }



    public void updateData(ArrayList<qData> a){
        Log.i(TAG,"Grid ListUpdated");
        for (qData temp : a){
            all_Q_data.add(temp);
            Log.i(TAG,"Added Data : "+temp.getqID()+","+temp.getqQue()+","+temp.getqAns()+","+temp.getqType()+","+temp.getOptionList().toString());
            for(String op : temp.getOptionList()){
                Log.i(TAG,"Options : "+op);
            }
        }
        Log.i(TAG,"Data Updated...............");
        for(qData temp02 : all_Q_data){
            Log.i(TAG,"Added Data 02: "+temp02.getqID()+","+temp02.getqQue()+","+temp02.getqAns()+","+temp02.getqType()+","+temp02.getOptionList().toString());
            for(String op : temp02.getOptionList()){
                Log.i(TAG,"Options : "+op);
            }
        }
        Log.i(TAG,"Data Updated in other List...............");
        for(qData temp03 : all_Q_data){
            tempB.clear();
            for(String s : temp03.getOptionList()){
                tempB.add(new B(s));
                Log.i(TAG,"Line Options : "+s);
            }
            all_Q_data02.add(new aPassData(temp03.getqID(),temp03.getqType(),temp03.getqQue(),temp03.getqID(),tempB));
        }
        for(aPassData temp04 : all_Q_data02){
            Log.i(TAG,"Added Data 03: "+temp04.getqID()+temp04.getqType()+temp04.getqQue()+temp04.getqAns());
            for(B b: temp04.getOptionList()){
                Log.i(TAG,"Options : "+b.getStr());
            }
        }


        size = a.size();
        Log.i(TAG,"Size changed : "+size);
        gridAdapter.notifyDataSetChanged();
        Log.i(TAG,"notifyDataSetChanged");
        progressDialog.dismiss();

    }


}
