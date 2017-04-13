package com.example.personal.myapplication2;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Signuptab extends Fragment {
    EditText uname,email,password,phone,age,bldgrp,weight,height;
    String uname_text,email_text,password_text,phone_text,age_text, bldgrp_text,weight_text,height_text;
    Button sbmt = null;
    InputStream is = null;
    DataBaseAdapter db;
    String line,result;
    int code;
    Context context = null;
    public Signuptab() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_signuptab, container, false);
        db =new DataBaseAdapter(getActivity());
        db=db.open();
        uname = (EditText) v.findViewById(R.id.suname);
        email = (EditText) v.findViewById(R.id.semail);
        password = (EditText) v.findViewById(R.id.spwd);
        phone = (EditText) v.findViewById(R.id.spn);
        age = (EditText) v.findViewById(R.id.sage);
        bldgrp = (EditText) v.findViewById(R.id.sblgp);
        weight = (EditText) v.findViewById(R.id.sweight);
        height = (EditText) v.findViewById(R.id.sheight);
        sbmt = (Button) v.findViewById(R.id.button2);
        sbmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uname_text = uname.getText().toString();
                email_text = email.getText().toString();
                password_text = password.getText().toString();
                phone_text = phone.getText().toString();
                age_text = age.getText().toString();
                bldgrp_text = bldgrp.getText().toString();
                weight_text = weight.getText().toString();
                height_text = height.getText().toString();
                if(uname_text.equals("")||password_text.equals("")||email_text.equals("")||phone_text.equals("")||height_text.equals("")||weight_text.equals("")||bldgrp_text.equals("")||age_text.equals(""))
                {
                    Log.i("msg","failed");
                    return;
                }
                else
                {
                    Log.i("msg","Inserted Successfully");
                    db.insertEntry(uname_text,email_text,phone_text, password_text,height_text,weight_text,bldgrp_text,age_text);
                }
            }
        });
        return v;
    }


}
