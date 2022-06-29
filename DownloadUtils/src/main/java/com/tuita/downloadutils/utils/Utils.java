package com.tuita.downloadutils.utils;

import java.text.DecimalFormat;

/**
 * 时间计算以及百分比计算等工具类
 */
public class Utils {
    private static long kbMaxSize = 1024;
    private static long mbMaxSize = kbMaxSize * 1024;
    private static long gbMaxSize = mbMaxSize * 1024;
    private static DecimalFormat mSizeDecimal = new DecimalFormat("0.00");

    /**
     * 将文件大小的Byte转成感知的单位大小
     *
     * @return
     */
    public static String byteToString(long byteSize) {
        if (byteSize >= gbMaxSize) {
            return byteToStringGB(byteSize);
        } else if (byteSize >= mbMaxSize) {
            return byteToStringMB(byteSize);
        } else if (byteSize >= kbMaxSize) {
            return byteToStringKB(byteSize);
        } else {
            return String.format(byteSize + "%s", "B");
        }
    }


    /**
     * 将文件大小的Byte转成感知的K单位大小
     *
     * @param byteSize
     * @return
     */
    private static String byteToStringKB(long byteSize) {
        return String.format(mSizeDecimal.format((double) byteSize / kbMaxSize) + "%s", "KB");
    }

    /**
     * 将文件大小的Byte转成感知的M单位大小
     *
     * @param byteSize
     * @return
     */
    private static String byteToStringMB(long byteSize) {
        return String.format(mSizeDecimal.format((double) byteSize / mbMaxSize) + "%s", "MB");
    }

    /**
     * 将文件大小的Byte转成感知的G单位大小
     *
     * @param byteSize
     * @return
     */
    private static String byteToStringGB(long byteSize) {
        return String.format(mSizeDecimal.format((double) byteSize / gbMaxSize) + "%s", "GB");
    }

    /**
     * 根据当前process进度计算成100%的百分比进度
     *
     * @param currentProcess
     * @return
     */
    public static int longToIntProcess(long currentProcess, long maxProcess) {
        if (currentProcess <= 0 || currentProcess > maxProcess || maxProcess < 100) {
            return 0;
        }
        return (int) (currentProcess / (maxProcess / 100));
    }
}
