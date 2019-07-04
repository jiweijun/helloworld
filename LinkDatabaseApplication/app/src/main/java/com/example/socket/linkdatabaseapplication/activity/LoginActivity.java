package com.example.socket.linkdatabaseapplication.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.socket.linkdatabaseapplication.DB.DbUtil;
import com.example.socket.linkdatabaseapplication.R;

import android.content.SharedPreferences;

/*
 ---------------------------------------------登陆页面-------------------------------
 */
public class LoginActivity extends Activity implements View.OnClickListener{

    private EditText user,password;
    private Button cancel,login;
    private Context context;
    private String user_tv, password_tv;
   // private Handler handler;
  private Bundle b=new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
       // WorkThread wt=new WorkThread();
       // wt.start();//调动子线程
        AutoLogin();
    }

    private void init() {
        context =LoginActivity.this;
       user= (EditText)findViewById(R.id.user);
       password= (EditText)findViewById(R.id.password);
       cancel= (Button)findViewById(R.id.cancel);
       login= (Button)findViewById(R.id.login);
        cancel.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel:
                user.setText("");
                password.setText("");
                Toast.makeText(context,"点击了取消按钮",0).show();
                break;
            case R.id.login:
                user_tv=user.getText().toString().trim();
                password_tv=password.getText().toString().trim();
                Message m=handler.obtainMessage();//获取事件
               // Bundle b=new Bundle();
                b.putString("name",user_tv);
                b.putString("pass",password_tv);//以键值对形式放进 Bundle中
               // m.setData(b);
               // m.what=0;
               // handler.sendMessage(m);//把信息放到通道中

                new WorkThread().start();//开启线程

                //实例化SharedPreferences对象（第一步）
                SharedPreferences mySharedPreferences= getSharedPreferences("test",
                        Activity.MODE_PRIVATE);
                //实例化SharedPreferences.Editor对象（第二步）
                SharedPreferences.Editor editor = mySharedPreferences.edit();
                //用putString的方法保存数据
                editor.putString("name", user_tv);
                editor.putString("pass", password_tv);
                //提交当前数据
                editor.commit();


                break;
        }
    }

    private void AutoLogin()
    {
        //同样，在读取SharedPreferences数据前要实例化出一个SharedPreferences对象
        SharedPreferences sharedPreferences= getSharedPreferences("test",
                Activity.MODE_PRIVATE);
        // 使用getString方法获得value，注意第2个参数是value的默认值
        user_tv =sharedPreferences.getString("name", "");
        password_tv =sharedPreferences.getString("pass", "");
        Message m=handler.obtainMessage();//获取事件
        //Bundle b=new Bundle();
        b.putString("name",user_tv);
        b.putString("pass",password_tv);//以键值对形式放进 Bundle中
        //m.setData(b);
        //m.what=0;
       // handler.sendMessage(m);//把信息放到通道中

        new WorkThread().start();//开启线程
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message m) {
            switch (m.what) {
                case 0:

                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();//显示提示框
                        break;
                case 1:

                    Toast.makeText(LoginActivity.this, "错误", Toast.LENGTH_SHORT).show();
                    break;

                    }


            }
    };


    class WorkThread extends  Thread{
        @Override
        public  void run(){
           // Bundle b = m.getData();//得到与信息对用的Bundle
            String name = b.getString("name");//根据键取值
            String pass = b.getString("pass");
            DbUtil db = new DbUtil();//调用数据库查询类
            String ret = db.QuerySQL(name, pass);//得到返回值
            if (ret.equals("1"))//为1，页面跳转，登陆成功
            {

                //startActivity(new Intent(LoginActivity.this,MainActivity.class));
               // Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();//显示提示框
                //return;

                Message message=Message.obtain();
                message.what=0;
                handler.sendMessage(message);//将结果通知主线程，处理结果
            }
            else
            {
                Message message=Message.obtain();
                message.what=1;
                handler.sendMessage(message);//将结果通知主线程，处理结果

           // Toast.makeText(LoginActivity.this, "错误", Toast.LENGTH_SHORT).show();
            }
        }
    }




}
