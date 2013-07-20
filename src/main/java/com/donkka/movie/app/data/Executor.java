package com.donkka.movie.app.data;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Shane on 6/13/13.
 */
public class Executor {

    private static final int NUM_THREADS = 1;

    private static ExecutorService executor;

    private static void init(){
        if(executor == null)
            executor = Executors.newFixedThreadPool(NUM_THREADS);
    }

    public static void run(Runnable runner){
        if(executor == null)
            Executor.init();
        executor.execute(runner);
    }
}
