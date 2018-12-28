package com.example.thomas.gymclubapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import com.example.thomas.gymclubapp.DataBase.ManageDB;
import com.example.thomas.gymclubapp.Models.News;
import com.example.thomas.gymclubapp.Network.NetworkCheck;
import com.example.thomas.gymclubapp.R;

import java.util.ArrayList;
import java.util.HashMap;

public class NewsFragment extends ListFragment {
    ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
    SimpleAdapter adapter;
    NetworkCheck network;
    ManageDB myDB;

    String title[] = {"news 1", "news 2", "news 3", "news 4", "news 5", "news 6", "news 7"};
    String content[] = {"Nullam pellentesque quam nibh, id porta risus semper et. Duis vestibulum suscipit libero, ac porttitor diam dapibus a. Sed egestas ipsum et justo venenatis, vel pharetra nisi varius. Suspendisse egestas elementum imperdiet. Maecenas aliquam erat sapien, volutpat cursus enim lacinia id. Vestibulum",
            "dignissim quam. Morbi varius nec nibh nec sollicitudin. Sed nec diam a diam semper ultricies. Donec in est nec lacus convallis convallis. Maecenas tempor nulla a blandit fringilla. Etiam purus libero",
            "Proin facilisis purus augue, quis vestibulum elit suscipit quis. Sed auctor facilisis tortor, et bibendum diam feugiat posuere. Suspendisse blandit tortor dictum quam dignissim, eu sodales dolor euismod.",
            "this is content this is content this is content this is content this is content this is content this is content this is content this is content this is content this is content this is content this is content",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed massa neque, iaculis sed eros et, tincidunt suscipit nulla. Morbi bibendum mauris ut aliquam feugiat. Donec id neque aliquam, sagittis elit id, congue leo. Vivamus enim mi, dapibus sed metus congue, lobortis rutrum mauris. Vivamus dolor sem, tristique eget commodo congue, auctor viverra sem. Morbi malesuada commodo justo ac tristique. Fusce consectetur consequat rhoncus",
            "content 6 content 6 content 6 content 6 content 6 content 6 content 6 content 6 content 6 content 6 content 6 content 6 content 6",
            "content news 7 content news 7 content news 7 content news 7 content news 7 content news 7 content news 7 content news 7 content news 7 content news 7 content news 7 content news 7 content news 7"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_news, container, false);
        Context mContext = view.getContext();
        myDB = new ManageDB(mContext);
        network = new NetworkCheck(mContext);
        setNewsList();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void setNewsList() {
        HashMap<String, String> map = new HashMap<String, String>();
        News newNew;
        if (!network.isNetworkAvailable()) {
            ArrayList<News> list = myDB.getNewsList();
            for (int i = 0; i < list.size(); i++) {
                map = new HashMap<String, String>();
                map.put("Title", list.get(i).getTitle());
                map.put("Content", list.get(i).getContent());
                data.add(map);
            }
        } else {
            for (int i = 0; i < title.length; i++) {
                newNew = new News(title[i], content[i]);
                myDB.addNew(newNew);
                map = new HashMap<String, String>();
                map.put("Title", title[i]);
                map.put("Content", content[i]);
                data.add(map);
            }
        }
        String[] from = {"Title","Content"};
        int[] to = {R.id.newsTitle, R.id.newsContent};
        adapter = new SimpleAdapter(getActivity().getBaseContext(), data, R.layout.newslist_item, from, to);
        setListAdapter(adapter);

    }
}
