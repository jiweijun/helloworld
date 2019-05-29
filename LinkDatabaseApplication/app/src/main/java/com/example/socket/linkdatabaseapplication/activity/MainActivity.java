package com.example.socket.linkdatabaseapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.example.socket.linkdatabaseapplication.R;
import com.example.socket.linkdatabaseapplication.scan;


public class MainActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private RadioGroup radioGroup;
    private Fragment[] mFragments;
    private int mIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
        setIndexSelected(0);
        setRadioGroupListener();


    }

    private void initFragment() {
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        frameLayout = (FrameLayout) findViewById(R.id.fl_content);
        HomeFragment homeFragment = new HomeFragment();
        ShopFragment shopFragment = new ShopFragment();
        LiveFragment liveFragment = new LiveFragment();
        ShoppingCarFragment shoppingCarFragment = new ShoppingCarFragment();
        MineFragment mineFragment = new MineFragment();
        //添加到数组
        mFragments = new Fragment[]{homeFragment, shopFragment, liveFragment, shoppingCarFragment, mineFragment};
        //开启事务
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //添加首页
        ft.add(R.id.fl_content, homeFragment).commit();
        //默认设置为第0个
        setIndexSelected(0);
    }

    private void setIndexSelected(int index) {
        if (mIndex == index) {
            return;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        //隐藏
        ft.hide(mFragments[mIndex]);
        //判断是否添加
        if (!mFragments[index].isAdded()) {
            ft.add(R.id.fl_content, mFragments[index]).show(mFragments[index]);
        } else {
            ft.show(mFragments[index]);
        }
        ft.commit();
        //再次赋值
        mIndex = index;

    }

    private void setRadioGroupListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_home:
                        setIndexSelected(0);
                        break;
                    case R.id.rb_shop:
                        setIndexSelected(1);
                        break;
                    case R.id.rb_live:
                        setIndexSelected(2);
                        break;
                    case R.id.rb_shopping_car:
                        setIndexSelected(3);
                        break;
                    case R.id.rb_mine:
                        setIndexSelected(4);
                        break;
                    default:
                        setIndexSelected(0);
                        break;
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //仅当activity为task根（即首个启动activity）时才生效,这个方法不会改变task中的activity状态，
            // 按下返回键的作用跟按下HOME效果一样；重新点击应用还是回到应用退出前的状态；
            moveTaskToBack(false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void go2(View v) {


        Intent intent = new Intent();

        intent.setClass(MainActivity.this,scan.class);

        startActivity(intent);
    }
}
