package com.tuita.filedownloadt;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.tuita.downloadutils.FileDownload;
import com.tuita.downloadutils.listener.DownloadProcessListener;
import com.tuita.downloadutils.res.LoadType;
import com.tuita.downloadutils.utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mDownRv;
    private DownAdapter mAdapter;
    private ArrayList<Bean> list;
    private String[] url = {"https://dlied4.myapp.com/myapp/1104466820/cos.release-40109/10040714_com.tencent.tmgp.sgame_a841321_1.54.1.10_l9h7n0.apk"
            , "https://downv6.qq.com/qqweb/QQ_1/android_apk/Android_8.8.90.7975_537119415_64.apk"
            , "https://fmapp-cos.chinafamilymart.com.cn/image/20220509/1652071280352/fmapp_v2.6.10_2022-04-21_OpenInstall_release_2610_jiagu_sign.apk"};
    private String[] src = {"https://gimg0.baidu.com/gimg/src=https%3A%2F%2Fappdown.baidu.com%2Fimg%2F0%2F512_512%2F244d263299e954004a6475b171586157.png&app=2000&size=b189,257&n=0&g=4n&q=90&fmt=jpeg?sec=0&t=9f4f26a591eddc775acdaf04f8e7a02e"
            , "https://sqimg.qq.com/qq_product_operations/im/qqlogo/logo.png"
            , "https://fmapp-download.chinafamilymart.com.cn/bg/logo.png"};
    private String[] name = {"王者荣耀", "腾讯QQ", "Fa米家"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void initData() {
        for (int i = 0; i < url.length; i++) {
            Bean bean = new Bean();
            bean.setName(name[i]);
            bean.setSrc(src[i]);
            bean.setUrl(url[i]);
            list.add(bean);
        }
        mAdapter.notifyDataSetChanged();
        mAdapter.setAdapterItemClick((downAdapter, view, position) -> {
            FileDownload.newInstance()
                    .setDownloadPosition(position)
                    .download(list.get(position).getUrl(), LoadType.Single)
                    .setDownloadProcessListener(new DownloadProcessListener() {
                        @Override
                        public void failed(Exception e, String errorMsg) {
                            Toast.makeText(getBaseContext(), "下载出错了 ： " + errorMsg, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void success(File file) {
                            Bean bean = list.get(position);
                            Toast.makeText(getBaseContext(), bean.getName() + "下载完成", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void process(int bytesum, int byteread, int contentLength, long saveByte) {
                            Bean bean = list.get(position);
                            bean.setKbProcess(Utils.byteToString(bytesum) + " / " + Utils.byteToString(contentLength) + "(" + Utils.byteToString(saveByte) + "/s)");
                            bean.setProcess(Utils.longToIntProcess(bytesum, contentLength));
                            runOnUiThread(() -> downAdapter.notifyItemChanged(position));
                        }
                    })
                    .execute();
        });
    }

    private void initView() {
        list = new ArrayList<>();
        mDownRv = findViewById(R.id.down_rv);
        mAdapter = new DownAdapter(list, this);
        mDownRv.setAdapter(mAdapter);
    }
}