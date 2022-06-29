package com.tuita.downloadutils.thread;


import com.tuita.downloadutils.res.FileProcess;

/**
 * 下载大文件用的线程，单独开线程池
 */
public class BlockThread extends Thread{
    private String url;
    private String name;
    private FileProcess mFileProcess;

    public BlockThread() {

    }

    public BlockThread(FileProcess fileProcess) {
        this.mFileProcess = fileProcess;
        this.url = fileProcess.getFileUrl();
        this.name = fileProcess.getFileName();
    }


    @Override
    public void run() {

    }
}
