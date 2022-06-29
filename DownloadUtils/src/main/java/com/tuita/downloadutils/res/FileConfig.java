package com.tuita.downloadutils.res;

import android.os.Environment;

import java.io.File;

/**
 * 文件配置
 */
public class FileConfig {
    /**
     * 文件下载根路径
     */
    private String mFileRootPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    /**
     * 默认下载文件夹
     */
    private String mDefaultFileFolder = "download";
    /**
     * 默认的文件存储路径
     */
    private String mDefaultFileStorePath = mFileRootPath + mDefaultFileFolder;

    public FileConfig() {
        File parentFolder = new File(mDefaultFileStorePath);
        System.out.println(parentFolder.getAbsolutePath());
        if (!parentFolder.exists()) {
            parentFolder.mkdir();
        }
    }

    public String getFileRootPath() {
        return mFileRootPath;
    }

    public void setFileRootPath(String mFileRootPath) {
        this.mFileRootPath = mFileRootPath;
    }

    public String getDefaultFileFolder() {
        return mDefaultFileFolder;
    }

    public void setDefaultFileFolder(String mDefaultFileFolder) {
        this.mDefaultFileFolder = mDefaultFileFolder;
    }

    public String getDefaultFileStorePath() {
        return mDefaultFileStorePath;
    }

    public void setDefaultFileStorePath(String mDefaultFileStorePath) {
        this.mDefaultFileStorePath = mDefaultFileStorePath;
    }
}
