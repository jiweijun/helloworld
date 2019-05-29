package com.example.socket.linkdatabaseapplication.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.socket.linkdatabaseapplication.R;
import com.example.socket.linkdatabaseapplication.scan;


public class HomeFragment extends BaseFragment {

    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        return view;
    }

    public void go2(View v) {


        Intent intent = new Intent();

        intent.setClass(getActivity(),scan.class);

        startActivity(intent);
    }
}
