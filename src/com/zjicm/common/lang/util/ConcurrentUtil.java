package com.zjicm.common.lang.util;

import com.zjicm.common.Server;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public final class ConcurrentUtil {
    public static void sleepQuiet(long timeout) {
        if (timeout > 0) {
            try {
                Thread.sleep(timeout);
            } catch (Throwable e) {
            }
        }
    }

    public static boolean execute(final Runnable runnable, long timeout) {
        if (runnable != null) {
            final CountDownLatch latch = new CountDownLatch(1);
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        runnable.run();
                    } catch (Throwable e) {
                        e.printStackTrace();
                    } finally {
                        latch.countDown();
                    }
                }
            });
            thread.start();
            try {
                latch.await(timeout, TimeUnit.MILLISECONDS);
                if (latch.getCount() > 0) {
                    thread.interrupt();
                    return false;
                }
            } catch (Throwable e) {
                e.printStackTrace();
                return false;
            }

            return true;
        }
        return false;
    }

    public static void execute(Runnable runnable) {
        if (runnable != null) {
            Server.EXECUTOR.execute(runnable);
        }
    }

    public static <T> T retryOnFailure(Callable<T> callable, int retryTimes, int delayInMillis) {
        return retryOnFailure(callable, retryTimes, delayInMillis, null);
    }

    public static <T> T retryOnFailure(Callable<T> callable, int retryTimes, int delayInMillis, T def) {
        if (callable != null) {
            if (retryTimes <= 0) {
                retryTimes = Short.MAX_VALUE;
            }

            for (int i = 0; i < retryTimes; i++) {
                try {
                    return ObjectUtil.nullToDefault(callable.call(), def);
                } catch (Throwable e) {
                    e.printStackTrace();
                }

                if (delayInMillis > 0) {
                    ConcurrentUtil.sleepQuiet(delayInMillis);
                }
            }
        }
        return def;
    }
}
