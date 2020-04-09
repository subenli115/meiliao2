package com.ziran.meiliao.ui.main.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.citypicker.citylist.sortlistview.SortModel;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.DisplayUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.permission.PermissionUtil;
import com.ziran.meiliao.ui.base.PermissionActivity;
import com.ziran.meiliao.ui.main.fragment.LoginFragment;
import com.ziran.meiliao.ui.main.fragment.RegisterFragment;
import com.umeng.socialize.UMShareAPI;

import butterknife.Bind;

/**
 * 登录界面
 * Created by Administrator on 2016/12/26.
 */

public class LoginActivity extends PermissionActivity implements RadioGroup.OnCheckedChangeListener {
    //登录Fragment
    LoginFragment loginFragment;
    //注册Fragment
    RegisterFragment registerFragment;
    //切换登录与注册的RadioGroup
    @Bind(R.id.rg_login)
    RadioGroup radioGroup;
    @Bind(R.id.iv_login_bg)
    ImageView iv_bg;
    public String platName;
    private LoginFragment.ThreeLoginBean mThreeLoginBean;

    public String jsonData;

    public static void startAction(Context mContext) {
        try{
            Intent intent = new Intent(mContext, NewLoginActivity.class);
            mContext.startActivity(intent);
        }catch(Exception e){
        }
    }


    @Override
    public int getLayoutId() {
        return R.layout.act_login;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    public void initView() {
        radioGroup.setOnCheckedChangeListener(this);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        loginFragment = LoginFragment.newInstance(null, null);
        ft.add(R.id.fl_login_container, loginFragment, "login");
        ft.commit();
        setPermission(PermissionUtil.getSMS());
        DisplayUtil.measureSoftKeyBoardHeight(this);
    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_register:
                showRegisterFragment(mThreeLoginBean);
                break;
            case R.id.rb_login:
                showLoginFragment();
                iv_bg.setImageResource(R.mipmap.login_bg);
                break;
        }
    }

    public void showRegisterFragment(LoginFragment.ThreeLoginBean flag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.hide(loginFragment);
        if (registerFragment == null) {
            registerFragment = RegisterFragment.newInstance(null, null);
            ft.add(R.id.fl_login_container, registerFragment, "register");
        } else {
            ft.show(registerFragment);
        }
        if (EmptyUtils.isNotEmpty(mThreeLoginBean)) {
            registerFragment.setJsonData(flag);
            mThreeLoginBean = null;
        }
        iv_bg.setImageResource(R.mipmap.register_bg);
        ft.commit();
    }

    public void showLoginFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (registerFragment != null) {
            ft.hide(registerFragment);
        }
        ft.show(loginFragment);
        ft.commit();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyAPP.setReqPerOk(false);
        UMShareAPI.get(this).release();
    }

    public String getPlatName() {
        return platName;
    }

    public void setPlatName(String platName) {
        this.platName = platName;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    //从区号选择页面返回数据处理
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (null != data) {
            SortModel cityData = data.getParcelableExtra("cityData");
            if (EmptyUtils.isNotEmpty(cityData)) {
                loginFragment.setAreaCode(cityData);
                if (registerFragment != null) {
                    registerFragment.setAreaCode(cityData);
                }
            } else {
                UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (MyAPP.isLogout) {
//            MainActivity.startAction(this);
            finish();
        } else {
            super.onBackPressed();
        }
    }
}
