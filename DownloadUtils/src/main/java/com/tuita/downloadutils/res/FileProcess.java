package com.tuita.downloadutils.res;

import com.tuita.downloadutils.listener.DownloadProcessListener;
import com.tuita.downloadutils.thread.BlockThread;
import com.tuita.downloadutils.thread.SingleThread;

/**
 * 文件配置
 */
public class FileProcess {

    private DownloadProcessListener mDownloadProcessListener;
    private String mFileUrl;
    private String mFileName;
    private LoadType mLoadType;
    private SingleThread mSingleThreadRunnable;
    private BlockThread mBlockThreadRunnable;
    private FileConfig mFileConfig = new FileConfig();

    public FileProcess() {

    }

    public DownloadProcessListener getDownloadProcessListener() {
        return mDownloadProcessListener;
    }

    public void setDefaultFileStorePath(String mDefaultFileStorePath) {
        mFileConfig.setDefaultFileStorePath(mDefaultFileStorePath);
    }

    public String getDefaultFileStorePath() {
        return mFileConfig.getDefaultFileStorePath();
    }

    public void setDownloadProcessListener(DownloadProcessListener mDownloadProcessListener) {
        this.mDownloadProcessListener = mDownloadProcessListener;
    }

    public void setFileUrl(String mFileUrl) {
        this.mFileUrl = mFileUrl;
    }

    public void setFileName(String mFileName) {
        this.mFileName = mFileName;
    }

    public void setLoadType(LoadType mLoadType) {
        this.mLoadType = mLoadType;
    }

    public void setSingleThreadRunnable(SingleThread mSingleThreadRunnable) {
        this.mSingleThreadRunnable = mSingleThreadRunnable;
    }

    public void setBlockThreadRunnable(BlockThread mBlockThreadRunnable) {
        this.mBlockThreadRunnable = mBlockThreadRunnable;
    }

    public String getFileUrl() {
        return mFileUrl;
    }

    public String getFileName() {
        return mFileName;
    }

    public LoadType getLoadType() {
        return mLoadType;
    }

    public SingleThread getSingleThreadRunnable() {
        return mSingleThreadRunnable;
    }

    public BlockThread getBlockThreadRunnable() {
        return mBlockThreadRunnable;
    }
}
