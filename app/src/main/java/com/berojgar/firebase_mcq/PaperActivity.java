package com.berojgar.firebase_mcq;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class PaperActivity extends AppCompatActivity {

    static public ArrayList<PaperList> mList;
    static public ArrayList<String> paperNameList;
    public static final String TAG = "PiyushTag";
    static ProgressDialog progressDialog;
    int data_Fetch_request=12;
    static CustomAdapter customAdapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paper);
        mList = new ArrayList<PaperList>();
        paperNameList = new ArrayList<String>();
        String subName=getIntent().getStringExtra("Subject_Name");
        Toast.makeText(PaperActivity.this,subName+" Papers",Toast.LENGTH_SHORT).show();
        progressDialog = new ProgressDialog(PaperActivity.this, AlertDialog.THEME_HOLO_DARK);
        progressDialog.setMessage("Fetching Available Paper Sets...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.create();
        progressDialog.show();

        //background Data Fetching
        new FetchData(this,data_Fetch_request,subName).execute();

        //Recycler View INIT
        recyclerView = (RecyclerView)findViewById(R.id.view_recycler_paperList);
        recyclerView.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        customAdapter = new CustomAdapter(paperNameList);
        recyclerView.setAdapter(customAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Log.i(TAG, String.valueOf(position));
                Intent i2 = new Intent(PaperActivity.this,QPaperActivity.class);
                i2.putExtra("PaperCode",mList.get(position).getPaperName().toString());
                //Log.i(TAG,"Value passed : "+mList.get(position).getPaperName().toString());
                startActivity(i2);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }

    public void listUpdate2(ArrayList<PaperList> temp){
        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }
        for(PaperList t: temp){
            //Log.i(TAG,t.getPaperID()+":"+t.getPaperName());
            mList.add(new PaperList(t.getPaperID().toString(),t.getPaperName().toString()));
            paperNameList.add(t.getPaperName().toString());
            customAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
