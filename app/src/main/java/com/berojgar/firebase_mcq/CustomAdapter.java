package com.berojgar.firebase_mcq;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{
    //public constructor
    ArrayList<String> subList;

    public CustomAdapter(ArrayList<String> mList){
        this.subList = mList;
        for(String t:mList){
            Log.i("PiyushTag","FOR 2: "+t);
        }
    }
    //My View Holder
    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView t1;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            t1 = (TextView)itemView.findViewById(R.id.view_text);
        }
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.patta,viewGroup,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.t1.setText(subList.get(i));
  //      myViewHolder.notifyAll();
    }

    @Override
    public int getItemCount() {
        Log.i("PiyushTag", String.valueOf(subList.size()));
        return subList.size();
    }
}





/*public class CustomAdapter extends BaseAdapter {

    Context cntx;
    ArrayList<String> arrayList;

    public CustomAdapter(Context context, ArrayList<String> mArrayList){
        this.arrayList=mArrayList;
        this.cntx=context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflator =LayoutInflater.from(cntx);
        convertView =inflator.inflate(R.layout.patta,null);
        TextView t1 =(TextView)convertView.findViewById(R.id.view_text);
        t1.setText(arrayList.get(position));
        return convertView;
    }
}
*/