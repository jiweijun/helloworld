package com.example.socket.linkdatabaseapplication.DB;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.socket.linkdatabaseapplication.R;

import java.util.ArrayList;



public class MyAdapter extends BaseAdapter {
    private ArrayList<ReportNeed> list_data;
    private Context context;
    private ViewHoled viewHold;

    public MyAdapter(ArrayList<ReportNeed> list_data, Context context) {
        this.list_data=list_data;
        this.context=context;
    }

    @Override
    public int getCount() {
        return list_data.size();
    }

    @Override
    public Object getItem(int position) {
        return list_data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       if(convertView==null){
           viewHold= new ViewHoled();
           convertView= View.inflate(context, R.layout.main_item,null);
           viewHold.t1=convertView.findViewById(R.id.t1);
           viewHold.t2=convertView.findViewById(R.id.t2);
           viewHold.t3=convertView.findViewById(R.id.t3);
           viewHold.t4=convertView.findViewById(R.id.t4);
           convertView.setTag(viewHold);
       }else {
           viewHold=(ViewHoled) convertView.getTag();
       }
       //temp,devSerialNumber,ph,rongJy,zhuD,gaoMengSY, cod,bod5,anDan,total_lin,total_dan
        viewHold.t1.setText(list_data.get(position).getReportName()+"");
        viewHold.t2.setText(list_data.get(position).getTiName()+"");
        viewHold.t3.setText(list_data.get(position).getTiTime()+"");
        viewHold.t4.setText(list_data.get(position).getReportNo()+"");
        return convertView;
    }


    static class ViewHoled{
        TextView t1;
        TextView t2;
        TextView t3;
        TextView t4;

    }

}
