package com.example.socket.linkdatabaseapplication.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.socket.linkdatabaseapplication.R;
import com.example.socket.linkdatabaseapplication.WIFI.SocketActivity;
import com.example.socket.linkdatabaseapplication.WIFI.wifiActivity;
import com.example.socket.linkdatabaseapplication.barscan.scan;

public class LiveFragment extends BaseFragment {

    private Button btn;
    private Button btn_scan;
    private Button btn_wifi;
    View view;

    public LiveFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.fragment_live,container,false);
        btn = (Button) view.findViewById(R.id.btn_socket);
        btn_scan = (Button) view.findViewById(R.id.btn_scan);
        btn_wifi = (Button) view.findViewById(R.id.btn_wifi);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);


        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();

                intent.setClass(getActivity(), SocketActivity.class);

                startActivity(intent);
            }
        });

        btn_scan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();

                intent.setClass(getActivity(), scan.class);

                startActivity(intent);
            }
        });

        btn_wifi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();

                intent.setClass(getActivity(), wifiActivity.class);

                startActivity(intent);
            }
        });

    }


}
