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

import com.example.thomas.gymclubapp.API.GymApp.GymAPI;
import com.example.thomas.gymclubapp.Models.Register;
import com.example.thomas.gymclubapp.R;

public class RegisterFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_register, container, false);
        Context mContext = view.getContext();

        final GymAPI gymapi = new GymAPI(mContext);
        final EditText email = (EditText)view.findViewById(R.id.email);
        final EditText firstname = (EditText)view.findViewById(R.id.firstname);
        final EditText lastname = (EditText)view.findViewById(R.id.lastname);
        final EditText password = (EditText)view.findViewById(R.id.password);

        Button regist = (Button)view.findViewById(R.id.register);
        regist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (view.getId() == R.id.register) {
                    String Mail = email.getText().toString();;
                    String FstName = firstname.getText().toString();
                    String LstName = lastname.getText().toString();
                    String Pwd = password.getText().toString();
                    if (Mail != "" && FstName != "" && LstName != "" && Pwd != "") {
                        Register register = new Register(Mail, Pwd, FstName, LstName);
                        // gymapi.Login(); TODO finir le call api

                    } else {
                        Toast.makeText(getContext(), "Wrong incomplete", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        return view;
    }
}