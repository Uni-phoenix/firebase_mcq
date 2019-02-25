package com.berojgar.firebase_mcq;

import android.os.Bundle;
import android.service.autofill.TextValueSanitizer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.ArrayList;

public class Fragment_questions extends Fragment {

    public static final String TAG = "PiyushTag";
    static ArrayList<aPassData> my_all_data;
    Button b_p,b_n;
    CheckBox c_1,c_2,c_3,c_4;
    TextView t_q;


    static int now_value;
    public  Fragment_questions(){
            //empty constructor
    }

    public void now_value_change(int value){
        t_q.setText(my_all_data.get(now_value).getqQue());
        c_1.setText(my_all_data.get(now_value).getOptionList().get(0).getStr());
        c_2.setText(my_all_data.get(now_value).getOptionList().get(1).getStr());
        c_3.setText(my_all_data.get(now_value).getOptionList().get(2).getStr());
        c_4.setText(my_all_data.get(now_value).getOptionList().get(3).getStr());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question,container,false);
        t_q = view.findViewById(R.id.textView_question);
        b_p = view.findViewById(R.id.button_next_fragment);
        b_n = view.findViewById(R.id.button_previous_fragment);
        c_1 = view.findViewById(R.id.checkBox_op1);
        c_2 = view.findViewById(R.id.checkBox_op2);
        c_3 = view.findViewById(R.id.checkBox_op3);
        c_4 = view.findViewById(R.id.checkBox_op4);

        my_all_data = (ArrayList<aPassData>) getArguments().getSerializable("All Data");
        now_value = getArguments().getInt("now_value");
        t_q.setText(my_all_data.get(now_value).getqQue());
        c_1.setText(my_all_data.get(now_value).getOptionList().get(0).getStr());
        c_2.setText(my_all_data.get(now_value).getOptionList().get(1).getStr());
        c_3.setText(my_all_data.get(now_value).getOptionList().get(2).getStr());
        c_4.setText(my_all_data.get(now_value).getOptionList().get(3).getStr());

        b_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(now_value==0){
                    Toast.makeText(getActivity(),"already first..",Toast.LENGTH_SHORT).show();
                }else{
                    now_value--;
                    now_value_change(now_value);
                }
            }
        });
        b_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(now_value==my_all_data.size()-1){
                    Toast.makeText(getActivity(),"already last..",Toast.LENGTH_SHORT).show();
                }else{
                    now_value++;
                    now_value_change(now_value);
                }
            }
        });


//        Log.i(TAG,"Fragment value fetching for Options");
//        for(aPassData t: my_all_data){
//            Log.i(TAG,t.getqID()+t.getqType()+t.getqQue()+t.getqAns());
//            if(t.getOptionList()==null){
//
//                Log.i(TAG,"Its NULL");
//            }
//            for(B s:t.getOptionList()){
//                Log.i(TAG,s.getStr());
//            }
//        }
//        if(my_all_data.get(now_value).getOptionList()==null){
//            Log.i(TAG,"Its null....");
//        }
//        for(String s:my_all_data.get(now_value).getOptionList()){
//            Log.i(TAG,s);
//        }


        return view;
    }


}
