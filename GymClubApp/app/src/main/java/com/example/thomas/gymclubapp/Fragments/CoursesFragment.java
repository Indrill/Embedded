package com.example.thomas.gymclubapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;

import com.example.thomas.gymclubapp.R;

import java.util.ArrayList;
import java.util.HashMap;


public class CoursesFragment extends ListFragment {
    ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
    SimpleAdapter adapter;
    String[] videos = {"http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4","http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4","http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"
            ,"http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4","http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4","http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4",
            "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4","http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4","http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_videolist, container, false);

        HashMap<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < videos.length; i++) {
            map = new HashMap<String, String>();
            map.put("Info", videos[i]);
            data.add(map);
        }
        String[] from = {"Info"};
        int[] to = {R.id.infoTxt};
        adapter = new SimpleAdapter(getActivity().getBaseContext(), data, R.layout.videolist_item, from, to);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos,
                                    long id) {
                getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.content_frame,
                        new Video()).commit();
            }
        });
    }
}
