package com.ahyq.utilslibrary;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Method;

public final class MyUriUtils {


    private static final String TAG = "MyUriUtils";

    private MyUriUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }


    public static File uri2File(@NonNull final Uri uri) {
        LogUtils.dTag("UriUtils", uri.toString());
        String authority = uri.getAuthority();
        String scheme = uri.getScheme();
        String path = uri.getPath();
        LogUtils.dTag(TAG, "authority :" + authority);

        LogUtils.dTag(TAG, "scheme :" + scheme);

        LogUtils.dTag(TAG, "path :" + path);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && path != null) {
            String[] externals = new String[]{"/external", "/external_path", "/external_files"};
            for (String external : externals) {
                if (path.startsWith(external + "/")) {
                    File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                            + path.replace(external, ""));
                    if (file.exists()) {
                        LogUtils.dTag("UriUtils", uri.toString() + " -> " + external);
                        return file;
                    }
                }
            }
        }
        if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            if (path != null) {
                return new File(path);
            }
            LogUtils.dTag("UriUtils", uri.toString() + " parse failed. -> 0");
            return null;
        }// end 0

        else if ("com.huawei.hidisk.fileprovider".equals(uri.getAuthority())) {
            //华为Android10的文件管理器

            if (!TextUtils.isEmpty(path)) {
                return new File(path.replace("/root", ""));
            }
            return null;
        } else if ("com.tencent.mobileqq.fileprovider".equals(uri.getAuthority())) {
            //华为Android10的文件管理器

            if (!TextUtils.isEmpty(path)) {
                return new File(path.replace("/external_files", ""));
            }
            return null;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                && DocumentsContract.isDocumentUri(Utils.getApp(), uri)) {
            if ("com.android.externalstorage.documents".equals(authority)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return new File(Environment.getExternalStorageDirectory() + "/" + split[1]);
                } else {
                    // Below logic is how External Storage provider build URI for documents
                    // http://stackoverflow.com/questions/28605278/android-5-sd-card-label
                    StorageManager mStorageManager = (StorageManager) Utils.getApp().getSystemService(Context.STORAGE_SERVICE);
                    try {
                        Class<?> storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");
                        Method getVolumeList = mStorageManager.getClass().getMethod("getVolumeList");
                        Method getUuid = storageVolumeClazz.getMethod("getUuid");
                        Method getState = storageVolumeClazz.getMethod("getState");
                        Method getPath = storageVolumeClazz.getMethod("getPath");
                        Method isPrimary = storageVolumeClazz.getMethod("isPrimary");
                        Method isEmulated = storageVolumeClazz.getMethod("isEmulated");

                        Object result = getVolumeList.invoke(mStorageManager);

                        final int length = Array.getLength(result);
                        for (int i = 0; i < length; i++) {
                            Object storageVolumeElement = Array.get(result, i);
                            //String uuid = (String) getUuid.invoke(storageVolumeElement);

                            final boolean mounted = Environment.MEDIA_MOUNTED.equals(getState.invoke(storageVolumeElement))
                                    || Environment.MEDIA_MOUNTED_READ_ONLY.equals(getState.invoke(storageVolumeElement));

                            //if the media is not mounted, we need not get the volume details
                            if (!mounted) {
                                continue;
                            }

                            //Primary storage is already handled.
                            if ((Boolean) isPrimary.invoke(storageVolumeElement)
                                    && (Boolean) isEmulated.invoke(storageVolumeElement)) {
                                continue;
                            }

                            String uuid = (String) getUuid.invoke(storageVolumeElement);

                            if (uuid != null && uuid.equals(type)) {
                                return new File(getPath.invoke(storageVolumeElement) + "/" + split[1]);
                            }
                        }
                    } catch (Exception ex) {
                        LogUtils.dTag("UriUtils", uri.toString() + " parse failed. " + ex.toString() + " -> 1_0");
                    }
                }
                LogUtils.dTag("UriUtils", uri.toString() + " parse failed. -> 1_0");
                return null;
            }// end 1_0
            else if ("com.android.providers.downloads.documents".equals(authority)) {
                final String id = DocumentsContract.getDocumentId(uri);
                if (!TextUtils.isEmpty(id)) {
                    try {
                        final Uri contentUri = ContentUris.withAppendedId(
                                Uri.parse("content://downloads/public_downloads"),
                                Long.valueOf(id)
                        );
                        return getFileFromUri(contentUri, "1_1");
                    } catch (NumberFormatException e) {
                        if (id.startsWith("raw:")) {
                            return new File(id.substring(4));
                        }
                    }
                }
                LogUtils.dTag("UriUtils", uri.toString() + " parse failed. -> 1_1");
                return null;
            }// end 1_1
            else if ("com.android.providers.media.documents".equals(authority)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                Uri contentUri;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                } else {
                    LogUtils.dTag("UriUtils", uri.toString() + " parse failed. -> 1_2");
                    return null;
                }
                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};
                return getFileFromUri(contentUri, selection, selectionArgs, "1_2");
            } else if ("com.huawei.hidisk.fileprovider".equals(authority)) {
                //华为Android10的文件管理器
                if (!TextUtils.isEmpty(path)) {
                    return new File(path.replace("/root", ""));
                }
                return getFileFromUri(uri, "1_3_huawei");
            }// end 1_2
            else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
                return getFileFromUri(uri, "1_3");
            }// end 1_3
            else {
                LogUtils.dTag("UriUtils", uri.toString() + " parse failed. -> 1_4");
                return null;
            }// end 1_4
        }// end 1
        else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            return getFileFromUri(uri, "2");
        }// end 2
        else {
            LogUtils.dTag("UriUtils", uri.toString() + " parse failed. -> 3");
            return null;
        }// end 3
    }

    private static File getFileFromUri(final Uri uri, final String code) {
        return getFileFromUri(uri, null, null, code);
    }

    private static File getFileFromUri(final Uri uri,
                                       final String selection,
                                       final String[] selectionArgs,
                                       final String code) {
        if ("com.google.android.apps.photos.content".equals(uri.getAuthority())) {
            if (!TextUtils.isEmpty(uri.getLastPathSegment())) {
                return new File(uri.getLastPathSegment());
            }
        } else if ("com.tencent.mtt.fileprovider".equals(uri.getAuthority())) {
            String path = uri.getPath();
            if (!TextUtils.isEmpty(path)) {
                File fileDir = Environment.getExternalStorageDirectory();
                return new File(fileDir, path.substring("/QQBrowser".length(), path.length()));
            }
        }

        final Cursor cursor = Utils.getApp().getContentResolver().query(
                uri, new String[]{"_data"}, selection, selectionArgs, null);
        if (cursor == null) {
            LogUtils.dTag("UriUtils", uri.toString() + " parse failed(cursor is null). -> " + code);
            return null;
        }
        try {
            if (cursor.moveToFirst()) {
                final int columnIndex = cursor.getColumnIndex("_data");
                if (columnIndex > -1) {
                    return new File(cursor.getString(columnIndex));
                } else {
                    LogUtils.dTag("UriUtils", uri.toString() + " parse failed(columnIndex: " + columnIndex + " is wrong). -> " + code);
                    return null;
                }
            } else {
                LogUtils.dTag("UriUtils", uri.toString() + " parse failed(moveToFirst return false). -> " + code);
                return null;
            }
        } catch (Exception e) {
            LogUtils.dTag("UriUtils", uri.toString() + " parse failed. -> " + code);
            return null;
        } finally {
            cursor.close();
        }
    }
}
