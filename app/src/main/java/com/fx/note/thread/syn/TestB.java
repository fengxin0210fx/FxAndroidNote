package com.fx.note.thread.syn;

import com.blankj.utilcode.util.LogUtils;

/**
 * @author Created by 冯鑫 on 2021/8/27 15:48.
 * @description
 */
public class TestB {
    private static final String TAG = "TestB";
    final static Object object = new Object();

    public void getB() {
        synchronized (object) {
            LogUtils.dTag(TAG, "getB ");
            try {
                Thread.sleep(2000000);
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }


    public void getB1() {
        synchronized (this) {

            LogUtils.dTag(TAG, "getB1 ");
            try {
                Thread.sleep(2000000);
            } catch (Exception e) {
                e.printStackTrace();

            }
        }


    }

    public void getB2() {
        synchronized (this) {

            LogUtils.dTag(TAG, "getB2 ");

        }


    }

}
