package com.ziran.meiliao.im.activity;


import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.ziran.meiliao.R;
import com.ziran.meiliao.im.controller.SendFileController;
import com.ziran.meiliao.im.view.SendFileView;


public class SendFileActivity extends FragmentActivity {

    private SendFileView mView;
    private SendFileController mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_file);
        mView = (SendFileView) findViewById(R.id.send_file_view);
        mView.initModule();
        mController = new SendFileController(this, mView);
        mView.setOnClickListener(mController);
        mView.setOnPageChangeListener(mController);

        //设置文件选择界面viewpager能左右滑动..在 会话 通讯录 我  主界面是不能左右滑动的
        mView.setScroll(true);
    }

    public FragmentManager getSupportFragmentManger() {
        // TODO Auto-generated method stub
        return getSupportFragmentManager();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mView.setScroll(false);
    }
}
