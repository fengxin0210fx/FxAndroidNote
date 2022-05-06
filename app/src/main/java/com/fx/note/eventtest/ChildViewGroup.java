package com.fx.note.eventtest;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.LogUtils;

import androidx.annotation.Nullable;

/**
 * @author Created by 冯鑫 on 2021/6/28 19:55.
 * @description
 */
public class ChildViewGroup extends LinearLayout {
    private static final String TAG = "子容器控件";

    public ChildViewGroup(Context context) {
        super(context);
    }

    public ChildViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ChildViewGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ChildViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;

            }
        });

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.dTag(TAG, "2:dispatchTouchEvent action:ACTION_DOWN");
//                return false;
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.dTag(TAG, "2:dispatchTouchEvent action:ACTION_MOVE");
//                return true;
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.dTag(TAG, "2:dispatchTouchEvent action:ACTION_UP");
                //return true;
                break;
            case MotionEvent.ACTION_CANCEL:
                LogUtils.dTag(TAG, "2:dispatchTouchEvent action:ACTION_CANCEL");
                break;
        }
        LogUtils.dTag(TAG, "2:dispatchTouchEvent dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.dTag(TAG, "2:onInterceptTouchEvent action:ACTION_DOWN");
//                return true;
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.dTag(TAG, "2:onInterceptTouchEvent action:ACTION_MOVE");
//                return true;

            break;
            case MotionEvent.ACTION_UP:
                LogUtils.dTag(TAG, "2:onInterceptTouchEvent action:ACTION_UP");
                //return true;
                break;
            case MotionEvent.ACTION_CANCEL:
                LogUtils.dTag(TAG, "2:onInterceptTouchEvent action:ACTION_CANCEL");
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.dTag(TAG, "2:onTouchEvent action:ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.dTag(TAG, "2:onTouchEvent action:ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.dTag(TAG, "2:onTouchEvent action:ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                LogUtils.dTag(TAG, "2:onTouchEvent action:ACTION_CANCEL");
                break;
        }
        return super.onTouchEvent(ev);
    }
}
