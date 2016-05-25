package com.xiaobao.httpdowmload;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.FileCallback;

import java.io.File;

import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textView;
    ProgressBar progressbar;
    String url = "http://dl.apk-downloaders.com/apps/2015/11/26/recyclerviewanimators%201.1.3_[www.Downloader-Apk.com].apk";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        findViewById(R.id.btn_start).setOnClickListener(this);
        findViewById(R.id.btn_stop).setOnClickListener(this);

           /*     .execute(new FileCallback("/sdcard/temp/", "testecyclervie.apk") {  //文件下载时，需要指定下载的文件目录和文件名
                    @Override
                    public void onResponse(boolean isFromCache, File file, Request request, @Nullable Response response) {
                        // file 即为文件数据，文件保存在指定布幕
                    }
                });*/
    }

    // m
    private String getSize(long size) {

        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;

        if (size >= gb) {
            return String.format("%.1f GB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        } else
            return String.format("%d B", size);
    }

    private void stop() {

        //根据 Tag 取消请求
        OkHttpUtils.getInstance().cancelTag(url);
    }

    private void start() {


        int name_index = url.lastIndexOf("/");

        String name = url.substring(name_index, url.length());

        OkHttpUtils.get(url)//
                .tag(url)//

                /** * @param currentSize  当前下载的字节数
                 * @param totalSize    总共需要下载的字节数
                 * @param progress     当前下载的进度
                 * @param networkSpeed 当前下载的速度   字节/秒
                 *                     */
                .execute(new FileCallback("/sdcard/apktemp/", name) {

                    public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                        super.downloadProgress(currentSize, totalSize, progress, networkSpeed);

                        textView.setText("下载--" + getSize(currentSize) + "大小--" + getSize(totalSize) + "当前下载的进度" + progress + "--当前下载的速度--" + getSize(networkSpeed));
                        Log.i("test", "progress--" + progress);
                        int p = (int) (progress * 100);
                        progressbar.setProgress(p);
                    }

                    @Override
                    public void onResponse(boolean isFromCache, File file, Request request, @Nullable Response response) {

                        Toast.makeText(getApplicationContext(), "is form cache " + isFromCache, Toast.LENGTH_LONG).show();


                    }
                });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_start:

                start();

                break;
            case R.id.btn_stop:

                stop();

                break;

            default:
                break;

        }

    }
}
