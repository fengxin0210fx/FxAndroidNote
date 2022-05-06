package com.fx.note.eventtest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;

import androidx.annotation.Nullable;

/**
 * @author Created by 冯鑫 on 2021/6/28 20:34.
 * @description
 */
public class ChildView extends View {
    private static final String TAG = "子View";

    public ChildView(Context context) {
        this(context, null);
    }

    public ChildView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChildView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public ChildView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
//        setOnTouchListener(new OnTouchListener() {
//            @SuppressLint("ClickableViewAccessibility")
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                LogUtils.dTag(TAG, "1:onTouch  ");
//
//                return false;
//            }
//        });
//        setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LogUtils.dTag(TAG, "1:setOnClickListener onClick");
//
//            }
//        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.dTag(TAG, "1:dispatchTouchEvent action:ACTION_DOWN");
                //return true;
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.dTag(TAG, "1:dispatchTouchEvent action:ACTION_MOVE");
//                return true;
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.dTag(TAG, "1:dispatchTouchEvent action:ACTION_UP");
                //return true;
                break;
            case MotionEvent.ACTION_CANCEL:
                LogUtils.dTag(TAG, "1:dispatchTouchEvent action:ACTION_CANCEL");
                break;
        }
        LogUtils.dTag(TAG, "1:dispatchTouchEvent dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.dTag(TAG, "1:onTouchEvent action:ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.dTag(TAG, "1:onTouchEvent action:ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.dTag(TAG, "1:onTouchEvent action:ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                LogUtils.dTag(TAG, "1:onTouchEvent action:ACTION_CANCEL");
                break;
        }
        return super.onTouchEvent(ev);
    }
}
