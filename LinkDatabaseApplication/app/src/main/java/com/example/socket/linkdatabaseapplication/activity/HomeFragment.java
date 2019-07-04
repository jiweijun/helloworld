package com.example.socket.linkdatabaseapplication.activity;


import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.SharedPreferences;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.socket.linkdatabaseapplication.DB.DbUtil;
import com.example.socket.linkdatabaseapplication.DB.MyAdapter;
import com.example.socket.linkdatabaseapplication.DownloadUtils;
import com.example.socket.linkdatabaseapplication.R;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static android.support.v4.content.FileProvider.getUriForFile;
import static com.example.socket.linkdatabaseapplication.DB.DbUtil.typeLocationList;
import static com.example.socket.linkdatabaseapplication.DownloadUtils.getFileIntent;
import static com.example.socket.linkdatabaseapplication.MyApplication.getMyApplication;


public class HomeFragment extends BaseFragment {
    private ListView list;
    private final int GETDATA = 0;
    private Button btn;
    private View view;
    private String user_tv;
    SimpleAdapter adapter;
    public int MID;
    private String reportNo;
    private String strSQL;
    private String strSQL2;
    private String strSQL3;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GETDATA:
                    //list.setAdapter(null);
                    list.setAdapter(new MyAdapter(typeLocationList, getActivity()));//设置数据到ListView
                    break;
            }
        }
    };

    public HomeFragment() {
        // init();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
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

        list = (ListView) view.findViewById(R.id.list);
        // 列表现的单机事件
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long id) {
                /*
                 * 点击列表项时触发onItemClick方法，四个参数含义分别为
                 * arg0：发生单击事件的AdapterView
                 * arg1：AdapterView中被点击的View
                 * position：当前点击的行在adapter的下标
                 * id：当前点击的行的id
                 */

                //获得选中项的HashMap对象
                TextView c = (TextView) arg1.findViewById(R.id.t4);
                reportNo = c.getText().toString().trim();


                Toast.makeText(getActivity(),
                        "您选择的是" + reportNo,
                        Toast.LENGTH_SHORT).show();


            }

        });

        /**
         * Item 长按方式弹出菜单多选方式1
         * Item 长按方式弹出菜单多选方式2
         * ItemOnLongClick1()与ItemOnLongClick2()不共存，按实际需要选择
         */
        //        ItemOnLongClick1();
        ItemOnLongClick1();

    }

    private void ItemOnLongClick1() {
//注：setOnCreateContextMenuListener是与下面onContextItemSelected配套使用的
        list.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {

            public void onCreateContextMenu(ContextMenu menu, View v,
                                            ContextMenu.ContextMenuInfo menuInfo) {
                menu.add(0, 0, 0, "查看报告");
                menu.add(0, 1, 0, "复核通过");
                menu.add(0, 2, 0, "报告退回");

            }
        });
    }

    // 长按菜单响应函数
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
                .getMenuInfo();
        MID = (int) info.id;// 这里的info.id对应的就是数据库中_id的值

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);

        switch (item.getItemId()) {
            case 0:
                // 添加操作
                Toast.makeText(getActivity(),
                        "查看报告",
                        Toast.LENGTH_SHORT).show();

                strSQL = "select 数据 from Lims_Table where 报告编号 = '" + reportNo + "'";
                new ReportReadFile().start();
                //new RFile().start();

                break;

            case 1:
                // 删除操作



                strSQL3 = "update Lims_Table set 状态 = '已复核',复核人= '" + user_tv + "',复核时间= '" + str + "'where 报告编号 = '" + reportNo + "'";
                //new ReportDataThread().start();
                strSQL = "insert into lims_his(报告编号,报告名称,数据,提交人,复核人,状态,原因,提交时间,复核时间,拒绝时间,创建时间) select 报告编号,报告名称,数据,提交人,复核人,状态,原因,提交时间,复核时间,拒绝时间,创建时间 from lims_table where 报告编号 = '" + reportNo + "'";
                //new ReportDataThread().start();
                // 添加操作
                Toast.makeText(getActivity(),
                        "复核通过",
                        Toast.LENGTH_SHORT).show();
                // '删除原有记录
                strSQL2 = "delete from Lims_Table where 报告编号  = '" + reportNo + "'";
                new CommandTwoThread().start();





                break;

            case 2:
                Toast.makeText(getActivity(),
                        "报告退回",
                        Toast.LENGTH_SHORT).show();


                strSQL = "update Lims_Table set 状态 = '已退回',原因= '" + " " + "',复核人= '" + user_tv + "',复核时间= '" + str + "'where 报告编号 = '" + reportNo + "'";
                new ReportDataThread().start();
                break;

            default:
                break;
        }

        return super.onContextItemSelected(item);

    }



    //----------------------------去数据库加载数据-------------------------
    private class DownloadDataThread extends Thread {
        @Override
        public void run() {

            DbUtil dbUtil = new DbUtil();
            //同样，在读取SharedPreferences数据前要实例化出一个SharedPreferences对象
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("test",
                    Activity.MODE_PRIVATE);
            // 使用getString方法获得value，注意第2个参数是value的默认值
            user_tv = sharedPreferences.getString("name", "");
            user_tv = "季卫军";
            int size = dbUtil.getTypesLocation(user_tv);
            //if(size>0){//查询得到结果
            Message message = Message.obtain();
            message.what = GETDATA;
            handler.sendMessage(message);//将结果通知主线程，处理结果
            //}
        }
    }

    private class ReportDataThread extends Thread {
        @Override
        public void run() {
            DbUtil dbUtil = new DbUtil();
            dbUtil.ExecSQLDeal(strSQL);

            Message message = Message.obtain();
            message.what = 1;
            handler.sendMessage(message);//将结果通知主线程，处理结果

        }
    }

    private class CommandTwoThread extends Thread {
        @Override
        public void run() {
            DbUtil dbUtil = new DbUtil();
            dbUtil.ExecSQLDeal(strSQL3);
            dbUtil.ExecSQLDeal(strSQL);
            dbUtil.ExecSQLDeal(strSQL2);
            Message message = Message.obtain();
            message.what = 1;
            handler.sendMessage(message);//将结果通知主线程，处理结果

        }
    }

    private class ReportReadFile extends Thread {
        @Override
        public void run() {
            DbUtil dbUtil = new DbUtil();
            int u = 0;
            try {
                u = dbUtil.readerFile(strSQL);
            } catch (SQLException e) {
                e.printStackTrace();
            }
           // File newFile = new File(getMyApplication().getFilesDir(), "1.xls");
            File newFile = new File(Environment.getExternalStorageDirectory(), "1.xls");
            //Uri contentUri = getUriForFile(getContext(), "com.mydomain.fileprovider", newFile);

            startActivity( getFileIntent(getActivity() , newFile,getMyApplication().getFilesDir().getPath() + "/1.xls"));

            Message message = Message.obtain();
            if (u == 0) {
                message.what = 2;
            } else {
                message.what = 3;
            }

            handler.sendMessage(message);//将结果通知主线程，处理结果

        }
    }
    private class RFile extends Thread {
        @Override
        public void run() {
            //File file = new File(getMyApplication().getFilesDir().getPath() + "/1.xls");
           // File filePath = new File(getMyApplication().getFilesDir(), "files");
            File newFile = new File(getMyApplication().getFilesDir(), "1.xls");
            //Uri contentUri = getUriForFile(getContext(), "com.mydomain.fileprovider", newFile);

            startActivity( getFileIntent(getActivity() , newFile,getMyApplication().getFilesDir().getPath() + "/1.xls"));
        }
    }
    private void openfile(String filename) {
        // DownloadUtils d = new DownloadUtils();
        //获取文件的类型
        String filetype = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
        if ("jpg".equals(filetype) || "jpeg".equals(filetype) || "bmp".equals(filetype) || "gif".equals(filetype) || "png".equals(filetype)) {
            Toast.makeText(getActivity(), "文件类型错误", Toast.LENGTH_LONG).show();
        } else {
            //检测是否安装了wps软件，没有安装则去下载
            if (isAvilible(getActivity(), "cn.wps.moffice_eng")) {
                //先查看本地是否存在此文件。存在就立即访问。否则再去下载
                // 获得存储卡的路径

                    //返回真实路径
                    String mSavePath ="1"; //d.downloadFile(filename);
                    if (mSavePath != "") {
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putString("OpenMode", "ReadOnly");// 只读模式
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setAction(android.content.Intent.ACTION_VIEW);
                        intent.setClassName("cn.wps.moffice_eng",
                                "cn.wps.moffice.documentmanager.PreStartActivity2");
                      /*  Uri uri = null ;
                        //uri = Uri.fromFile(new File(mSavePath + URLDecoder.decode(filename, "UTF-8").substring(filename.lastIndexOf("/") + 1, filename.length())));
                        uri = Uri.fromFile(new File(getMyApplication().getFilesDir().getPath() + "1.xls"));
                        ;
                        Log.e("wps访问的uri", uri + "");
                        intent.setData(uri);
                        intent.putExtras(bundle);
                        try {
                            startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                            e.printStackTrace();
                        }
*/
                }
            } else {
                Toast.makeText(getActivity(),
                        "没有安装WPS",
                        Toast.LENGTH_SHORT).show();
            }
        }

    }




    private boolean isAvilible( Context context, String packageName )
    {
        final PackageManager packageManager = context.getPackageManager();
        // 获取所有已安装程序的包信息
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        for ( int i = 0; i < pinfo.size(); i++ )
        {
            if(pinfo.get(i).packageName.equalsIgnoreCase(packageName))
                return true;
        }
        return false;
    }


}


