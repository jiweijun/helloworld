package com.example.socket.linkdatabaseapplication.activity;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableRow;

import com.example.socket.linkdatabaseapplication.R;


public class MineFragment extends BaseFragment {

    private View view;
    private Button btn;
    private TableRow row_exit;

    public MineFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.fragment_mine,container,false);
        row_exit = (TableRow) view.findViewById(R.id.more_page_row3);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);


        row_exit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //实例化SharedPreferences对象（第一步）
                SharedPreferences mySharedPreferences= getActivity().getSharedPreferences("test",
                        Activity.MODE_PRIVATE);
                //实例化SharedPreferences.Editor对象（第二步）
                SharedPreferences.Editor editor = mySharedPreferences.edit();
                //用putString的方法保存数据
                //editor.putString("name", user_tv);
                editor.putString("pass", "");
                //提交当前数据
                editor.commit();

                Intent intent = new Intent();

                intent.setClass(getActivity(),  LoginActivity.class);
                startActivity(intent);

                getActivity().finish();
            }
        });
    }
}
