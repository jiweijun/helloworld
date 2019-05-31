package com.example.socket.linkdatabaseapplication.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.socket.linkdatabaseapplication.R;
import com.example.socket.linkdatabaseapplication.scan;

public class LiveFragment extends BaseFragment {


    public LiveFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_live,container,false);
        return view;
    }
    public void go2(View v) {


        Intent intent = new Intent();

        intent.setClass(getActivity(),scan.class);

        startActivity(intent);
    }
}
