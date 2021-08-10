
package com.lifly.patterntest.builder;

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
    //默认内存缓存
    ImageCache mCache = new MemoryCache();
    //图片加载中显示的图片id
    int mLoadingImageId;
    //加载失败的图片Id
    int mLoadingFailedImageId;
    //图片加载策略
    LoaderPolicy mLoaderPolicy;
    //线程池
    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private volatile static ImageLoader I_INSTANCE;

    private ImageLoader() {
    }

    public void setLoadingImage(int loadingImageId) {
        this.mLoadingImageId = loadingImageId;
    }

    public void setLoaderPolicy(LoaderPolicy loaderPolicy) {
        this.mLoaderPolicy = loaderPolicy;
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

    /*
    用户自定义缓存
     */
    public void setImageCache(ImageCache cache) {
        mCache = cache;
    }

    public void setLoadingFailedImageId(int loadingFailedImageId) {
        this.mLoadingFailedImageId = loadingFailedImageId;
    }
    public void setThreadCount(int count){
        mExecutorService.shutdown();
        mExecutorService=null;
        mExecutorService=Executors.newFixedThreadPool(count);
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
