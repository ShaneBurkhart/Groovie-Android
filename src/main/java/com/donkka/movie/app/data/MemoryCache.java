package com.donkka.movie.app.data;


import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * Created by Shane on 6/13/13.
 */
public class MemoryCache{
    private static final int MAX_MEMORY_DIVISION = 2;
    private static BitmapCache cache;
    private static void init(){
        if(cache == null)
            cache = new BitmapCache((int) (Runtime.getRuntime().maxMemory() / MAX_MEMORY_DIVISION));
        System.out.println("Current Cach Size: " + cache.size() + "/" + cache.maxSize());
    }

    public static synchronized Bitmap get(String url){
        init();
        return cache.get(url.hashCode() + "");
    }

    public static synchronized void put(String url, Bitmap b){
        init();
        if(b != null && url != null)
            cache.put(url.hashCode() + "", b);
    }

    public static synchronized void clear(){
        init();
        cache.evictAll();
    }

    private static class BitmapCache extends LruCache<String, Bitmap> {
        public BitmapCache(int size){
            super(size);
        }

        @Override
        protected int sizeOf(String key, Bitmap value) {
            return value.getRowBytes() * value.getHeight();
        }

        @Override
        protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
            if(evicted){
                System.out.println("Recycled bitmap");
                oldValue.recycle();
            }
        }
    }
}
