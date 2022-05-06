package com.fx.note.appliction;

import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.blankj.utilcode.util.LogUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author Created by 冯鑫 on 2021/10/9 16:02.
 * @description
 */
public class MyContentProvider extends ContentProvider {
    private static final String TAG = "MyContentProvider";


    @Override
    public void attachInfo(Context context, ProviderInfo info) {
        Log.d(TAG, "attachInfo");
        super.attachInfo(context, info);
    }

    @Override
    public boolean onCreate() {
        Log.d(TAG, "onCreate");
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
