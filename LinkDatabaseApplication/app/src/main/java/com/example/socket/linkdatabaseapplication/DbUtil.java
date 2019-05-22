package com.example.socket.linkdatabaseapplication;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbUtil {

    private Connection connection;
    public static ArrayList<EnviromentHomeClass> typeLocationList;
    private String types,location,time;
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
            con = DriverManager.getConnection("jdbc:jtds:sqlserver://" + ip + ":1433/" + db + ";charset=utf8", user, pwd);//ip:数据库的IP  db：数据库名字 user:数据库用户名   pwd：用户名对应密码
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
            //要想连接数据库必须导入相应的包在libs中（jtds-1.2.7.jar和sqljdbc.jar）
            connection= getSQLConnection("192.168.1.161","sa", "123456sa\\", "TableDB");//连接数据库，修改为自己的，这个ip地址你们连不上。
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

    //查询数据
      public int getTypesLocation(){
        typeLocationList=new ArrayList<EnviromentHomeClass>();//存放查询出的数据
        synchronized (this){
            try {

                connection=getSQLConnection("192.168.1.108", "sa", "Chemins0000", "MonitorSystem");

                String sql="select a.types,a.location,a.DevSerialNumber, \n" +
                        "               d.time, d.temp , d.ph, d.rongjieyang,d.dandao, d.zhudu,d.gaomengsy ,d.cod ,d.bod , \n" +
                        "               d.andan , d.alllin , d.alldan, d.yulv ,d.yingdu , d.sedu ,d.xiaosuanyan ,d.xuanfuwu , d.shiyou ,d.ylsu \n" +
                        "from DevInfo a\n" +
                        "       inner join (\n" +
                        "       select  r.Time, r.DevSerialNumber,r.temp , r.ph, r.rongjieyang,r.dandao, r.zhudu,r.gaomengsy ,r.cod ,r.bod , \n" +
                        "               r.andan , r.alllin , r.alldan, r.yulv ,r.yingdu , r.sedu ,r.xiaosuanyan ,r.xuanfuwu , r.shiyou ,r.ylsu \n" +
                        "             from Simulation_Data r \n" +
                        "             inner join (\n" +
                        "                          select DevSerialNumber, max(Time) as time\n" +
                        "                          from Simulation_Data  group by DevSerialNumber\n" +
                        "                        )b\n" +
                        "             on r.Time= b.Time and  r.DevSerialNumber = b.DevSerialNumber\n" +
                        "       )d  on a.DevSerialNumber=d.DevSerialNumber;\n";
                PreparedStatement preparedStatement=connection.prepareStatement(sql);
                ResultSet resultSet=preparedStatement.executeQuery();

                //判断是否有下一个
                while (resultSet.next()){
                    //通过字段得到数值
                    types=resultSet.getString("types");//类型
                    location=resultSet.getString("location");//地址
                    devSerialNumber=resultSet.getFloat("DevSerialNumber");//设备号

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


                    //将得到的数据存放到类中，然后存放到List中
                    typeLocationList.add(new EnviromentHomeClass(types,location,devSerialNumber,time, temp,ph, rongJy, dandao, zhuD,
                                        gaoMengSY, cod,  bod5, anDan, total_lin,total_dan,  yuL,yingdu, sedu, xiaosuanyan, xuanfuwu,  shiYou, yLSu) );

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return typeLocationList.size();
        }
      }

}
