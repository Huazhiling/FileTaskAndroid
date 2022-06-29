package com.tuita.downloadutils.res;

/**
 * 用来保存下载状态
 */
public enum DownloadType {
    /**
     * 正在下载
     */
    DOWNLOAD,
    /**
     * 手动暂停下载
     */
    PAUSE,
    /**
     * 任务添加在队列，等待下载
     */
    EXECUTE,
    /**
     * 手动取消
     */
    CANCEL
}
