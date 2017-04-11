package com.zjicm.common.lang.io;

import java.io.Closeable;

public final class CloseUtil {
    public static void closeQuiet(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable e) {
            }
        }
    }
}
