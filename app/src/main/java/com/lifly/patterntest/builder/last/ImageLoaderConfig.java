package com.lifly.patterntest.builder.last;


import com.lifly.patterntest.sixprinciple.OCP.ImageLoader;
import com.lifly.patterntest.sixprinciple.OCP.last.ImageCache;
import com.lifly.patterntest.sixprinciple.OCP.last.MemoryCache;

public class ImageLoaderConfig {
    //图片缓存配置对象
    ImageCache mCache = new MemoryCache();
    //加载图片时的loading和加载失败的图片配置对象
    DisplayConfig displayConfig = new DisplayConfig();
    //加载策略
    LoadPolicy loadPolicy = new SerialPolicy();
    //线程数量
    int threadCount = Runtime.getRuntime().availableProcessors() + 1;

    public ImageLoaderConfig() {
    }


    /**
     * 配置类的Builder
     */
    public static class Builder {
        //图片缓存配置对象
        ImageCache mCache = new MemoryCache();
        //加载图片时的loading和加载失败的图片配置对象
        DisplayConfig displayConfig = new DisplayConfig();
        //加载策略
        LoadPolicy loadPolicy = new SerialPolicy();
        //线程数量
        int threadCount = Runtime.getRuntime().availableProcessors() + 1;

        public Builder setThreadCount(int count) {
            threadCount = count;
            return this;
        }

        public Builder setCache(ImageCache cache) {
            this.mCache = cache;
            return this;
        }

        public Builder setLoadPolicy(LoadPolicy policy) {
            if (policy != null) {
                loadPolicy = policy;
            }
            return this;
        }

        public Builder setLoadingFailedImage(int resId) {
            this.displayConfig.loadingFailedResId = resId;
            return this;
        }

        public Builder setLoadingImage(int resId) {
            displayConfig.loadingResId = resId;
            return this;
        }

        void applyConfig(ImageLoaderConfig config){
            config.displayConfig=displayConfig;
            config.mCache=mCache;
            config.loadPolicy=loadPolicy;
            config.threadCount=threadCount;
        }

        public ImageLoaderConfig create(){
            ImageLoaderConfig config=new ImageLoaderConfig();
            applyConfig(config);
            return config;
        }
    }
}
