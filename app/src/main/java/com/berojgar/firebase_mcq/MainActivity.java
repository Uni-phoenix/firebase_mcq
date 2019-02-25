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
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "PiyushTag";
    static ProgressDialog progressDialog;
    static CustomAdapter myAdapter;
    static public ArrayList<String> subList;
    //static ListView listView;
    RecyclerView recyclerView;
    int data_fetch_request=11;
    //static RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    //FetchData fetchData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG,"OnCreate");
        subList = new ArrayList<>();
//        Log.i(TAG,"ArrayList contains : "+subList.toString());
        //ProgressDialog INIT
        progressDialog = new ProgressDialog(MainActivity.this, AlertDialog.THEME_HOLO_DARK);
        progressDialog.setMessage("Fetching Available Subjects..");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.create();
        progressDialog.show();

        //background Execute Data Fetching
        new FetchData(MainActivity.this,data_fetch_request).execute();


        //ListView INIT
        recyclerView = (RecyclerView) findViewById(R.id.view_main_recycler);
        Log.i(TAG,"ListView INIT");
        recyclerView.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new CustomAdapter(subList);
        recyclerView.setAdapter(myAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.i(TAG,"View view : "+position);
                //Toast.makeText(MainActivity.this,"Position"+subList.get(position),Toast.LENGTH_SHORT).show();
                Intent i1 = new Intent(MainActivity.this,PaperActivity.class);
                i1.putExtra("Subject_Name",subList.get(position));
                startActivity(i1);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }

    public void listUpdate(ArrayList<String> mList){
        //remove Progressbar when Data Fetched
        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }
        subList.clear();
        for(String t:mList){
            Log.i(TAG,"FOR : "+t);
            subList.add(t);
        }
        Log.i(TAG,"contents of List : "+subList.toString());
        myAdapter.notifyDataSetChanged();
        Log.i(TAG,"Fetched Data......"+subList.toString());
        Log.i(TAG,"changed");
        Log.i(TAG,"put to list");

        //put to list
    }
}
