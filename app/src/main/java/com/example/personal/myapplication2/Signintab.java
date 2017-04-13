package com.example.personal.myapplication2;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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
import java.util.Timer;
import java.util.TimerTask;


public class Signintab extends Fragment {
    EditText uname;
    EditText password;
    String uname_text = null;
    String password_text = null;
    InputStream is = null;
    Button sbmt;
    String line = null;
    String result = null;
    int code;
    Context context = null;
    DataBaseAdapter db;
    public Signintab() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_signintab, container, false);
        uname = (EditText) v.findViewById(R.id.siuname);
        password = (EditText) v.findViewById(R.id.sipass);
        db=new DataBaseAdapter(getActivity());
        db=db.open();
        sbmt = (Button) v.findViewById(R.id.proceed);
        sbmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uname_text = uname.getText().toString();
                password_text = password.getText().toString();
                String storedPassword=db.getSinlgeEntry(uname_text);
                if(password_text.equals(storedPassword))
                {
                    Log.i("msg","Logged In Successfully");
                    new Timer().schedule(new TimerTask(){
                        public void run() {
                            startActivity(new Intent(getActivity(), HomePage.class));
                            getActivity().finish();
                        }
                    }, 2000);
                    db.close();
                }
                else
                {
                    Log.i("msg","Invalid Credentials");
                }
            }
            });
        return v;
    }
}
