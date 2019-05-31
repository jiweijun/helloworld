package com.example.socket.linkdatabaseapplication.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.socket.linkdatabaseapplication.DbUtil;
import com.example.socket.linkdatabaseapplication.MyAdapter;
import com.example.socket.linkdatabaseapplication.R;
import com.example.socket.linkdatabaseapplication.scan;
import android.support.v4.app.Fragment;

import static com.example.socket.linkdatabaseapplication.DbUtil.typeLocationList;


public class HomeFragment extends BaseFragment {
    private ListView list;
    private final int GETDATA=0;
    private Button btn;
    private View view;


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case GETDATA:
                    list.setAdapter(new MyAdapter(typeLocationList,getActivity()));//设置数据到ListView
                    break;
            }
        }
    };

    public HomeFragment() {
       // init();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_home,container,false);
        btn = (Button) view.findViewById(R.id.button2);
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
                init();
                new DownloadDataThread().start();//开启线程
            }
        });
    }


    public void go3(View v) {
      init();
      new DownloadDataThread().start();//开启线程
    }


    private void init() {
        list=(ListView) view.findViewById(R.id.list);

    }
    //----------------------------去数据库加载数据-------------------------
    private class DownloadDataThread extends Thread{
        @Override
        public void run() {
            DbUtil dbUtil=new DbUtil();
            int size= dbUtil.getTypesLocation();
            if(size>0){//查询得到结果
                Message message=Message.obtain();
                message.what=GETDATA;
                handler.sendMessage(message);//将结果通知主线程，处理结果
            }
        }
    }

}
