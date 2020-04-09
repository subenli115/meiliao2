package com.ziran.meiliao.ui.workshops.contract;

import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.ui.base.CommonModel;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * des:图片列表contract
 * Created by xsf
 * on 2016.09.14:38
 */
public interface CrowdFundingUserMsgInputContract {

    interface View extends BaseView {
        void showData(Result result);
        void showPostImgResult(Result result);
    }

    abstract class Presenter extends BasePresenter<View, CommonModel> {
        public abstract void postData(String url, Map<String, String> params);
        public abstract void postImg(String url, Map<String, String> params, List<File> files);
    }
}
