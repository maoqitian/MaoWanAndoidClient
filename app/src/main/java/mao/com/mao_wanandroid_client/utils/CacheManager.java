package mao.com.mao_wanandroid_client.utils;

import java.io.File;
import java.math.BigDecimal;

/**
 * @author maoqitian
 * @Description: 主要功能清除内/外缓存 缓存管理类
 * @date 2019/8/1 0001 11:23
 */
public class CacheManager {

    // 获取缓存文件
    private static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                // 如果下面还有文件
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 删除指定目录下文件及目录
     *
     * @param deleteThisPath
     * @param filePath
     * @return
     */
    public static void deleteFolderFile(File filePath, boolean deleteThisPath) {
        if (filePath != null) {
            try {
                if (filePath.isDirectory()) {// 如果下面还有文件
                    File files[] = filePath.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        deleteFolderFile(files[i].getAbsoluteFile(), true);
                    }
                }
                if (deleteThisPath) {
                    if (!filePath.isDirectory()) {// 如果是文件，删除
                        filePath.delete();
                    } else {// 目录
//                        if (filePath.listFiles().length == 0) {// 目录下没有文件或者目录，删除
//                            filePath.delete();
//                        }
                    }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * 格式化单位
     * @param size
     * @return
     */
    private static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "Byte";
        }

        double megaByte = size / 1024 / 1024;
        if (megaByte < 1) {
           BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(1, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(1, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(1, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }

        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(1, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }

    //获取缓存大小
    public static String getCacheSize(File file) throws Exception {
        return getFormatSize(getFolderSize(file));
    }
}
