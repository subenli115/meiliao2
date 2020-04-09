/*
 * Copyright (C) 2016 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.ziran.meiliao.common.permission;

import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.ziran.meiliao.common.commonutils.AnimationUtil;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.permission.rom.HuaweiUtils;
import com.ziran.meiliao.common.permission.rom.MeizuUtils;
import com.ziran.meiliao.common.permission.rom.MiuiUtils;
import com.ziran.meiliao.common.permission.rom.QikuUtils;
import com.ziran.meiliao.common.permission.rom.RomUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description:
 *
 * @author zhaozp
 * @since 2016-10-17
 */

public class FloatWindowManager {
    private static final String TAG = "FloatWindowManager";

    protected static volatile FloatWindowManager instance;

    private boolean isWindowDismiss = true;
    private WindowManager windowManager = null;
    private WindowManager.LayoutParams mParams = null;
    private BaseAVCallFloatView mFloatView = null;
    private Dialog dialog;

    public FloatWindowManager() {
        floatViews = new ConcurrentHashMap<>();
    }


    public static FloatWindowManager getInstance() {
        if (instance == null) {
            synchronized (FloatWindowManager.class) {
                if (instance == null) {
                    instance = new FloatWindowManager();
                }
            }
        }
        return instance;
    }

    public void applyOrShowFloatWindow(Context context) {
        applyOrShowFloatWindow(context, null);
    }

    public void applyOrShowFloatWindow(Context context, BaseAVCallFloatView floatView) {
        dismissWindow();
        if (checkPermission(context)) {
            showWindow(context, floatView);
        } else {
            applyPermission(context);
        }
    }


    private boolean checkPermission(Context context) {
        //6.0 版本之后由于 google 增加了对悬浮窗权限的管理，所以方式就统一了
        if (Build.VERSION.SDK_INT < 23) {
            if (RomUtils.checkIsMiuiRom()) {
                return miuiPermissionCheck(context);
            } else if (RomUtils.checkIsMeizuRom()) {
                return meizuPermissionCheck(context);
            } else if (RomUtils.checkIsHuaweiRom()) {
                return huaweiPermissionCheck(context);
            } else if (RomUtils.checkIs360Rom()) {
                return qikuPermissionCheck(context);
            }
        }
        return commonROMPermissionCheck(context);
    }

    private boolean huaweiPermissionCheck(Context context) {
        return HuaweiUtils.checkFloatWindowPermission(context);
    }

    private boolean miuiPermissionCheck(Context context) {
        return MiuiUtils.checkFloatWindowPermission(context);
    }

    private boolean meizuPermissionCheck(Context context) {
        return MeizuUtils.checkFloatWindowPermission(context);
    }

    private boolean qikuPermissionCheck(Context context) {
        return QikuUtils.checkFloatWindowPermission(context);
    }

    private boolean commonROMPermissionCheck(Context context) {
        //最新发现魅族6.0的系统这种方式不好用，天杀的，只有你是奇葩，没办法，单独适配一下
        if (RomUtils.checkIsMeizuRom()) {
            return meizuPermissionCheck(context);
        } else {
            Boolean result = true;
            if (Build.VERSION.SDK_INT >= 23) {
                try {
                    Class clazz = Settings.class;
                    Method canDrawOverlays = clazz.getDeclaredMethod("canDrawOverlays", Context.class);
                    result = (Boolean) canDrawOverlays.invoke(null, context);
                } catch (Exception e) {
                    LogUtils.logd(Log.getStackTraceString(e));
                }
            }
            return result;
        }
    }

    private void applyPermission(Context context) {
        if (Build.VERSION.SDK_INT < 23) {
            if (RomUtils.checkIsMiuiRom()) {
                miuiROMPermissionApply(context);
            } else if (RomUtils.checkIsMeizuRom()) {
                meizuROMPermissionApply(context);
            } else if (RomUtils.checkIsHuaweiRom()) {
                huaweiROMPermissionApply(context);
            } else if (RomUtils.checkIs360Rom()) {
                ROM360PermissionApply(context);
            }
        }
        commonROMPermissionApply(context);
    }

    private void ROM360PermissionApply(final Context context) {
        showConfirmDialog(context, new OnConfirmResult() {
            @Override
            public void confirmResult(boolean confirm) {
                if (confirm) {
                    QikuUtils.applyPermission(context);
                } else {
                    LogUtils.logd("ROM:360, user manually refuse OVERLAY_PERMISSION");
                }
            }
        });
    }

    private void huaweiROMPermissionApply(final Context context) {
        showConfirmDialog(context, new OnConfirmResult() {
            @Override
            public void confirmResult(boolean confirm) {
                if (confirm) {
                    HuaweiUtils.applyPermission(context);
                } else {
                    LogUtils.logd("ROM:huawei, user manually refuse OVERLAY_PERMISSION");
                }
            }
        });
    }

    private void meizuROMPermissionApply(final Context context) {
        showConfirmDialog(context, new OnConfirmResult() {
            @Override
            public void confirmResult(boolean confirm) {
                if (confirm) {
                    MeizuUtils.applyPermission(context);
                } else {
                    LogUtils.logd("ROM:meizu, user manually refuse OVERLAY_PERMISSION");
                }
            }
        });
    }

    private void miuiROMPermissionApply(final Context context) {
        showConfirmDialog(context, new OnConfirmResult() {
            @Override
            public void confirmResult(boolean confirm) {
                if (confirm) {
                    MiuiUtils.applyMiuiPermission(context);
                } else {
                    LogUtils.logd("ROM:miui, user manually refuse OVERLAY_PERMISSION");
                }
            }
        });
    }

    /**
     * 通用 rom 权限申请
     */
    private void commonROMPermissionApply(final Context context) {
        //这里也一样，魅族系统需要单独适配
        if (RomUtils.checkIsMeizuRom()) {
            meizuROMPermissionApply(context);
        } else {
            if (Build.VERSION.SDK_INT >= 23) {
                showConfirmDialog(context, new OnConfirmResult() {
                    @Override
                    public void confirmResult(boolean confirm) {
                        if (confirm) {
                            try {
                                Class clazz = Settings.class;
                                Field field = clazz.getDeclaredField("ACTION_MANAGE_OVERLAY_PERMISSION");

                                Intent intent = new Intent(field.get(null).toString());
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.setData(Uri.parse("package:" + context.getPackageName()));
                                context.startActivity(intent);
                            } catch (Exception e) {
                                LogUtils.logd(Log.getStackTraceString(e));
                            }
                        } else {
                            Log.d(TAG, "user manually refuse OVERLAY_PERMISSION");
                            //需要做统计效果
                        }
                    }
                });
            }
        }
    }

    private void showConfirmDialog(Context context, OnConfirmResult result) {
        showConfirmDialog(context, "您的手机没有授予悬浮窗权限，请开启后再试", result);
    }

    private void showConfirmDialog(Context context, String message, final OnConfirmResult result) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }

        dialog = new AlertDialog.Builder(context).setCancelable(true).setTitle("").setMessage(message).setPositiveButton("现在去开启", new
                DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                result.confirmResult(true);
                dialog.dismiss();
            }
        }).setNegativeButton("暂不开启", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                result.confirmResult(false);
                dialog.dismiss();
            }
        }).create();
        dialog.show();
    }

    public boolean isShow() {
        return mFloatView != null && mFloatView.isShown();
    }

    private interface OnConfirmResult {
        void confirmResult(boolean confirm);
    }

    private Map<String, View> floatViews;

    public void showWindow(String tag, BaseAVCallFloatView floatView) {
        if (!floatViews.containsKey(tag)) {
            showWindow(floatView.getContext(), floatView);
            floatViews.put(tag, floatView);
        }
    }

    public void dismissWindow(String tag) {
        if (floatViews.containsKey(tag)) {
            if (windowManager != null && floatViews.get(tag) != null) {
                windowManager.removeViewImmediate(floatViews.get(tag));
            }
        }
        if (EmptyUtils.isEmpty(floatViews)) {
            isWindowDismiss = true;
        }
    }

    private int screenHeight;

    private void showWindow(Context context, BaseAVCallFloatView floatView) {
        if (!isWindowDismiss) {
            LogUtils.logd("view is already added here");
            return;
        }

        isWindowDismiss = false;
        if (windowManager == null) {
            windowManager = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        }

        Point size = new Point();
        windowManager.getDefaultDisplay().getSize(size);
        int screenWidth = size.x;
        screenHeight = size.y;


        mParams = new WindowManager.LayoutParams();
        mParams.packageName = context.getPackageName();
        mParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        mParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager
                .LayoutParams.FLAG_LAYOUT_INSET_DECOR | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        mParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        mParams.format = PixelFormat.RGBA_8888;
        mParams.gravity = Gravity.LEFT | Gravity.TOP;


        if (floatView == null) {
            mFloatView = new AVCallFloatView(context);
        } else {
            mFloatView = floatView;
        }
        if (withBottomHeight == 0) {
            withBottomHeight = dp2px(mFloatView.getContext(), 48f);
        }
        mParams.x = screenWidth - dp2px(context, 100);
        mParams.y = screenHeight - mFloatView.getMeasuredHeight();
        mFloatView.setParams(mParams);
        mFloatView.setIsShowing(true);
        windowManager.addView(floatView, mParams);
    }

    boolean isSetViewHeight;

    public void dismissWindow() {
        if (isWindowDismiss) {
            LogUtils.logd("window can not be dismiss cause it has not been added");
            return;
        }

        isWindowDismiss = true;
        mFloatView.setIsShowing(false);
        if (windowManager != null && mFloatView != null) {
            windowManager.removeViewImmediate(mFloatView);
        }
    }

    private int dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }


    public void setIsShowing(boolean isShowing) {
        if (mFloatView == null) return;
        this.isShowing = isShowing;
        mFloatView.setVisibility(isShowing ? View.VISIBLE : View.INVISIBLE);
    }

    private boolean isShowing;

    //    private int lastY;
    public void setIsShowingAnimation(boolean isShowing) {
        if (mFloatView == null || this.isShowing == isShowing) return;
        this.isShowing = isShowing;
        AnimationUtil.startAnimationVer(isShowing, mFloatView, true, 400, null);
    }

    public void setShowingAndY(boolean withBottom, boolean isShowing) {
        setIsShowing(isShowing);
        setY(withBottom);
    }

    public boolean isShowing() {
        return isShowing;
    }

    public void setShowingAndYWithAnimation(boolean withBottom, boolean isShowing) {
        setIsShowingAnimation(isShowing);
        setY(withBottom);
    }

    private boolean withBottom;
    private int withBottomHeight;

    public void setY(final boolean withBottom) {
        if (mFloatView == null) return;
        if (!isSetViewHeight) {
            mParams.y = screenHeight - mFloatView.getMeasuredHeight();
            isSetViewHeight = true;
        }
        if (this.withBottom == withBottom) return;

        final int startY = mParams.y;
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1f);
        valueAnimator.setDuration(300);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (withBottom) {
                    mParams.y = (int) (startY - withBottomHeight * animation.getAnimatedFraction());
                } else {
                    mParams.y = (int) (startY + withBottomHeight * animation.getAnimatedFraction());
                }
                windowManager.updateViewLayout(mFloatView, mParams);
            }
        });
        valueAnimator.start();
        this.withBottom = withBottom;
    }

}
