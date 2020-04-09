package com.ziran.meiliao.ui.workshops.fragment;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.ui.base.CommonHttpFragment;
import com.ziran.meiliao.ui.workshops.activity.TeamCustomActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 内容:团建定制
 * Created by Administrator on 2017/1/7.
 */

public class GuideTeamCustomFragment extends CommonHttpFragment {


    @Bind(R.id.ntb)
    NormalTitleBar ntb;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_guide_team_custom;
    }


    @OnClick(R.id.iv_guide_team)
    public void onViewClicked() {
        startActivity(TeamCustomActivity.class);
    }
}
