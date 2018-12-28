package com.example.thomas.gymclubapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.thomas.gymclubapp.API.GymApp.GymAPI;
import com.example.thomas.gymclubapp.Models.Login;
import com.example.thomas.gymclubapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class LoginFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_login, container, false);
        final Context mContext = view.getContext();

        final GymAPI gymapi = new GymAPI(mContext);
        final EditText email = (EditText)view.findViewById(R.id.email);
        final EditText password = (EditText)view.findViewById(R.id.password);

        Button login = (Button)view.findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (view.getId() == R.id.login) {
                    String Mail = email.getText().toString();;
                    String Pwd = password.getText().toString();
                    if (Mail != "" && Pwd != "") {
                        Login login = new Login(Mail, Pwd);
                        gymapi.Login(new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject json = new JSONObject(response);
                                    Toast.makeText(mContext, json.toString(), Toast.LENGTH_LONG).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }, login);
                    } else {
                        Toast.makeText(getContext(), "Wrong incomplete", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        return view;
    }
}
