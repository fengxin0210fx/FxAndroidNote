package com.fx.note.eventtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.fx.note.PngShowActivity;
import com.fx.note.bean.Person;
import com.fx.note.R;
import com.fx.note.bean.Pig;

public class EventActivity extends AppCompatActivity {
    private static final String TAG = "EventActivity";
    final int HASH_INCREMENT = 0x61c88647;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ThreadLocal翻译成中文比较准确的叫法应该是：线程局部变量。
        setContentView(R.layout.activity_event);

        Button show = findViewById(R.id.show_png);
        show.invalidate();
        show.postInvalidate();
        show.post(() -> {

        });
        show.setOnClickListener(v -> {
            Intent intent = new Intent(EventActivity.this, PngShowActivity.class);
            intent.putExtra("pngid", "event");
            startActivity(intent);

        });


        ThreadLocal<Person> threadLocal = new ThreadLocal<>();
        Person person = new Person("张三", 30);
        Person personS = new Person("张4", 34);
        Pig pig = new Pig("我是猪", 2);
        threadLocal.set(person);
        new Thread(() -> {
            threadLocal.set(person);
            threadLocal.set(personS);
            Person person1 = threadLocal.get();
            if (person1 == null) {
                LogUtils.dTag(TAG, Thread.currentThread().getName() + ":  " + "person1==null");
            } else {
                LogUtils.dTag(TAG, Thread.currentThread().getName() + ":  " + threadLocal.get());
            }

        }, "thread1").start();
        new Thread(() -> {
            Person person1 = threadLocal.get();
            if (person1 == null) {
                LogUtils.dTag(TAG, Thread.currentThread().getName() + ":  " + "person1==null");
            }
        }, "thread2").start();
//        hashCodes(16);//hash初始长度
//        hashCodes(32);//扩容一倍
//        hashCodes(64);

    }

    public void hashCodes(int length) {
        int hashcode = 0;
        for (int i = 0; i < length; i++) {
            hashcode = hashcode + HASH_INCREMENT;
            System.out.print(hashcode & (length - 1));
            System.out.print(" ");
        }
        System.out.println();
    }

    ;

    //事件触发 开始
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.dTag(TAG, "0:dispatchTouchEvent action:ACTION_DOWN");
                //手指落下，如果父元素
                //return true;
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.dTag(TAG, "0:dispatchTouchEvent action:ACTION_MOVE");
                //手指移动时候回调
//                return true;
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.dTag(TAG, "0:dispatchTouchEvent action:ACTION_UP");
                //手指抬起
                //return true;
                break;
            case MotionEvent.ACTION_CANCEL:
                LogUtils.dTag(TAG, "0:dispatchTouchEvent action:ACTION_CANCEL");
                break;
        }
        LogUtils.dTag(TAG, "0:dispatchTouchEvent dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        //如果父元素ontouch 返回 false 或者 super 就回调。
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.dTag(TAG, "0:onTouchEvent action:ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.dTag(TAG, "0:onTouchEvent action:ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.dTag(TAG, "0:onTouchEvent action:ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                LogUtils.dTag(TAG, "0:onTouchEvent action:ACTION_CANCEL");
                break;
        }
        return super.onTouchEvent(ev);
    }
}