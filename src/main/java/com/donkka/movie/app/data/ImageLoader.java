package com.donkka.movie.app.data;

import android.graphics.Bitmap;
import android.os.Handler;

import com.donkka.movie.lib.scaleimage.ScaleImageView;

/**
 * Created by Shane on 6/13/13.
 */
public class ImageLoader implements Runnable{

    public String url;
    public ScaleImageView imageView;
    Handler handler;

    public ImageLoader(String url, ScaleImageView image, Handler handler){
        this.url = url;
        this.imageView = image;
        this.handler = handler;
    }

    @Override
    public void run() {
        handler.post(new ImageViewChangerRunnable(this.imageView, this.loadImage(this.url)));
    }

    private Bitmap loadImage(String url) {
        Bitmap b;
        if((b = MemoryCache.get(url)) != null){
            System.out.println("Loading Image from cache");
            return b;
        }
        if((b = FileCache.get(url)) != null){
            //Add to memory cache
            MemoryCache.put(url, b);
            return b;
        }
        System.out.println("Downloading Image and Caching");
        FileCache.download(url);
        b = FileCache.get(url);
        MemoryCache.put(url, b);
        return b;
    }

    private class ImageViewChangerRunnable implements Runnable{
        private ScaleImageView imageView;
        private Bitmap bitmap;
        private ImageViewChangerRunnable(ScaleImageView imageView, Bitmap bitmap){
            this.imageView = imageView;
            this.bitmap = bitmap;
        }
        @Override
        public void run() {
            imageView.setImageBitmap(this.bitmap);
        }
    }
}