package com.xiaobao.httpdowmload;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.lzy.okhttputils.callback.FileCallback;

import java.io.File;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhangkangbin on 2016/5/25.
 */
public class FileDownLoadCallback extends FileCallback {

    public FileDownLoadCallback(String destFileName) {
        super(destFileName);
    }

    public FileDownLoadCallback(@NonNull String destFileDir, @NonNull String destFileName) {
        super(destFileDir, destFileName);
    }

    @Override
    public void onResponse(boolean isFromCache, File file, Request request, @Nullable Response response) {

    }

    /**
     * 执行下载过程中的进度回调，UI线程
     *
     * @param currentSize  当前下载的字节数
     * @param totalSize    总共需要下载的字节数
     * @param progress     当前下载的进度
     * @param networkSpeed 当前下载的速度   字节/秒
     */
    public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {

        super.downloadProgress(currentSize, totalSize, progress, networkSpeed);
    }
}
