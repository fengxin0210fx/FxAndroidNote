package com.fx.note.thread.syn;

import com.blankj.utilcode.util.LogUtils;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Created by 冯鑫 on 2021/8/27 15:48.
 * @description
 */
public class TestA {
    private static final String TAG = "TestA";
    final Object object = new Object();
    public  void getA() {
        synchronized (TestA.class) {
            LogUtils.dTag(TAG, "getA ");
            try {
                Thread.sleep(2000000);
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }


    public void getA1() {
        synchronized (object){
            LogUtils.dTag(TAG, "getA1 ");
            try {
                Thread.sleep(2000000);
                ReentrantLock lock=new ReentrantLock();
                lock.lock();
                lock.unlock();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void getA2() {
//        AtomicInteger
//        ReentrantLock

        synchronized (object){
            LogUtils.dTag(TAG, "getA2 ");
        }

    }
}
