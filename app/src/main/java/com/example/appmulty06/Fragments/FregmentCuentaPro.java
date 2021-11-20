package com.example.appmulty06.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appmulty06.R;


public class FregmentCuentaPro extends Fragment{
    @Nullable

    EditText id;
    EditText user;
    EditText tip;
    String a="a";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_cuenta_pro,container,false);
        id = (EditText) view.findViewById(R.id.ID);
        user = (EditText) view.findViewById(R.id.User);
        tip = (EditText) view.findViewById(R.id.CuentaD);
        
        return view;




    }


    public void onClickcc(View v) {
        id.setText("a");
        user.setText("a");
        tip.setText("a");

    }

}
