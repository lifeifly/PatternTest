package com.lifly.patterntest.sixprinciple.ISP;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.lifly.patterntest.sixprinciple.SRP.ImageCache;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * SD卡缓存
 */
public class DiskCahce extends ImageCache {
    //缓存目录路径
    private static String cacheDir = "sdcard/cache/";

    //从缓存中读取图片
    public Bitmap get(String url) {
        return BitmapFactory.decodeFile(cacheDir + url);
    }

    //将图片缓存到内存中
    public void put(String url, Bitmap bitmap) {
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(cacheDir + url);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            CloseUtils.closeQuietly(fos);
        }
    }
}
