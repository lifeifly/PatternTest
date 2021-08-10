
package com.lifly.patterntest.sinleton;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.lifly.patterntest.sixprinciple.OCP.ImageLoader;
import com.lifly.patterntest.sixprinciple.OCP.last.ImageCache;
import com.lifly.patterntest.sixprinciple.OCP.last.MemoryCache;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageLoader3 {
    //默认内存缓存
    ImageCache mCache = new MemoryCache();

    //线程池
    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private volatile static ImageLoader3 I_INSTANCE;

    private ImageLoader3() {
    }

    public static ImageLoader3 getInstance() {
        if (I_INSTANCE == null) {
            synchronized (ImageLoader3.class) {
                if (I_INSTANCE == null) {
                    I_INSTANCE = new ImageLoader3();
                }
            }
        }
        return I_INSTANCE;
    }

    /*
    用户自定义缓存
     */
    public void setImageCache(ImageCache cache) {
        mCache = cache;
    }

    public void displayImage(final String url, final ImageView imageView) {
        //判断使用哪种缓存
        Bitmap bitmap = mCache.get(url);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            return;
        }
        imageView.setTag(url);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadImage(url);
                if (bitmap == null) return;
                if (imageView.getTag().equals(url)) {
                    imageView.setImageBitmap(bitmap);
                }
                mCache.put(url, bitmap);
            }
        });
    }

    public Bitmap downloadImage(String imageUrl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

}
