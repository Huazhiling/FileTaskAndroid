package com.tuita.downloadutils.thread;

import com.tuita.downloadutils.res.FileProcess;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class AutoThread extends Thread {
    private String url;
    private String name;
    private FileProcess mFileProcess;

    public AutoThread() {

    }

    public AutoThread(FileProcess fileProcess) {
        this.mFileProcess = fileProcess;
        this.url = fileProcess.getFileUrl();
        this.name = fileProcess.getFileName();
    }

    @Override
    public void run() {

        try {
            URL url = new URL(this.url);
            URLConnection urlConnection = url.openConnection();
            urlConnection.addRequestProperty("Accept-Encoding", "identity");
            urlConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 11; Pixel 5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.91 Mobile Safari/537.36");
            urlConnection.getConnectTimeout();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
