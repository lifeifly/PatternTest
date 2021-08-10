package com.lifly.patterntest.sixprinciple.ISP;

import java.io.Closeable;
import java.io.IOException;

public class CloseUtils {
    private CloseUtils() {
    }

    /**
     * Closeable只暴露了close方法，不会担心暴露其他方法
     * @param closeable
     */
    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
