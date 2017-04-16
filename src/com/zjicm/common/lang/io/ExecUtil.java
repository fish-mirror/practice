package com.zjicm.common.lang.io;

import com.zjicm.common.lang.consts.ExecConsts;
import com.zjicm.common.lang.util.StringUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;

/**
 * Created by yujing on 2017/4/15.
 */
public class ExecUtil {
    public static boolean autoOrient(String sourcePath, String targetPath) {
        if (StringUtil.isNotEmpty(sourcePath) && StringUtil.isNotEmpty(targetPath) &&
            ImageUtil.isImageMagickSafe(Paths.get(sourcePath).toFile())) {
            return exec(ExecConsts.Cmd.CONVERT, new String[]{sourcePath + "[0]", "-auto-orient", targetPath}, 20);
        }
        return false;
    }

    public static boolean makeSnap(String sourcePath, String targetPath, int imageWidth, int imageHeight) {
        if (StringUtil.isEmpty(sourcePath) || StringUtil.isEmpty(targetPath)) {
            return false;
        }
        if (ImageUtil.isImageMagickSafe(Paths.get(sourcePath).toFile())) {
            return exec(ExecConsts.Cmd.CONVERT,
                        new String[]{sourcePath + "[0]",
                                     "-thumbnail",
                                     imageWidth + "x" + imageHeight + ">",
                                     targetPath},
                        20);
        }
        return false;
    }

    public static boolean resizeImage(String sourcePath, String targetPath, int imageWidth, int imageHeight) {
        if (StringUtil.isEmpty(sourcePath) || StringUtil.isEmpty(targetPath)) {
            return false;
        }
        if (ImageUtil.isImageMagickSafe(Paths.get(sourcePath).toFile())) {
            return exec(ExecConsts.Cmd.CONVERT,
                        new String[]{sourcePath + "[0]",
                                     "-thumbnail",
                                     imageWidth + "x" + imageHeight + ">",
                                     targetPath},
                        20);
        }
        return false;
    }

    public static boolean cropImage(String sourcePath,
                                    String targetPath,
                                    int imageWidth,
                                    int imageHeight,
                                    int startX,
                                    int startY) {
        if (StringUtil.isEmpty(sourcePath) || StringUtil.isEmpty(targetPath)) {
            return false;
        }
        if (ImageUtil.isImageMagickSafe(Paths.get(sourcePath).toFile())) {
            return exec(ExecConsts.Cmd.CONVERT,
                        new String[]{sourcePath + "[0]",
                                     "-crop",
                                     imageWidth + "x" + imageHeight + "+" + startX + "+" + startY,
                                     targetPath},
                        20);
        }
        return false;
    }

    public static boolean makeSnap(String sourcePath, String targetPath, int imageWidth) {
        if (StringUtil.isEmpty(sourcePath) || StringUtil.isEmpty(targetPath)) {
            return false;
        }
        if (ImageUtil.isImageMagickSafe(Paths.get(sourcePath).toFile())) {
            return exec(ExecConsts.Cmd.CONVERT,
                        new String[]{sourcePath + "[0]", "-thumbnail", imageWidth + "x", targetPath},
                        20);
        }
        return false;
    }

    public static boolean applyGIFWatermark(String sourcePath, String targetPath, String watermark) {
        if (StringUtil.isEmpty(sourcePath) || StringUtil.isEmpty(targetPath) || StringUtil.isEmpty(watermark)) {
            return false;
        }
        return exec(ExecConsts.Cmd.CONVERT,
                    new String[]{sourcePath, ExecConsts.Options.COALESCE, ExecConsts.Options.GRAVITY,
                                 ExecConsts.Options.SOUTHEAST,
                                 ExecConsts.Options.GEOMETRY, "+0+0 null:", watermark,
                                 ExecConsts.Options.LAYERS,
                                 ExecConsts.Cmd.COMPOSITE,
                                 ExecConsts.Options.LAYERS,
                                 ExecConsts.Options.OPTIMIZE,
                                 targetPath},
                    20);
    }

    public static boolean convert(String sourcePath, String targetPath) {
        if (StringUtil.isEmpty(sourcePath) || StringUtil.isEmpty(targetPath)) {
            return false;
        }
        if (ImageUtil.isImageMagickSafe(Paths.get(sourcePath).toFile())) {
            return exec(ExecConsts.Cmd.CONVERT, new String[]{sourcePath + "[0]", targetPath}, 20);
        }
        return false;
    }

    public static boolean applyWatermark(String sourcePath, String targetPath, String watermark) {
        if (StringUtil.isEmpty(sourcePath) || StringUtil.isEmpty(targetPath) || StringUtil.isEmpty(watermark)) {
            return false;
        }
        if (ImageUtil.isImageMagickSafe(Paths.get(sourcePath).toFile())) {
            return exec(ExecConsts.Cmd.COMPOSITE,
                        new String[]{"-gravity", "southeast", "-dissolve", "80", watermark, sourcePath, targetPath},
                        20);
        }
        return false;
    }

    /**
     * 获取媒体文件的时长
     *
     * @param sourcePath
     * @return
     */
    public static StringBuilder getFileInfo(String sourcePath) {
        return execReturnString(ExecConsts.Cmd.FFPROBE, new String[]{sourcePath}, 20);
    }

    /**
     * 执行后返回信息
     *
     * @param command         命名信息
     * @param args            参数
     * @param timeoutInSecond 超市时间
     * @return
     */
    public static StringBuilder execReturnString(String command, String[] args, int timeoutInSecond) {
        StringBuilder buff = new StringBuilder();
        boolean result = false;
        try {
            result = exec(command, args, buff, timeoutInSecond);
            if (!result) {
                System.out.println(buff.toString());
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return buff;
    }

    public static boolean exec(String command, String[] args, int timeoutInSecond) {
        StringBuilder buff = new StringBuilder();
        boolean result = false;
        try {
            result = exec(command, args, buff, timeoutInSecond);
            if (!result) {
                System.out.println(buff.toString());
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return result;
    }

    private static boolean exec(String command, String[] args, StringBuilder errorBuff, int timeoutInSecond) throws
                                                                                                             IOException,
                                                                                                             InterruptedException {
        if (StringUtil.isEmpty(command)) {
            return false;
        }
        StringBuilder cmdBuff = new StringBuilder();
        cmdBuff.append(command);
        for (String arg : args) {
            if (StringUtil.isNotEmpty(arg)) {
                cmdBuff.append(' ');
                cmdBuff.append(arg);
            }
        }

        String cmd = cmdBuff.toString();
        System.out.println(cmd);

        Process proc = Runtime.getRuntime().exec(cmd);
        new StreamGobbler(proc.getErrorStream(), errorBuff).start();
        new StreamGobbler(proc.getInputStream()).start();

        proc.getOutputStream().close();

        int timeleft = timeoutInSecond <= 0 ? 5 : timeoutInSecond;// 单位秒
        timeleft *= 10;
        while ((timeleft--) > 0) {
            Thread.sleep(100);
            try {
                int exit = proc.exitValue();
                if (exit == 0) {
                    return true;
                }

                System.out.println("Exec " + cmd + " error");
                return false;
            } catch (Throwable e) {
            }
        }

        if (timeleft <= 0) {
            if (proc != null) {
                try {
                    proc.destroy();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Exec " + cmd + " time out");
            return false;
        }

        return true;
    }
}

class StreamGobbler extends Thread {
    private InputStream is;
    private StringBuilder buff = null;

    public StreamGobbler(InputStream is, StringBuilder buff) {
        this.is = is;
        this.buff = buff;
    }

    public StreamGobbler(InputStream is) {
        this(is, null);
    }

    public void run() {
        try {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                if (buff != null) {
                    buff.append(line);
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
