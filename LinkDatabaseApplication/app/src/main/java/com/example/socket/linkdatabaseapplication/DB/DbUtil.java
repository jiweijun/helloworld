package com.example.socket.linkdatabaseapplication.DB;


import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;

import com.example.socket.linkdatabaseapplication.MyApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.example.socket.linkdatabaseapplication.MyApplication.getMyApplication;

public class DbUtil {

    private Connection connection;
    public static ArrayList<ReportNeed> typeLocationList;
    private String names,PermissionList,reportName,tiName,tiTime,reportNo;
    private double   temp,devSerialNumber,ph,rongJy,zhuD,gaoMengSY, cod,bod5,anDan,total_lin,total_dan,shiYou,yLSu,yuL,dandao,yingdu,sedu,xiaosuanyan,xuanfuwu;

    //连接数据库
    public Connection getSQLConnection(String ip, String user, String pwd, String db) throws Exception
    {
        Connection con = null;
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");//固定写法
            //一定要在AndroidManifest.xml中加入socket权限，不然会包权限错误
            /*
              <uses-permission android:name="android.permission.INTERNET" />
              <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
              <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
             */
            con = DriverManager.getConnection("jdbc:jtds:sqlserver://" + ip + ":6/" + db + ";charset=utf8", user, pwd);//ip:数据库的IP  db：数据库名字 user:数据库用户名   pwd：用户名对应密码
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return con;
    }
    //登陆
    public String QuerySQL(String name,String pass)
    {
        String result = "";
        try
        {
            //要想连接数据库必须导入相应的包在libs中（jtds-1.2.7.jar和sqljdbc.jar）//222.92.157.106
            connection= getSQLConnection("192.168.1.1","t", "3", "TableDB");//连接数据库，修改为自己的，这个ip地址你们连不上。
            String sql = "select Name from Users_l   where  Name=? and  Password=?"; //查询语句
            PreparedStatement stat = connection.prepareStatement(sql);//得到PreparedStatement对象
            stat.setString(1, name);//给占位符设置上内容
            stat.setString(2, pass);
            ResultSet rs = stat.executeQuery();//执行查询语句
            while (rs.next())//判断是否查询出数据
            {
                result= "1" ;
            }
            rs.close();
            connection.close();
        } catch (Exception e)
        {
            e.printStackTrace();
            result += "查询数据异常!" + e.getMessage();
        }
        return result;
    }
public int ExecSQLDeal(String strSql)
{
   int eN;
   eN=0;
    try
    {
        //要想连接数据库必须导入相应的包在libs中（jtds-1.2.7.jar和sqljdbc.jar）//222.92.157.106
        connection= getSQLConnection("192.168.1.1","t", "3", "TableDB");//连接数据库，修改为自己的，这个ip地址你们连不上。

        PreparedStatement stat = connection.prepareStatement(strSql);//得到PreparedStatement对象
        eN=stat.executeUpdate();
        connection.close();
    } catch (Exception e)
    {
        e.printStackTrace();

    }
   return eN;
}
    /**
     * [获取应用程序版本名称信息]
     * @param context
     * @return 当前应用的版本名称
     */
    public static synchronized String getPackageName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.packageName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//读取数据库二进制文件
    public int readerFile(String strSql) throws SQLException
    {
        try
        {
            //要想连接数据库必须导入相应的包在libs中（jtds-1.2.7.jar和sqljdbc.jar）//222.92.157.106
            connection= getSQLConnection("192.168.1.1","t", "3", "TableDB");//连接数据库，修改为自己的，这个ip地址你们连不上。
                //File file=new File("/data/data/"+getApplicationContext().getFilesDir().getAbsolutePath()+"/files/1.xls");//本地生成的文件


                   // byte[] Buffer = new byte[4096*5];
                    PreparedStatement stat = connection.prepareStatement(strSql);//得到PreparedStatement对象

                    ResultSet rs = stat.executeQuery();//执行查询语句
                    int len = -1;
                    if(rs.next())
                     {
                         //MyApplication i =getPackageName();
                       // File file = new File(getMyApplication().getFilesDir().getPath() + "/1.xls");
                        // File filePath = new File(getMyApplication().getFilesDir(), "files");
                        // File file = new File(getMyApplication().getFilesDir(), "1.xls");
                         File file = new File(Environment.getExternalStorageDirectory(), "1.xls");



                        FileOutputStream outputStream = new FileOutputStream(file);
                        //FileOutputStream outputStream =openFileOutput("1.xls",Context.MODE_PRIVATE);
                        InputStream iStream = rs.getBinaryStream("数据");//去字段用getBinaryStream()


                        while((len = iStream.read())!=-1){
                            outputStream.write(len);
                        }
                        outputStream.flush();
                        outputStream.close();
                    }
                    rs.close();
                    connection.close();
                    return len;
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0;
                }



    }

    //查询数据
      public int getTypesLocation(String name){
        typeLocationList=new ArrayList<ReportNeed>();//存放查询出的数据
        synchronized (this){
            try {

                connection= getSQLConnection("192.168.1.1","t", "3", "TableDB");//连接数据库，修改为自己的，这个ip地址你们连不上。

                String sql = "select 报告名称,提交人,提交时间,报告编号 from lims_table where 状态 = '已提交' and 复核人 = '" + name + "'"; //查询语句
                PreparedStatement preparedStatement=connection.prepareStatement(sql);
                ResultSet resultSet=preparedStatement.executeQuery();

                //判断是否有下一个
                while (resultSet.next()){
                    //通过字段得到数值
                    reportName=resultSet.getString("报告名称");//报告名称
                    tiName=resultSet.getString("提交人");//提供人
                    tiTime=resultSet.getString("提交时间");//提交时间
                    reportNo=resultSet.getString("报告编号");//提交时间
                   /* devSerialNumber=resultSet.getFloat("DevSerialNumber");//设备号

                    time=resultSet.getString("time");//时间
                    temp=resultSet.getDouble("temp");//温度
                    ph=resultSet.getDouble("ph");//ph
                    rongJy=resultSet.getDouble("rongjieyang");//溶解氧
                    dandao=resultSet.getDouble("dandao");//溶解氧
                    zhuD=resultSet.getDouble("zhudu");//浊度
                    //高猛酸盐
                    gaoMengSY=resultSet.getDouble("gaomengsy");//orp
                    cod=resultSet.getDouble("cod");//cod
                    //BOD5
                    bod5=resultSet.getDouble("bod");//电导
                    anDan=resultSet.getDouble("andan");//氨氮
                    //总磷
                    total_lin=resultSet.getDouble("alllin");//电导2
                    //总氮
                    total_dan=resultSet.getDouble("alldan");//盐度
                    yuL=resultSet.getDouble("yulv");//余氯
                    //硬度
                    yingdu=resultSet.getDouble("yingdu");
                    //色度
                    sedu=resultSet.getDouble("sedu");
                    //硝酸盐
                    xiaosuanyan=resultSet.getDouble("xiaosuanyan");
                    //悬浮物
                    xuanfuwu=resultSet.getDouble("xuanfuwu");
                    //石油类
                    shiYou=resultSet.getDouble("shiyou");
                    yLSu=resultSet.getDouble("ylsu");
                    */


                    //将得到的数据存放到类中，然后存放到List中
                    typeLocationList.add(new ReportNeed(reportName,tiName,tiTime,reportNo));


                }
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return typeLocationList.size();
        }
      }

}
