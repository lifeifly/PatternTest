package com.lifly.patterntest.sixprinciple.OCP;

import android.graphics.Bitmap;

import com.lifly.patterntest.sixprinciple.SRP.ImageCache;

/**
 * 双缓存，先从内存缓存获取再从SD中获取
 * 缓存图片也是图片和SD卡各缓存一份
 */
public class DoubleCache {
    ImageCache mMemoryCache = new ImageCache();
    DiskCahce mDiskCache = new DiskCahce();

    //先从内粗 缓存中获取，没有再从SD中获取
    public Bitmap get(String url) {
        Bitmap bitmap = mMemoryCache.get(url);
        if (bitmap == null) {
            bitmap = mDiskCache.get(url);
        }
        return bitmap;
    }

    //将图片缓存到内存和SD卡
    public void put(String url, Bitmap bitmap) {
        mMemoryCache.put(url, bitmap);
        mDiskCache.put(url, bitmap);
    }
}
