package com.lifly.patterntest.sixprinciple.OCP.last;

import android.graphics.Bitmap;

/**
 * 可以让用户扩展接口注入自己的缓存实现
 */
public interface ImageCache {
    /*
    缓存
     */
    void put(String url, Bitmap bitmap);

    /*
    获取缓存
     */
    Bitmap get(String url);
}
