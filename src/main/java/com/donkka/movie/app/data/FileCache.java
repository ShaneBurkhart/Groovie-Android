package com.donkka.movie.app.data;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Shane on 6/13/13.
 */
public class FileCache {
    private static final String TMP_DIR_NAME = "_tmp";

    private static File dir;
    private static Activity a;

    public static void init(Activity a){
        if(a == null)
            return;
        FileCache.a = a;
        if(dir == null || (!isStorageAvailable() && FileCache.dir != FileCache.a.getExternalCacheDir())){
            if(isStorageAvailable()){
                FileCache.dir = new File(FileCache.a.getExternalFilesDir(null), TMP_DIR_NAME);
                if(!FileCache.dir.exists())
                    FileCache.dir.mkdir();
            }else
                FileCache.dir = FileCache.a.getCacheDir();
        }
    }

    public static void download(String url){
        System.out.println("Downloading...");
        File f = FileCache.getFile(url);
        if(f.exists())
            return;
        writeBitmapToFile(f, url);
    }

    public static Bitmap get(String key){
        System.out.println("Getting from File Cache!!");
        File f = FileCache.getFile(key);
        if(!f.exists())
            return null;
        return BitmapFactory.decodeFile(f.getPath());
    }

    public static void clearCache(){
        FileCache.init(FileCache.a);
        for (File f : FileCache.dir.listFiles())
            f.delete();
    }

    public static void remove(String key){
        File f = FileCache.getFile(key);
        if(f.exists())
            f.delete();
    }

    public static boolean isStorageAvailable(){
        boolean mExternalStorageAvailable = false;
        boolean mExternalStorageWriteable = false;
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // We can read and write the media
            mExternalStorageAvailable = mExternalStorageWriteable = true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            // We can only read the media
            mExternalStorageAvailable = true;
            mExternalStorageWriteable = false;
        } else {
            // Something else is wrong. It may be one of many other states, but all we need
            //  to know is we can neither read nor write
            mExternalStorageAvailable = mExternalStorageWriteable = false;
        }
        return mExternalStorageAvailable && mExternalStorageWriteable;
    }

    private static void writeBitmapToFile(File f, String url){
        try{
            InputStream is = (InputStream) new URL(url).getContent();
            FileOutputStream fos = new FileOutputStream(f);
            int count = 0;
            byte[] buffer = new byte[1024];
            while((count = is.read(buffer)) > -1){
                fos.write(buffer, 0, count);
            }
            fos.flush();
            fos.close();
            is.close();
        } catch (IOException e){
            System.out.println("FileCache: writeBitmapToFile: " + e.getMessage());
        }
    }

    private static File getFile(String key){
        FileCache.init(FileCache.a);
        return new File(FileCache.dir, key.hashCode() + "");
    }
}
