package com.xiaobao.httpdowmload;

import android.app.Application;
import com.lzy.okhttputils.OkHttpUtils;

/**
 * Created by zhangkangbin on 2016/5/25.
 */
public class AppContext extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //必须调用初始化
        OkHttpUtils.init(this);
    }
}
