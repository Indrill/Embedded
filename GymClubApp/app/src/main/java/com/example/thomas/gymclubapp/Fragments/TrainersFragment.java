package com.example.thomas.gymclubapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.thomas.gymclubapp.API.GymApp.GymAPI;
import com.example.thomas.gymclubapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.HashMap;

public class TrainersFragment extends ListFragment {
    ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
    SimpleAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_trainers, container, false);
        Context mContext = view.getContext();

        final GymAPI gymapi = new GymAPI(mContext);

        gymapi.getTrainers( new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray arrJson = new JSONArray(response);
                    String[] players = new String[arrJson.length()];
                    for(int i = 0; i < arrJson.length(); i++) {
                        players[i] = arrJson.getString(i);
                    }
                    setTrainersList(players);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos,
                                    long id) {
                Toast.makeText(getActivity(), data.get(pos).get("Player"), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void setTrainersList(String[] players) {
        HashMap<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < players.length; i++) {
            map = new HashMap<String, String>();
            map.put("Player", players[i]);
            map.put("Info", "");
            map.put("Image", Integer.toString(R.drawable.ic_trainers));

            data.add(map);
        }
        String[] from = {"Player","Info", "Image"};
        int[] to = {R.id.nameTxt, R.id.infoTxt, R.id.imageView1};
        adapter = new SimpleAdapter(getActivity().getBaseContext(), data, R.layout.trainerlist_item, from, to);
        setListAdapter(adapter);
    }
}