package com.zjicm.common.lang.consts;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

public final class FileConsts {
    public static final int[] AVATAR_SIZES = new int[]{48, 64, 90, 120};
    public static final File WEBINF = new File(FileConsts.class.getClassLoader()
                                                               .getResource("log4j2.xml")
                                                               .getFile()).getParentFile().getParentFile();
    public static final String WATERMARK = new File(WEBINF, "bin/watermark.png").getAbsolutePath();
    private static final int[] WATERMARK_SIZES = new int[]{2560, 2048, 1600, 1024, 640, 250};
    private static final Map<Integer, String> WATERMARKS = new HashMap<Integer, String>();

    static {
        for (int size : WATERMARK_SIZES) {
            WATERMARKS.put(size, new File(WEBINF, "bin/watermark_" + size + ".png").getAbsolutePath());
        }
    }

    public static String getWatermarkPath(int width) {
        for (int size : WATERMARK_SIZES) {
            if (width > size) {
                return WATERMARKS.get(size);
            }
        }
        return WATERMARK;
    }
}
