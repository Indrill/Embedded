package com.example.thomas.gymclubapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;

import com.example.thomas.gymclubapp.DataBase.ManageDB;
import com.example.thomas.gymclubapp.Models.Coach;
import com.example.thomas.gymclubapp.Network.NetworkCheck;
import com.example.thomas.gymclubapp.R;

import java.util.ArrayList;
import java.util.HashMap;

public class TrainersFragment extends ListFragment {
    ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
    SimpleAdapter adapter;
    NetworkCheck network;
    ManageDB myDB;

    String name[] = {"name1", "name2", "name3", "name4", "name5", "name6", "name7"};
    String desc[] = {"desc1 desc1 desc1 desc1 desc1 desc1 desc1 desc1 desc1 desc1 desc1 desc1 desc1",
                    "desc2 desc2 desc2 desc2 desc2 desc2 desc2 desc2 desc2 desc2 desc2 desc2 desc2",
                    "desc3 desc3 desc3 desc3 desc3 desc3 desc3 desc3 desc3 desc3 desc3 desc3 desc3",
                    "desc4 desc4 desc4 desc4 desc4 desc4 desc4 desc4 desc4 desc4 desc4 desc4 desc4",
                    "desc5 desc5 desc5 desc5 desc5 desc5 desc5 desc5 desc5 desc5 desc5 desc5 desc5",
                    "desc6 desc6 desc6 desc6 desc6 desc6 desc6 desc6 desc6 desc6 desc6 desc6 desc6",
                    "desc7 desc7 desc7 desc7 desc7 desc7 desc7 desc7 desc7 desc7 desc7 desc7 desc7"};
    String num[] = {"0102030405", "1020304050", "0607080910", "6070809010", "0000000000", "1213141516", "2021222324"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_trainers, container, false);
        Context mContext = view.getContext();
        myDB = new ManageDB(mContext);
        network = new NetworkCheck(mContext);
        setTrainersList();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
                callNbr(data.get(pos).get("Phone"));
            }
        });
    }

    public void callNbr(String nbr) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" +  nbr));
        startActivity(callIntent);
    }

    private void setTrainersList() {
        HashMap<String, String> map = new HashMap<String, String>();
        Coach newCoach;
        if (!network.isNetworkAvailable()) {
            ArrayList<Coach> list = myDB.getCoachList();
            for (int i = 0; i < list.size(); i++) {
                map = new HashMap<String, String>();
                map.put("Coach", list.get(i).getName());
                map.put("Info", list.get(i).getDescription());
                map.put("Phone", list.get(i).getPhoneNbr());
                map.put("Image", Integer.toString(R.drawable.ic_trainers));
                data.add(map);
            }
        } else {
            for (int i = 0; i < name.length; i++) {
                newCoach = new Coach(name[i], desc[i], num[i]);
                myDB.addCoach(newCoach);
                map = new HashMap<String, String>();
                map.put("Coach", name[i]);
                map.put("Info", desc[i]);
                map.put("Phone", num[i]);
                map.put("Image", Integer.toString(R.drawable.ic_trainers));
                data.add(map);
            }
        }
        String[] from = {"Coach","Info","Phone","Image"};
        int[] to = {R.id.nameTxt, R.id.infoTxt, R.id.phoneNbr, R.id.imageView1};
        adapter = new SimpleAdapter(getActivity().getBaseContext(), data, R.layout.trainerlist_item, from, to);
        setListAdapter(adapter);
    }
}