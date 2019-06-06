package com.example.socket.linkdatabaseapplication.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.socket.linkdatabaseapplication.ChartActivity;
import com.example.socket.linkdatabaseapplication.R;
import com.example.socket.linkdatabaseapplication.scan;

public class LiveFragment extends BaseFragment {

    private Button btn;
    View view;

    public LiveFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.fragment_live,container,false);
        btn = (Button) view.findViewById(R.id.btn_socket);
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
    }

    public void go2(View v) {


        Intent intent = new Intent();

        intent.setClass(getActivity(),scan.class);

        startActivity(intent);
    }
}
