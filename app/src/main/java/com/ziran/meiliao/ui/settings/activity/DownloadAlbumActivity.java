package com.ziran.meiliao.ui.settings.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.entry.MusicEntry;
import com.ziran.meiliao.ui.adapter.OneSlideAdapter;
import com.ziran.meiliao.ui.settings.fragment.DownLoadMusicFragment;
import com.ziran.meiliao.utils.MusicPanelFloatManager;
import com.ziran.meiliao.utils.StringUtils;
import com.ziran.meiliao.widget.CustomMusicPanelNewView;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * 优惠劵界面
 * Created by Administrator on 2017/1/4.
 */

public class  DownloadAlbumActivity extends BaseActivity {
    @Bind(R.id.ntb)
    public NormalTitleBar ntb;
    @Bind(R.id.linearLayout)
    public View linearLayout;
    @Bind(R.id.btnSelect)
    public Button btnSelect;
    @Bind(R.id.musicPanelView)
    CustomMusicPanelNewView mMusicPanelView;

    private com.ziran.meiliao.utils.AnimationUtil animationUtil1;
    private DownLoadMusicFragment loadMusicFragment;

    public static void startAction(Context mContext, String title, String albumId) {
        Intent intent = new Intent(mContext, DownloadAlbumActivity.class);
        intent.putExtra(AppConstant.ExtraKey.VIDEO_TITLE, title);
        intent.putExtra(AppConstant.SPKey.ALBUM_ID, albumId);
        mContext.startActivity(intent);
    }

    private static final int NORMAL = 0;
    private static final int CANCEL = 1;
    public int currState = NORMAL;

    @Override
    public int getLayoutId() {
        return R.layout.activity_single_delete;
    }

    @Override
    public void initPresenter() {

    }
    @Override
    protected void onResume() {
        super.onResume();
        MusicPanelFloatManager.getInstance().bindView(mMusicPanelView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MusicPanelFloatManager.getInstance().unBindView(mMusicPanelView);
    }
    @Override
    public void initView() {
        String title = getIntentExtra(getIntent(), AppConstant.ExtraKey.VIDEO_TITLE);
        ntb.setTvLeftVisiable(true, true);
        ntb.setTitleText(title);
        ntb.setRightTitle(StringUtils.getText(R.string.delete_act_edit));
        loadMusicFragment = new DownLoadMusicFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(AppConstant.ExtraKey.FROM_TYPE, OneSlideAdapter.FROM_ALBUM_MUSIC);
        initFragment(loadMusicFragment,bundle);

        ntb.setOnRightTextListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeState();
            }
        });
        mRxManager.on(AppConstant.RXTag.UPDATE_SEL, new Action1<Integer>() {
            @Override
            public void call(Integer count) {
                String string = getString(R.string.delete_act_sellect);
                String text = count == 0 ? string : (string + " (" + count + " )");
                btnSelect.setText(text);
            }
        });
        mRxManager.on("close", new Action1<Object>() {
            @Override
            public void call(Object o) {
                btnSelect.setText(R.string.delete_act_sellect);
                ntb.setRightTitle(getString(R.string.delete_act_edit));
                currState = NORMAL;
                linearLayout.setAnimation(animationUtil1.moveToViewBottom());
                loadMusicFragment.setCheckAndUpdate(false);
            }
        });
        if (animationUtil1 == null) {
            animationUtil1 = new com.ziran.meiliao.utils.AnimationUtil(linearLayout);
        }
    }
    public void setVisit(String name, String duration, String url, MusicEntry musicEntry) {

        mMusicPanelView.setTvMusicPanelTitle(name);
        mMusicPanelView.setTvMusicPanelDuration(duration);
        mMusicPanelView.setIvMusicPanelCover(url);
        mMusicPanelView.setVisibility(View.VISIBLE);
        mMusicPanelView.updatePlayState();
        MusicPanelFloatManager.getInstance().setMusicPrepare(musicEntry);
        MusicPanelFloatManager.getInstance().bindView(mMusicPanelView);
    }
    public void changeState() {
        linearLayout.clearAnimation();
        if (currState == NORMAL) {
            currState = CANCEL;
            linearLayout.setAnimation(animationUtil1.moveToViewLocation());
        } else {
            btnSelect.setText(R.string.delete_act_sellect);
            currState = NORMAL;
            linearLayout.setAnimation(animationUtil1.moveToViewBottom());
        }
        ntb.setRightToggle();
        loadMusicFragment.toggle();
    }

    @OnClick({R.id.btnSelect, R.id.btnDelete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSelect:
                loadMusicFragment.selectAll();
                break;
            case R.id.btnDelete:
                loadMusicFragment.delete("删除", "音频");
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if ("取消".equals(ntb.getRightText())) {
            mRxManager.post(AppConstant.RXTag.CLOSE, 1);
        } else {
            super.onBackPressed();
        }
    }
}
