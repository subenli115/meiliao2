package com.ziran.meiliao.im.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.UserInfo;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.im.utils.BitmapLoader;
import com.ziran.meiliao.widget.GlideCircleTransform;

public class GroupMemberGridAdapter extends BaseAdapter {

    private static final String TAG = "GroupMemberGridAdapter";

    private LayoutInflater mInflater;
    //群成员列表
    private List<UserInfo> mMemberList = new ArrayList<UserInfo>();
    private boolean mIsCreator = false;
    //群成员个数
    private int mCurrentNum;
    //用群成员项数余4得到，作为下标查找mRestArray，得到空白项
    private int mRestNum;
    private int maxGridItem;
    private boolean mIsGroup;
    private String mTargetId;
    private Context mContext;
    private int mAvatarSize;
    private String mTargetAppKey;
    private boolean mListType = true;//是否显示全部群成员
    //记录空白项的数组
    private int[] mRestArray = new int[] {3, 2, 1, 0, 4};

    //群聊
    public GroupMemberGridAdapter(Context context, List<UserInfo> memberList, boolean isCreator,
                                  int size) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        mIsGroup = true;
        this.mMemberList = memberList;
        mCurrentNum = mMemberList.size();
        this.mIsCreator = isCreator;
        maxGridItem = isCreator ? 13 : 14;
        this.mAvatarSize = size;
        initBlankItem(mCurrentNum);
    }

    //单聊
    public GroupMemberGridAdapter(Context context, String targetId, String appKey) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mTargetId = targetId;
        this.mTargetAppKey = appKey;
    }

    public void initBlankItem(int size) {
        if (mMemberList.size() > maxGridItem) {
            mCurrentNum = maxGridItem;
        } else {
            mCurrentNum = mMemberList.size();
        }
        mRestNum = mRestArray[mCurrentNum % 5];
    }

    public void refreshMemberList() {
        if (mMemberList.size() > maxGridItem) {
            mCurrentNum = maxGridItem;
        } else {
            mCurrentNum = mMemberList.size();
        }
        mRestNum = mRestArray[mCurrentNum % 5];
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        //如果是普通成员，并且群组成员余4等于3，特殊处理，隐藏下面一栏空白
        if (mCurrentNum % 5 == 4 && !mIsCreator) {
            return mCurrentNum > 14 ? 15 : mCurrentNum + 1;
        } else {
            return mCurrentNum > 13 ? 15 : mCurrentNum + mRestNum + 2;
        }
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mMemberList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ItemViewTag viewTag;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_group, null);
            viewTag = new ItemViewTag((ImageView) convertView.findViewById(R.id.grid_avatar),
                    (TextView) convertView.findViewById(R.id.grid_name),
                    (ImageView) convertView.findViewById(R.id.grid_delete_icon));
            convertView.setTag(viewTag);
        } else {
            viewTag = (ItemViewTag) convertView.getTag();
        }
            if (position == 0) {
                Conversation conv = JMessageClient.getSingleConversation(mTargetId, mTargetAppKey);
                UserInfo userInfo = (UserInfo) conv.getTargetInfo();
                if (!TextUtils.isEmpty(userInfo.getAvatar())) {
                    userInfo.getAvatarBitmap(new GetAvatarBitmapCallback() {
                        @Override
                        public void gotResult(int status, String desc, Bitmap bitmap) {
                            if (status == 0) {
                                viewTag.icon.setImageBitmap(bitmap);
                            }else {
                                viewTag.icon.setImageResource(R.drawable.jmui_head_icon);
                            }
                        }
                    });
                }else {
                    viewTag.icon.setImageResource(R.drawable.jmui_head_icon);
                }
                String displayName = userInfo.getNotename();
                if (TextUtils.isEmpty(displayName)) {
                    displayName = userInfo.getNickname();
                    if (TextUtils.isEmpty(displayName)) {
                        displayName = userInfo.getUserName();
                    }
                }
                viewTag.name.setText(displayName);
                viewTag.icon.setVisibility(View.VISIBLE);
                viewTag.name.setVisibility(View.VISIBLE);
            }else {
                viewTag.icon.setVisibility(View.GONE);
                viewTag.name.setVisibility(View.GONE);

            }


        return convertView;
    }

    public void setCreator(boolean isCreator) {
        mIsCreator = isCreator;
        notifyDataSetChanged();
    }

    private static class ItemViewTag {

        private ImageView icon;
        private ImageView deleteIcon;
        private TextView name;

        public ItemViewTag(ImageView icon, TextView name, ImageView deleteIcon) {
            this.icon = icon;
            this.deleteIcon = deleteIcon;
            this.name = name;
        }
    }


}
