package com.ahyq.utilslibrary;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.preference.PreferenceManager;
import android.provider.DocumentsContract;
import android.text.TextUtils;
import android.util.Log;

import androidx.documentfile.provider.DocumentFile;

import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class DocumentsUtils {
    //    DocumentsUtils 公共方法	功能描述
//    void cleanCache()	清除路径缓存，建议插拔 sd 卡后调用
//    boolean isOnExtSdCard(File file, Context c)	文件路径是否在外置 SD 卡上
//    DocumentFile getDocumentFile(final File file, final boolean isDirectory, Context context)	从 File 转到 DocumentFile
//    boolean mkdirs(Context context, File dir)	创建文件夹
//    boolean delete(Context context, File file)	删除文件
//    boolean canWrite(File file)	File 文件是否可写（如果文件不存在，则尝试创建文件再删除检查写入权限）不兼容
//    boolean canWrite(Context context, File file)	文件是否可写
//    boolean renameTo(Context context, File src, File dest)	文件重命名
//    boolean saveTreeUri(Context context, String rootPath, Uri uri)	保存 path 和 uri 到本地存储
//    boolean checkWritableRootPath(Context context, String rootPath)	检查路径是否可写，不可写返回 true
//    InputStream getInputStream(Context context, File destFile)	获取 InputStream，可用于读操作
//    OutputStream getOutputStream(Context context, File destFile)	获取 OutputStream，可用于写操作
    public static final int OPEN_DOCUMENT_TREE_CODE = 8000;
    private static final String TAG = DocumentsUtils.class.getSimpleName();
    private static List<String> sExtSdCardPaths = new ArrayList<>();
    private static int sBufferSize = 1;

    private DocumentsUtils() {

    }

    public static void cleanCache() {
        sExtSdCardPaths.clear();
    }

    /**
     * Get a list of external SD card paths. (Kitkat or higher.)
     *
     * @return A list of external SD card paths.
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private static String[] getExtSdCardPaths(Context context) {
        if (sExtSdCardPaths.size() > 0) {
            return sExtSdCardPaths.toArray(new String[0]);
        }
        for (File file : context.getExternalFilesDirs("external")) {
            if (file != null && !file.equals(context.getExternalFilesDir("external"))) {
                int index = file.getAbsolutePath().lastIndexOf("/Android/data");
                if (index < 0) {
                    Log.w(TAG, "Unexpected external file dir: " + file.getAbsolutePath());
                } else {
                    String path = file.getAbsolutePath().substring(0, index);
                    try {
                        path = new File(path).getCanonicalPath();
                    } catch (IOException e) {
                        // Keep non-canonical path.
                    }
                    sExtSdCardPaths.add(path);
                }
            }
        }
        if (sExtSdCardPaths.isEmpty()) {
            sExtSdCardPaths.add("/storage/sdcard1");
        }
        return sExtSdCardPaths.toArray(new String[0]);
    }

    /**
     * Determine the main folder of the external SD card containing the given file.
     *
     * @param file the file.
     * @return The main folder of the external SD card containing this file, if the file is on an SD
     * card. Otherwise,
     * null is returned.
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private static String getExtSdCardFolder(final File file, Context context) {
        String[] extSdPaths = getExtSdCardPaths(context);
        try {
            for (int i = 0; i < extSdPaths.length; i++) {
                if (file.getCanonicalPath().startsWith(extSdPaths[i])) {
                    return extSdPaths[i];
                }
            }
        } catch (IOException e) {
            return null;
        }
        return null;
    }

    /**
     * Determine if a file is on external sd card. (Kitkat or higher.)
     *
     * @param file The file.
     * @return true if on external sd card.
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static boolean isOnExtSdCard(final File file) {
        return getExtSdCardFolder(file, Utils.getApp()) != null;
    }

    /**
     * Get a DocumentFile corresponding to the given file (for writing on ExtSdCard on Android 5).
     * If the file is not
     * existing, it is created.
     *
     * @param file        The file.
     * @param isDirectory flag indicating if the file should be a directory.
     * @return The DocumentFile
     */
    public static DocumentFile getDocumentFile(final File file, final boolean isDirectory) {

        String baseFolder = getExtSdCardFolder(file, Utils.getApp());
        boolean originalDirectory = false;
        if (baseFolder == null) {
            return null;
        }

        String relativePath = null;
        try {
            String fullPath = file.getCanonicalPath();
            if (!baseFolder.equals(fullPath)) {
                relativePath = fullPath.substring(baseFolder.length() + 1);
            } else {
                originalDirectory = true;
            }
        } catch (IOException e) {
            return null;
        } catch (Exception f) {
            originalDirectory = true;
            //continue
        }
        String as = PreferenceManager.getDefaultSharedPreferences(Utils.getApp()).getString(baseFolder,
                null);

        Uri treeUri = null;
        if (as != null) {
            treeUri = Uri.parse(as);
        }
        if (treeUri == null) {
            return null;
        }

        // start with root of SD card and then parse through document tree.
        DocumentFile document = DocumentFile.fromTreeUri(Utils.getApp(), treeUri);
        if (originalDirectory) {
            return document;
        }
        String[] parts = relativePath.split("/");
        for (int i = 0; i < parts.length; i++) {
            DocumentFile nextDocument = document.findFile(parts[i]);

            if (nextDocument == null) {
                if ((i < parts.length - 1) || isDirectory) {
                    nextDocument = document.createDirectory(parts[i]);
                } else {
                    nextDocument = document.createFile("image", parts[i]);

                }
            }
            document = nextDocument;
        }

        return document;
    }

    public static boolean mkdirs(File dir, boolean isdir) {
        boolean res = dir.mkdirs();
        if (!res) {
            if (DocumentsUtils.isOnExtSdCard(dir)) {
                DocumentFile documentFile = DocumentsUtils.getDocumentFile(dir, isdir);
                res = documentFile != null && documentFile.canWrite();
            }
        }
        return res;
    }

    public static boolean delete(File file) {
        boolean ret = file.delete();

        if (!ret && DocumentsUtils.isOnExtSdCard(file)) {
            DocumentFile f = DocumentsUtils.getDocumentFile(file, false);
            if (f != null) {
                ret = f.delete();

            }
        }
        return ret;
    }

    public synchronized static boolean canWrite(File file) {
        boolean res = file.exists() && file.canWrite();

        if (!res && !file.exists()) {
            try {
                if (!file.isDirectory()) {
                    LogUtils.dTag(TAG, "!file.isDirectory() " + file.getAbsolutePath());
                    res = FileUtils.createOrExistsFile(file);
//                    res=mkdirs(file,false);
//                    res = file.createNewFile() && file.delete();

                } else {
                    LogUtils.dTag(TAG, "file.isDirectory" + file.getAbsolutePath());
//                    res = file.mkdirs() && file.delete();
                    res = FileUtils.createOrExistsDir(file);
//                    res=mkdirs(file,true);
                }
            } catch (Exception e) {
                e.printStackTrace();
                LogUtils.eTag(TAG, e);
            }
        }
        return res;
    }

    public synchronized static boolean canWrites(boolean res, File file) {
//        boolean res = canWrite(file);

        if (DocumentsUtils.isOnExtSdCard(file)) {
            DocumentFile documentFile = DocumentsUtils.getDocumentFile(file, true);
            res = documentFile != null && documentFile.canWrite();
        }
        return res;
    }

    public static boolean renameTo(Context context, File src, File dest) {
        boolean res = src.renameTo(dest);

        if (!res && isOnExtSdCard(dest)) {
            DocumentFile srcDoc;
            if (isOnExtSdCard(src)) {
                srcDoc = getDocumentFile(src, false);
            } else {
                srcDoc = DocumentFile.fromFile(src);
            }
            DocumentFile destDoc = getDocumentFile(dest.getParentFile(), true);
            if (srcDoc != null && destDoc != null) {
                try {
                    if (src.getParent().equals(dest.getParent())) {
                        res = srcDoc.renameTo(dest.getName());
                    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        res = DocumentsContract.moveDocument(context.getContentResolver(),
                                srcDoc.getUri(),
                                srcDoc.getParentFile().getUri(),
                                destDoc.getUri()) != null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return res;
    }

    public static InputStream getInputStream(File destFile) {
        LogUtils.dTag(TAG, destFile.getAbsoluteFile());
        InputStream in = null;
        try {
            if (!canWrite(destFile) && isOnExtSdCard(destFile)) {
                DocumentFile file = DocumentsUtils.getDocumentFile(destFile, false);
                if (file != null && file.canWrite()) {
                    in = Utils.getApp().getContentResolver().openInputStream(file.getUri());
                }
            } else {
                in = new FileInputStream(destFile);

            }
        } catch (FileNotFoundException e) {
            LogUtils.eTag(TAG, e);
            e.printStackTrace();
        }
        return in;
    }

    public static String getStringFromInputStream(File destFile) {
        //读取文件 的例子
        String str = "just a test\n";
        String strRead = "";
        try {
            InputStream is = getInputStream(destFile);
            strRead = readFile2String(is, BaseUtilsConstance.fileCharsetName);
//            InputStreamReader input = new InputStreamReader(is, StandardCharsets.UTF_8);
//            BufferedReader reader = new BufferedReader(input);
//            while ((str = reader.readLine()) != null) {
//                strRead += str+"\n";
//            }
            LogUtils.iTag(TAG, "lum:读取的文件是 " + strRead);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.eTag(TAG, e);
        }

        LogUtils.dTag(TAG, "strRead " + strRead);
//        strRead = strRead.substring(0, strRead.length());
        return strRead;
    }


    /**
     * Return the string in file.
     *
     * @param charsetName The name of charset.
     * @return the string in file
     */
    public static String readFile2String(InputStream is, String charsetName) {
        char[] bytes = readFile2BytesByStream(is, null);
        if (bytes == null) return null;
        if (TextUtils.isEmpty(charsetName)) {
            return new String(bytes);
        } else {
            try {
                return new String(bytes);
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    /**
     * Return the bytes in file by stream.
     *
     * @param listener The progress update listener.
     * @return the bytes in file
     */
    public static char[] readFile2BytesByStream(final InputStream is,
                                                final FileIOUtils.OnProgressUpdateListener listener) {

        try {

            CharArrayWriter charArrayWriter = null;

            try {
                charArrayWriter = new CharArrayWriter();
                InputStreamReader isr = new InputStreamReader(is, BaseUtilsConstance.fileCharsetName); //ANSI编码
                BufferedReader reader = new BufferedReader(isr);
                char[] b = new char[sBufferSize];
                int len;
                if (listener == null) {
                    while ((len = reader.read(b, 0, sBufferSize)) != -1) {
                        charArrayWriter.write(b);
                    }
                } else {
                    double totalSize = is.available();
                    int curSize = 0;
                    listener.onProgressUpdate(0);
                    while ((len = reader.read(b, 0, sBufferSize)) != -1) {
                        charArrayWriter.write(b);
                        curSize += len;
                        listener.onProgressUpdate(curSize / totalSize);
                    }
                }
                try {
                    isr.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    LogUtils.eTag(TAG, e.toString());
                }
                return charArrayWriter.toCharArray();
            } catch (Exception e) {
                e.printStackTrace();
                LogUtils.eTag(TAG, e);
                return null;
            } finally {
                try {
                    is.close();
                } catch (Exception e) {
                    LogUtils.eTag(TAG, e);
                    e.printStackTrace();
                }

                try {
                    if (charArrayWriter != null) {
                        charArrayWriter.close();
                    }
                } catch (Exception e) {
                    LogUtils.eTag(TAG, e);
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.eTag(TAG, e);
            return null;
        }
    }

    /**
     * Return the bytes in file by stream.
     *
     * @param file     The file.
     * @param listener The progress update listener.
     * @return the bytes in file
     */
    public static char[] readFile2BytesByStream(final File file,
                                                FileIOUtils.OnProgressUpdateListener listener) {
        if (!FileUtils.isFileExists(file)) return null;
//        listener = null;
        LogUtils.dTag(TAG, "readFile2BytesByStream  ");
        try {

            CharArrayWriter charArrayWriter = null;
            InputStream is = new BufferedInputStream(new FileInputStream(file), sBufferSize);

            try {
                charArrayWriter = new CharArrayWriter();
                InputStreamReader isr = new InputStreamReader(is, BaseUtilsConstance.fileCharsetName); //ANSI编码
                BufferedReader reader = new BufferedReader(isr);
                char[] b = new char[sBufferSize];
                int len;
                if (listener == null) {
                    while ((len = reader.read(b, 0, sBufferSize)) != -1) {
                        charArrayWriter.write(b);
                    }
                } else {
                    double totalSize = is.available();
                    int curSize = 0;
                    listener.onProgressUpdate(0);
                    while ((len = reader.read(b, 0, sBufferSize)) != -1) {
                        charArrayWriter.write(b);
                        curSize += len;
                        listener.onProgressUpdate(curSize / totalSize);
                    }
                }
                try {
                    isr.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    LogUtils.eTag(TAG, e.toString());
                }
                return charArrayWriter.toCharArray();
            } catch (Exception e) {
                e.printStackTrace();
                LogUtils.eTag(TAG, e);
                return null;
            } finally {
                try {
                    is.close();
                } catch (Exception e) {
                    LogUtils.eTag(TAG, e);
                    e.printStackTrace();
                }

                try {
                    if (charArrayWriter != null) {
                        charArrayWriter.close();
                    }
                } catch (Exception e) {
                    LogUtils.eTag(TAG, e);
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.eTag(TAG, e);
            return null;
        }
    }

    public static ArrayList<String> getStringListFromInputStream(File destFile) {
        //读取文件 的例子
        ArrayList<String> list = new ArrayList<>();
        String str = "just a test\n";
        String strRead = "";
        try {
            InputStream is = getInputStream(destFile);
            InputStreamReader input = new InputStreamReader(is, BaseUtilsConstance.fileCharsetName);
            BufferedReader reader = new BufferedReader(input);
            while ((str = reader.readLine()) != null) {
//                strRead += "\n" + str;
                list.add(str);
            }
            LogUtils.iTag(TAG, "lum:读取的文件是 " + strRead);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            LogUtils.eTag(TAG, e);
        }
        return list;
    }

    public static synchronized OutputStream getOutputStream(Context context, File destFile) {
        OutputStream out = null;
        try {
            if (!canWrite(destFile) && isOnExtSdCard(destFile)) {
                DocumentFile file = DocumentsUtils.getDocumentFile(destFile, false);
                if (file != null && file.canWrite()) {
                    out = context.getContentResolver().openOutputStream(file.getUri());
                }
            } else {

                out = new FileOutputStream(destFile);


            }
        } catch (FileNotFoundException e) {
            LogUtils.eTag(TAG, e);
            e.printStackTrace();
        }
        return out;
    }

    public static FileOutputStream getFileOutputStream(Context context, File destFile, boolean isAppend) {
        FileOutputStream out = null;
        try {

            if (!canWrite(destFile) && isOnExtSdCard(destFile)) {
                DocumentFile file = DocumentsUtils.getDocumentFile(destFile, false);
                if (file != null && file.canWrite()) {
                    ParcelFileDescriptor pfd = context.getContentResolver().openFileDescriptor(file.getUri(), "rw");
                    FileDescriptor fd = pfd.getFileDescriptor();
                    out = new FileOutputStream(fd);

                    System.out.println("FileOutputStream = ext cart");
                }
            } else {

                out = new FileOutputStream(destFile, isAppend);

                System.out.println("FileOutputStream = internal cart");
            }
        } catch (FileNotFoundException e) {
            System.out.println("FileOutputStream = no file");
            LogUtils.eTag(TAG, e);
            e.printStackTrace();
        }
        return out;
    }

    public static FileOutputStream getFileOutputStream(Context context, File destFile) {
        FileOutputStream out = null;
        try {

            if (!canWrite(destFile) && isOnExtSdCard(destFile)) {
                DocumentFile file = DocumentsUtils.getDocumentFile(destFile, false);
                if (file != null && file.canWrite()) {
                    ParcelFileDescriptor pfd = context.getContentResolver().openFileDescriptor(file.getUri(), "rw");
                    FileDescriptor fd = pfd.getFileDescriptor();
                    out = new FileOutputStream(fd);
                    System.out.println("FileOutputStream = ext cart");
                }
            } else {

                out = new FileOutputStream(destFile, true);

                System.out.println("FileOutputStream = internal cart");
            }
        } catch (FileNotFoundException e) {
            System.out.println("FileOutputStream = no file");
            LogUtils.eTag(TAG, e);
            e.printStackTrace();
        }
        return out;
    }

    public static boolean saveTreeUri(Context context, String rootPath, Uri uri) {
        DocumentFile file = DocumentFile.fromTreeUri(context, uri);
        if (file != null && file.canWrite()) {
//            SPUtils spUtils = SPUtils.getInstance("sdcard");
//            spUtils.put("sdcardpath", rootPath);
//            spUtils.put("uri", uri.toString());

            SharedPreferences perf = PreferenceManager.getDefaultSharedPreferences(context);
            perf.edit().putString(rootPath, uri.toString()).apply();
            return true;
        } else {
            LogUtils.eTag(TAG, "no write permission: " + rootPath);
        }
        return false;
    }

    public static boolean checkWritableRootPath(Context context, String rootPath) {
        File root = new File(rootPath);
//        LogUtils.dTag(TAG, "checkWritableRootPath");
//        LogUtils.dTag(TAG, "rootPath " + rootPath);
        if (!root.canWrite()) {
//            LogUtils.dTag(TAG, "!root.canWrite() " + !root.canWrite());
            if (DocumentsUtils.isOnExtSdCard(root)) {
                DocumentFile documentFile = DocumentsUtils.getDocumentFile(root, true);
//                LogUtils.dTag(TAG, "isOnExtSdCard " + rootPath);
                if (documentFile == null) {
                    return true;
                }
//                LogUtils.dTag(TAG, "documentFile.canWrite() " + documentFile.canWrite());
                return !documentFile.canWrite();
            } else {
                SharedPreferences perf = PreferenceManager.getDefaultSharedPreferences(context);
                String documentUri = perf.getString(rootPath, "");
                if (documentUri == null || documentUri.isEmpty()) {
//                    LogUtils.dTag(TAG, "documentUri == null || documentUri.isEmpty()");
                    return true;
                } else {
                    DocumentFile file = DocumentFile.fromTreeUri(context, Uri.parse(documentUri));
                    return !(file != null && file.canWrite());
                }
            }
        } else {
            LogUtils.dTag(TAG, "rootPath " + rootPath);
        }
        return false;
    }

    public static boolean writeFileFromoS(File file, String ss, boolean isAppend) {
        FileOutputStream fileOutputStream = getFileOutputStream(Utils.getApp(), file, isAppend);

        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream, BaseUtilsConstance.fileCharsetName));//写入到缓存流
            writer.write(ss);//从从缓存流写入
            writer.flush();
            writer.close();//关闭流

            return true;
        } catch (IOException e) {
            e.printStackTrace();
             LogUtils.eTag(TAG,e);
            return false;
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public static boolean writeFileFromoS(final OutputStream os, String ss) {

        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, BaseUtilsConstance.fileCharsetName));//写入到缓存流
            writer.write(ss);//从从缓存流写入
            writer.flush();
            writer.close();//关闭流

            return true;
        } catch (IOException e) {
            e.printStackTrace();

            return false;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}