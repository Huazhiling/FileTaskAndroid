package com.tuita.downloadutils.thread;

import android.os.Looper;

import com.tuita.downloadutils.res.FileProcess;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 这里的Single表示的是用一个线程去控制下载文件
 * 大文件分块下载请用{@link BlockThread}
 */
public class SingleThread extends Thread {
    private String url;
    private String name;
    private FileProcess mFileProcess;

    public SingleThread() {

    }

    public SingleThread(FileProcess fileProcess) {
        this.mFileProcess = fileProcess;
        this.url = fileProcess.getFileUrl();
        this.name = fileProcess.getFileName();
    }

    @Override
    public void run() {
        // 下载网络文件
        int bytesum = 0;
        int byteread = 0;
        try {
            URL url = new URL(this.url);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.addRequestProperty("Accept-Encoding", "identity");
            urlConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 11; Pixel 5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.91 Mobile Safari/537.36");
            InputStream inStream = urlConnection.getInputStream();
            int contentLength = urlConnection.getContentLength();
            FileOutputStream fs = new FileOutputStream(mFileProcess.getDefaultFileStorePath() + File.separator + "test.apk");
            byte[] buffer = new byte[102400];
            long startTime = System.currentTimeMillis();
            long saveByte = 0;
            long saveReadByte = 0;
            Looper.prepare();
            while ((byteread = inStream.read(buffer)) != -1) {
                long processTime = System.currentTimeMillis();
                //计算下载速度
                if ((processTime - startTime) / 1000 >= 1) {
                    saveByte = saveReadByte;
                    startTime = processTime;
                    saveReadByte = 0;
                } else {
                    saveReadByte += byteread;
                }
                bytesum += byteread;
                mFileProcess.getDownloadProcessListener().process(bytesum, byteread, contentLength, saveByte < 0 ? 0 : saveByte);
                fs.write(buffer, 0, byteread);
                fs.flush();
            }
            mFileProcess.getDownloadProcessListener().success(new File(mFileProcess.getDefaultFileStorePath() + File.separator + "test.apk"));
            Looper.loop();
            fs.close();
            inStream.close();
        } catch (MalformedURLException e) {
            mFileProcess.getDownloadProcessListener().failed(e, e.getMessage());
        } catch (IOException e) {
            mFileProcess.getDownloadProcessListener().failed(e, e.getMessage());
        }

    }
}
