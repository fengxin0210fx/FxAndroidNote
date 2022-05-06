package com.fx.note;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.blankj.utilcode.util.LogUtils;

public class TestActivity extends AppCompatActivity {
    private static final String TAG = "TestActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        LogUtils.dTag(TAG,"onRestart ");
        super.onRestart();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        LogUtils.dTag(TAG,"attachBaseContext ");
        super.attachBaseContext(newBase);
    }

    @Override
    public void setTheme(int resId) {
        super.setTheme(resId);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        LogUtils.dTag(TAG,"onPostCreate ");
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostResume() {
        LogUtils.dTag(TAG,"onPostResume ");
        super.onPostResume();
    }

    @Override
    protected void onStart() {
        LogUtils.dTag(TAG,"onStart ");
        super.onStart();
    }

    @Override
    protected void onStop() {
        LogUtils.dTag(TAG,"onStop ");
        super.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onLowMemory() {
        LogUtils.dTag(TAG,"onLowMemory ");
        super.onLowMemory();
    }

    @Override
    protected void onPause() {
        LogUtils.dTag(TAG,"onPause ");
        super.onPause();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        LogUtils.dTag(TAG,"onNewIntent ");
        super.onNewIntent(intent);
    }

    @Override
    public void onStateNotSaved() {
        LogUtils.dTag(TAG,"onStateNotSaved ");

        super.onStateNotSaved();
    }

    @Override
    protected void onResume() {
        LogUtils.dTag(TAG,"onResume ");
        super.onResume();
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        LogUtils.dTag(TAG,"getLifecycle ");
        return super.getLifecycle();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }
}