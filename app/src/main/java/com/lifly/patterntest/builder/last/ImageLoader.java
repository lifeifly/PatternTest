
package com.lifly.patterntest.builder.last;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.lifly.patterntest.sixprinciple.OCP.last.ImageCache;
import com.lifly.patterntest.sixprinciple.OCP.last.MemoryCache;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageLoader {
   //配置对象
    private ImageLoaderConfig mConfig;

    //线程池
    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private volatile static ImageLoader I_INSTANCE;

    private ImageLoader() {
    }



    public static ImageLoader getInstance() {
        if (I_INSTANCE == null) {
            synchronized (ImageLoader.class) {
                if (I_INSTANCE == null) {
                    I_INSTANCE = new ImageLoader();
                }
            }
        }
        return I_INSTANCE;
    }
    public void init(ImageLoaderConfig config){
        this.mConfig=config;

    }

    public void setThreadCount(int count){
        mExecutorService.shutdown();
        mExecutorService=null;
        mExecutorService=Executors.newFixedThreadPool(count);
    }
    public void displayImage(final String url, final ImageView imageView) {
        //判断使用哪种缓存
        Bitmap bitmap = mConfig.mCache.get(url);
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
                mConfig.mCache.put(url, bitmap);
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
