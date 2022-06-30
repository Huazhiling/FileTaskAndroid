package com.tuita.downloadutils.res;

import com.tuita.downloadutils.thread.AutoThread;
import com.tuita.downloadutils.thread.BlockThread;
import com.tuita.downloadutils.thread.SingleThread;

/**
 * Auto:系统根据文件大小以及下载文件参数自动选择是否分片
 * Single:只采用单线程下载文件
 * Block:优先采用分块下载，如果参数支持分块以及文件超过150M
 */
public enum LoadType {
    /**
     * 根据大小自动选择是否分块下载
     */
    Auto {
        @Override
        public void execute(FileProcess fileProcess) {
            new AutoThread(fileProcess).start();
        }
    },

    /**
     * 单线下载
     */
    Single {
        @Override
        public void execute(FileProcess fileProcess) {
            new SingleThread(fileProcess).start();
        }
    },
    /**
     * 多线分块下载
     */
    Block {
        @Override
        public void execute(FileProcess fileProcess) {
            new BlockThread(fileProcess).start();
        }
    };


    public void execute(FileProcess fileProcess) {
        throw new AbstractMethodError("unknown error");
    }
}
