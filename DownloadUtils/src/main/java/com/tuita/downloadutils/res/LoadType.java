package com.tuita.downloadutils.res;

/**
 * Auto:系统根据文件大小以及下载文件参数自动选择是否分片
 * Single:只采用单线程下载文件
 * Block:优先采用分块下载，如果参数支持分块以及文件超过150M
 */
public enum LoadType {
    /**
     * 根据大小自动选择是否分块下载
     */
    Auto,
    /**
     * 单线下载
     */
    Single,
    /**
     * 多线分块下载
     */
    Block
}
