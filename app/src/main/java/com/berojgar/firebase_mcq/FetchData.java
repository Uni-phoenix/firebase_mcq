package com.berojgar.firebase_mcq;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FetchData extends AsyncTask<Void,Void,Void> {
    public static final String TAG = "PiyushTag";
    int request_code;
    Context cntx;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference02,databaseReference03;
    static String subvalue;

    public FetchData(Activity context,int rc){
        this.cntx=context;
        this.request_code = rc;
    }
    public FetchData(Activity context,int rc, String Name){
        this.subvalue=Name;
        this.cntx=context;
        this.request_code = rc;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
       //

    }

    @Override
    protected Void doInBackground(Void... voids) {
        FirebaseApp.initializeApp(cntx);
        Log.i(TAG,"Line01");
        databaseReference = FirebaseDatabase.getInstance().getReference();
        Log.i(TAG,"Line02");
        switch(request_code){
            case 11:
                databaseReference.child("SubjectList").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<String> mList= new ArrayList<String>();
                        mList.clear();
                        Log.i(TAG,"onDataChange");
                        for(DataSnapshot temp:dataSnapshot.getChildren()){
                           // Log.i(TAG,"Enhanced For loop");
                            //Log.i(TAG,"Add on ArrayList : "+temp.getKey().toString());
                            mList.add(temp.getKey().toString());
//                            for(DataSnapshot temp02:temp.child("QPapers").getChildren()){
//                                Log.i(TAG,temp02.getKey()+","+temp02.getValue());
//                            }
                        }
                        Log.i(TAG,"onPostExecute");
                        new MainActivity().listUpdate(mList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.i(TAG,"onCancelled");
                    }
                });
            break;

            case 12:
                databaseReference.child("SubjectList").child(subvalue).child("QPapers").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<PaperList> pList = new ArrayList<PaperList>();
                        pList.clear();
                        Log.i(TAG,"onDataChange");
                        for(DataSnapshot d:dataSnapshot.getChildren()){
                            //Log.i(TAG,d.getKey()+" : "+d.getValue());
                            pList.add(new PaperList(d.getKey().toString(),d.getValue().toString()));
                        }
                        //onPostExecute()
                        new PaperActivity().listUpdate2(pList);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                break;

            case 13:
                final long[] count = {0};
                databaseReference02 = databaseReference.child("MCQ");
                databaseReference03 = databaseReference.child("OQ");
                ArrayList<qData> qList_full_Data = new ArrayList<qData>();
                databaseReference.child("PaperList").child(subvalue).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                        final ArrayList<qData> qList_full_Data = new ArrayList<qData>();
                        qList_full_Data.clear();
                        final ArrayList<String> tempOptionList = new ArrayList<String>();
                        qList_full_Data.clear();
                       // Log.i(TAG,"Value subValue : "+subvalue);
                        final long no_of_question = dataSnapshot.getChildrenCount();

                       // Log.i(TAG,"no_of_question : "+no_of_question);
                       // Log.i(TAG,"Adding Questions");
                        for(final DataSnapshot d: dataSnapshot.getChildren()){
                           // Log.i(TAG,"added temp_values : "+d.getKey()+":"+d.getValue().toString());
                            tempOptionList.clear();
                            switch(d.getValue().toString()){
                                case "MCQ" :
                                   // Log.i(TAG,"Adding MCQ");
                                    databaseReference02.child(d.getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot02) {
                                            for(final DataSnapshot d2 : dataSnapshot02.child("Options").getChildren()){
                                                tempOptionList.add(d2.getValue().toString());
                                               // Log.i(TAG,"tempOptionsList @ "+d2.getValue().toString());
                                            }
                                            qList_full_Data.add(new qData(d.getKey(),d.getValue().toString(),
                                                    dataSnapshot02.child("Que").getValue().toString(),
                                                    dataSnapshot02.child("Ans").getValue().toString(),tempOptionList));
                                            Log.i(TAG,"Value added : "+qList_full_Data.get((int)count[0]).getqQue()+","+qList_full_Data.get((int) count[0]).getqAns()+","+
                                                    qList_full_Data.get((int) count[0]).getqType()+","+qList_full_Data.get((int) count[0]).getOptionList().toString());
                                            count[0]++;
                                           // Log.i(TAG,"OUT.....");

                                            if(count[0]==no_of_question){
                                                new QPaperActivity().updateData(qList_full_Data);
                                            }
                                            tempOptionList.clear();
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                    break;
                                case "OQ" :
                                   // Log.i(TAG,"Adding OQ");
                                    databaseReference03.child(d.getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot03) {
                                            for(final DataSnapshot d3 : dataSnapshot03.child("Options").getChildren()){
                                                tempOptionList.add(d3.getValue().toString());
                                            }
                                            qList_full_Data.add(new qData(d.getKey(),d.getValue().toString(),
                                                    dataSnapshot03.child("Que").getValue().toString(),
                                                    dataSnapshot03.child("Ans").getValue().toString(),tempOptionList));
                                           // Log.i(TAG,"OUT.....");
                                            Log.i(TAG,"Value added : "+qList_full_Data.get((int)count[0]).getqQue()+","+qList_full_Data.get((int) count[0]).getqAns()+","+
                                                    qList_full_Data.get((int) count[0]).getqType()+","+qList_full_Data.get((int) count[0]).getOptionList().toString());
                                            count[0]++;
                                            if(count[0]==no_of_question){
                                                new QPaperActivity().updateData(qList_full_Data);
                                            }
                                            tempOptionList.clear();
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                    break;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                break;

        }



        return null;
    }

}
