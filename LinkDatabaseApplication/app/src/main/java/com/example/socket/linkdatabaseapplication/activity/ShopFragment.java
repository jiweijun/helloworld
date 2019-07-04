package com.example.socket.linkdatabaseapplication.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.socket.linkdatabaseapplication.mpchart.ChartActivity;
import com.example.socket.linkdatabaseapplication.R;


public class ShopFragment extends BaseFragment {

    private Button btn;
    View view;
    public ShopFragment() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_shop, container, false);
        btn = (Button) view.findViewById(R.id.button4);
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

                intent.setClass(getActivity(), ChartActivity.class);

                startActivity(intent);
            }
        });
    }



}
