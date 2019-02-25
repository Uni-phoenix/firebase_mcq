package com.berojgar.firebase_mcq;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {

    public static final String TAG = "PiyushTag";
    Context cntx;
    ArrayList<qData> tempM;

    public GridAdapter(Context context, ArrayList<qData> m){
        this.cntx = context;
        this.tempM = m;
        Log.i(TAG,"INIT with size : "+m.size());
    }

    @Override
    public int getCount() {
        Log.i(TAG,"getCount() : "+ tempM.size());
        return tempM.size();
    }

    @Override
    public Object getItem(int position) {
        return position+1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater =LayoutInflater.from(cntx);
        convertView = layoutInflater.inflate(R.layout.num_button,null);
        RelativeLayout relativeLayout = (RelativeLayout) convertView.findViewById(R.id.view_relative_for_grid);
        TextView button = (TextView) convertView.findViewById(R.id.m_Button);
        if(button.isClickable()){
            Log.i(TAG,"Its CLickable..");
        }
        convertView.setId(position);
        button.setText(Integer.toString(position+1));


        return convertView;
    }
}
