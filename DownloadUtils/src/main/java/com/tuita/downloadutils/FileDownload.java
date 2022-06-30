package com.tuita.downloadutils;

import android.annotation.SuppressLint;

import com.tuita.downloadutils.download.FileTaskDownloadExecute;
import com.tuita.downloadutils.listener.DownloadProcessListener;
import com.tuita.downloadutils.listener.FileTaskExecute;
import com.tuita.downloadutils.res.FileProcess;
import com.tuita.downloadutils.res.LoadType;
import com.tuita.downloadutils.thread.BlockThread;
import com.tuita.downloadutils.thread.SingleThread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class FileDownload {
    private String mFileUrl;
    private String mFileName;
    private LoadType mLoadType;
    private SingleThread mSingleThreadRunnable;
    private BlockThread mBlockThreadRunnable;
    private int mDownloadPosition;
    private DownloadProcessListener mDownloadProcessListener;
    private volatile static FileDownload mFileDownLoad;
    /**
     * 任务映射
     * key - 任务Url
     * value - 任务体
     */
    private HashMap<String, FileTaskExecute> taskExecuteHashMap;

    /**
     * 默认同时下载队列
     */
    private int mDefaultDownloadNum = 1;
    /**
     * 最大同时下载队列
     */
    private int coreDownloadNum;
    /**
     * 最大等待队列
     */
    private int maxDownloadNum;

    /**
     * 文件处理器，最后的解析，下载都在这里面
     */
    private final FileProcess mFileProcessor = new FileProcess();

    public synchronized static FileDownload newInstance() {
        if (mFileDownLoad == null) {
            synchronized (FileDownload.class) {
                mFileDownLoad = new FileDownload();
            }
        }
        return mFileDownLoad;
    }

    private FileDownload() {
        this.coreDownloadNum = mDefaultDownloadNum;
        this.maxDownloadNum = Integer.MAX_VALUE;
        this.mSingleThreadRunnable = new SingleThread();
        this.mBlockThreadRunnable = new BlockThread();
        this.taskExecuteHashMap = new HashMap<>();
    }

    public FileDownload setDownloadPosition(int mDownloadPosition) {
        this.mDownloadPosition = mDownloadPosition;
        return this;
    }

    /**
     * 下载文件，已时间命名
     *
     * @param url
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public FileDownload download(String url) {
        return download(url, new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(new Date()));
    }

    /**
     * 下载文件，由用户自定义名字
     *
     * @param url
     * @param fileName
     * @return
     */
    public FileDownload download(String url, String fileName) {
        return download(url, fileName, LoadType.Auto);
    }

    public FileDownload download(String url, LoadType loadType) {
        return download(url, new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(new Date()), loadType);
    }

    /**
     * 下载文件，由用户指定下载方式
     *
     * @param url
     * @param fileName
     * @param loadType 下载方式，具体解释请看{@link LoadType}
     * @return
     */
    public FileDownload download(String url, String fileName, LoadType loadType) {
        this.mFileUrl = url;
        this.mFileName = fileName;
        this.mLoadType = loadType;
        return this;
    }

    /**
     * 设置最大可同时下载数
     *
     * @param coreDownloadNum
     * @return
     */
    public FileDownload setCoreDownloadNum(int coreDownloadNum) {
        this.coreDownloadNum = coreDownloadNum;
        return this;
    }

    /**
     * 设置最大队列数
     *
     * @param maxDownloadNum
     * @return
     */
    public FileDownload setMaxDownloadNum(int maxDownloadNum) {
        this.maxDownloadNum = maxDownloadNum;
        return this;
    }

    public FileDownload setDownloadProcessListener(DownloadProcessListener downloadProcessListener) {
        this.mDownloadProcessListener = downloadProcessListener;
        return this;
    }

    /**
     * 执行下载，添加任务到队列
     */
    public void execute() {
        if (taskExecuteHashMap.get(mFileUrl) != null) {
            return;
        }
        taskExecuteHashMap.put(mFileUrl, new FileTaskDownloadExecute());
        buildFileConfig();
        mLoadType.execute(mFileProcessor);
    }

    private void buildFileConfig() {
        if (mFileUrl == null || mFileUrl.isEmpty()) {

            return;
        }
        mFileProcessor.setFileUrl(mFileUrl);
        mFileProcessor.setFileName(mFileName);
        mFileProcessor.setLoadType(mLoadType);
        mFileProcessor.setDownloadProcessListener(mDownloadProcessListener);
        mFileProcessor.setSingleThreadRunnable(mSingleThreadRunnable);
        mFileProcessor.setBlockThreadRunnable(mBlockThreadRunnable);
    }
}