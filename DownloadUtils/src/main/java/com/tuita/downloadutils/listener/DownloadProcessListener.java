package com.tuita.downloadutils.listener;

import java.io.File;

public interface DownloadProcessListener {
    /**
     * 下载失败回调，返回错误信息
     *
     * @param errorMsg
     */
    void failed(Exception exception,String errorMsg);

    /**
     * 下载完成后回调
     *
     * @param file 下载后的文件
     */
    void success(File file);

    /**
     * 下载中的回调
     *
     * @param bytesum       当前已下载大小
     * @param byteread      每次获取数据大小
     * @param contentLength 下载文件总大小
     * @param saveByte      每秒下载速率
     */
    void process(int bytesum, int byteread, int contentLength, long saveByte);
}
