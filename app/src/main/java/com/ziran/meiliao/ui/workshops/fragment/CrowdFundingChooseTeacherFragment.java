package com.ziran.meiliao.ui.workshops.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.commonwidget.LoadingTip;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.SearchItemBean;
import com.ziran.meiliao.ui.bean.TeacherLibraryBean;
import com.ziran.meiliao.ui.workshops.adapter.CrowdFundingChooseTopicAdapter;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.TopSearchBarView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 减压馆-分类Fragment
 * Created by Administrator on 2017/1/7.
 */

public class CrowdFundingChooseTeacherFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract
        .View<TeacherLibraryBean>, AppConstant.SearchId {
    @Bind(R.id.topSearchBar)
    TopSearchBarView mTopSearchBarView;

    @Bind(R.id.ll_filter_container)
    View llFilterContainer;
    @Bind(R.id.tv_filter)
    TextView tvFilter;
    private String courseId = "";

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_main_choose_common;
    }
    private boolean canClose;
    private Map<String, String> defMap;
    String text;

    @Override
    protected void initOther() {
        setLoadedTipState(LoadingTip.LoadStatus.empty);

        defMap = MapUtils.getDefMap(true);
        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra(AppConstant.ExtraKey.FROM_ID)) {
            courseId = intent.getStringExtra(AppConstant.ExtraKey.FROM_ID);
            if (!TextUtils.isEmpty(courseId)) {
                canClose = intent.getBooleanExtra(AppConstant.ExtraKey.FROM_CAN_CLOSE,false);
                text = intent.getStringExtra(AppConstant.ExtraKey.FROM_CONDITION);
                tvFilter.setText(text);
                llFilterContainer.setVisibility(View.VISIBLE);
                defMap.put("courseId", courseId);
                defMap.put("condition", text);
            }

        }
        defMap.put("pageSize", "10");
        mTopSearchBarView.setOnSearchListener(new TopSearchBarView.onSearchListener() {
            @Override
            public void search(String newValue) {
                onRefresh();
            }
        });
    }

    private void searchText() {
        defMap.put("page", String.valueOf(page));
        String content = mTopSearchBarView.getContent();
        content = EmptyUtils.isEmpty(content) ? EmptyUtils.isNotEmpty(courseId) ? text : "" : content;
        defMap.put("condition", content);
        mPresenter.postData(ApiKey.CROWN_FUND_SEARCH_CFQCOURSE_TEACHERS, defMap, TeacherLibraryBean.class);
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        loadedTip.setEmptyMsg("暂无内容", R.mipmap.ic_empty_nocontent);
        return new CrowdFundingChooseTopicAdapter(getContext(), AppConstant.SearchId.RESULT_TEACHER);
    }

    @Override
    protected void loadData() {
        searchText();
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        if (mAdapter.getItemViewType(position) == RESULT_TEACHER) {
            RxManagerUtil.post(AppConstant.RXTag.CROWD_FUNDING_CHOOSE_DATA, HandlerUtil.obj(WHAT_TEACHER, o));
            finish();
        }
    }

    @Override
    public void returnData(TeacherLibraryBean result) {
        List<SearchItemBean> list = new ArrayList<>();
        boolean loadMore = true;
        if (EmptyUtils.isNotEmpty(result.getData().getDataList())) {
            list.addAll(result.getData().getDataList());
        } else {
            list.add(SearchItemBean.empty());
            loadMore = false;
        }
        if (EmptyUtils.isNotEmpty(result.getData().getRecList())) {
            list.add(SearchItemBean.xiangGuan());
            list.addAll(result.getData().getRecList());
        }
        updateData(list);
        if (!loadMore) {
            iRecyclerView.setLoadMoreEnabled(false);
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        mTopSearchBarView.hideKeyBoard();
    }

    @OnClick(R.id.iv_clear_filter)
    public void onViewClicked() {
        if (canClose) return;
        courseId = "";
        llFilterContainer.setVisibility(View.GONE);
        RxManagerUtil.post(AppConstant.ExtraKey.CLEAR_FILTER, "course");
        defMap.remove("courseId");
    }

}
