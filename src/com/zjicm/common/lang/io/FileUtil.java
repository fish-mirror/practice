package com.zjicm.common.lang.io;

import com.zjicm.common.lang.consts.StringConsts;
import com.zjicm.common.lang.util.NumberUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public final class FileUtil {
    public static final int K = 1024;
    public static final int M = 1024 * 1024;

    public static final class Extension {
        private static final Map<String, String> FILE_TYPE = new HashMap<>();

        static {
            // 文件头对应关系参考来自：https://en.wikipedia.org/wiki/List_of_file_signatures
            FILE_TYPE.put("FFD8FFDB", "jpg");
            FILE_TYPE.put("FFD8FFE0", "jpeg");
            FILE_TYPE.put("FFD8FFE1", "jpeg");

            FILE_TYPE.put("89504E47", "png");
            FILE_TYPE.put("47494638", "gif");

            FILE_TYPE.put("FFFB", "mp3");
            FILE_TYPE.put("494433", "mp3");
            FILE_TYPE.put("52494646", "wav");
            FILE_TYPE.put("4F676753", "ogg");

            FILE_TYPE.put("D0CF11E0", "xls");
        }

        private static final Set<String> EXT_IMAGE = new HashSet<>();

        static {
            EXT_IMAGE.add("jpg");
            EXT_IMAGE.add("jpeg");
            EXT_IMAGE.add("png");
            EXT_IMAGE.add("gif");
        }

        private static final Set<String> EXT_STREAMING_MEDIA = new HashSet<>();

        static {
            EXT_STREAMING_MEDIA.add("mp3");
            EXT_STREAMING_MEDIA.add("wav");
            EXT_STREAMING_MEDIA.add("ogg");
        }

        private static final Set<String> EXT_COMPRESS = new HashSet<>();

        static {
            EXT_COMPRESS.add("rar");
            EXT_COMPRESS.add("zip");
            EXT_COMPRESS.add("gz");
            EXT_COMPRESS.add("tar");
            EXT_COMPRESS.add("7z");
        }

        private static final Set<String> EXT_MS_OFFICE = new HashSet<>();

        static {
            EXT_MS_OFFICE.add("doc");
            EXT_MS_OFFICE.add("docx");
            EXT_MS_OFFICE.add("dot");
            EXT_MS_OFFICE.add("xls");
            EXT_MS_OFFICE.add("xlsx");
            EXT_MS_OFFICE.add("ppt");
            EXT_MS_OFFICE.add("pps");
            EXT_MS_OFFICE.add("pptx");
        }

        private static final Set<String> EXT_RAW_TEXT = new HashSet<>();

        static {
            EXT_RAW_TEXT.add("txt");
            EXT_RAW_TEXT.add("xml");
            EXT_RAW_TEXT.add("html");
            EXT_RAW_TEXT.add("htm");
        }

        private static final Set<String> EXT_ALLOWED = new HashSet<>();

        static {
            EXT_ALLOWED.addAll(EXT_IMAGE);
            EXT_ALLOWED.addAll(EXT_RAW_TEXT);
            EXT_ALLOWED.addAll(EXT_COMPRESS);
            EXT_ALLOWED.addAll(EXT_MS_OFFICE);
            EXT_ALLOWED.addAll(EXT_STREAMING_MEDIA);
            EXT_ALLOWED.add("swf");
            EXT_ALLOWED.add("flv");
            EXT_ALLOWED.add("fla");
            EXT_ALLOWED.add("swc");
            EXT_ALLOWED.add("swt");
            EXT_ALLOWED.add("pdf");
            EXT_ALLOWED.add("pdg");
            EXT_ALLOWED.add("caj");
            EXT_ALLOWED.add("csv");
            EXT_ALLOWED.add("wps");
            EXT_ALLOWED.add("chm");
            EXT_ALLOWED.add("mht");
            EXT_ALLOWED.add("mp3");
            EXT_ALLOWED.add("mp4");
            EXT_ALLOWED.add("wav");
            EXT_ALLOWED.add("wmv");
            EXT_ALLOWED.add("dat");
            EXT_ALLOWED.add("rm");
            EXT_ALLOWED.add("wma");
            EXT_ALLOWED.add("mpg");
            EXT_ALLOWED.add("rmvb");
            EXT_ALLOWED.add("mpa");
            EXT_ALLOWED.add("m15");
            EXT_ALLOWED.add("m1v");
            EXT_ALLOWED.add("mp2");
            EXT_ALLOWED.add("ram");
        }

        private static final Set<String> EXT_GIF = new HashSet<>();

        static {
            EXT_GIF.add("gif");
        }

        private static final Set<String> EXT_EMBEDABLE = new HashSet<>();

        static {
            EXT_EMBEDABLE.addAll(EXT_IMAGE);
            EXT_EMBEDABLE.add("swf");
            EXT_EMBEDABLE.add("flv");
        }

        private static Map<String, String> SYNONYM = new HashMap<>();

        static {
            SYNONYM.put("ppt", "ppt");
            SYNONYM.put("pps", "ppt");
            SYNONYM.put("pptx", "ppt");
            SYNONYM.put("dpt", "ppt");

            SYNONYM.put("doc", "doc");
            SYNONYM.put("dot", "doc");
            SYNONYM.put("docx", "doc");

            SYNONYM.put("xls", "xls");
            SYNONYM.put("xlsx", "xls");

            SYNONYM.put("gz", "rar");
            SYNONYM.put("tar", "rar");
            SYNONYM.put("rar", "rar");
            SYNONYM.put("zip", "rar");
        }

        public static String getUnifiedExtension(String ext) {
            if (StringUtils.isNotEmpty(ext)) {
                String uext = SYNONYM.get(ext);
                return uext == null ? ext : uext;
            }

            return StringConsts.EMPTY;
        }

        public static boolean isEmbedable(String filename) {
            return contains(filename, EXT_EMBEDABLE);
        }

        public static boolean isStreamingMedia(String filename) {
            return contains(filename, EXT_STREAMING_MEDIA);
        }

        public static boolean isGIF(String filename) {
            return contains(filename, EXT_GIF);
        }

        public static boolean isMsOffice(String filename) {
            return contains(filename, EXT_MS_OFFICE);
        }

        public static boolean isRawText(String filename) {
            return contains(filename, EXT_RAW_TEXT);
        }

        public static boolean isImage(String filename) {
            return contains(filename, EXT_IMAGE);
        }

        public static boolean isAllowed(String filename) {
            return contains(filename, EXT_ALLOWED);
        }

        public static boolean isAllowed(byte[] bytes) {
            if (bytes == null || bytes.length < 4) return false;
            String fileTypeHexString = bytesToHexString(ArrayUtils.subarray(bytes, 0, 4));
            return EXT_ALLOWED.contains(getFileType(fileTypeHexString));
        }

        /**
         * byte数组转换成16进制字符串
         *
         * @param src
         * @return
         */
        public static String bytesToHexString(byte[] src) {
            StringBuilder stringBuilder = new StringBuilder();
            if (src == null || src.length <= 0) {
                return null;
            }
            for (int i = 0; i < src.length; i++) {
                int v = src[i] & 0xFF;
                String hv = Integer.toHexString(v);
                if (hv.length() < 2) {
                    stringBuilder.append(0);
                }
                stringBuilder.append(hv);
            }
            return stringBuilder.toString().toUpperCase();
        }

        private static String getFileType(String fileTypeHexString) {
            Iterator<Map.Entry<String, String>> entryiterator = FILE_TYPE.entrySet().iterator();
            while (entryiterator.hasNext()) {
                Map.Entry<String, String> entry = entryiterator.next();
                String fileTypeHexValue = entry.getKey();
                if (fileTypeHexString.toUpperCase().startsWith(fileTypeHexValue)) {
                    return entry.getValue();
                }
            }
            return null;
        }

        private static boolean contains(String file, Set<String> exts) {
            if (StringUtils.isNotEmpty(file)) {
                return exts.contains(getExtension(file, null));
            }
            return false;
        }
    }

    public static final class ContentType {
        public static boolean checkFileType(String mime, String type) {
            if (mime == null || type == null) {
                return false;
            }

            return mime.startsWith(type);
        }

        public static boolean isImage(String mime) {
            return checkFileType(mime, "image");
        }

        public static boolean isAudio(String mime) {
            return checkFileType(mime, "audio");
        }

        public static boolean isText(String mime) {
            return checkFileType(mime, "text");
        }

        public static boolean isVideo(String mime) {
            return checkFileType(mime, "video");
        }
    }

    public static String getFilePath(String originalFilename) {
        if (StringUtils.isEmpty(originalFilename)) {
            return StringConsts.EMPTY;
        }

        StringBuilder buff = new StringBuilder();
        buff.append(new SimpleDateFormat("yyyy/MM/dd/HH").format(new Date()));
        buff.append('/');
        buff.append(NumberUtil.random(8));
        String ext = getExtension(originalFilename);
        if (StringUtils.isNotEmpty(ext)) {
            buff.append('.');
            buff.append(getExtension(originalFilename));
        }

        return buff.toString();
    }

    public static String getSizeDisplay(int length) {
        return getSizeDisplay(length, 1);
    }

    public static String getSizeDisplay(long length, int precision) {
        if (length < K) {
            return String.valueOf(length);
        }

        if (length < M) {
            double f = (double) Math.round((double) length / K * precision) / precision;
            return f + "K";
        }

        double f = (double) Math.round((double) length / M * precision) / precision;
        return f + "M";
    }

    public static String getExtension(String name) {
        return getExtension(name, "");
    }

    public static void createDirs(File file) {
        if (file == null) {
            return;
        }

        File path = file.isDirectory() ? file : file.getParentFile();
        if (!path.exists()) {
            if (!path.mkdirs()) {
                throw new RuntimeException("mkdirs failed");
            }
        }
    }

    public static String getExtension(String name, String _default) {
        if (StringUtils.isEmpty(name)) {
            return _default;
        }
        int i = name.lastIndexOf('.');
        if (i >= 0) {
            return name.substring(i + 1).toLowerCase();
        }
        return _default;
    }

    public boolean isDir(String name) {
        return new File(name).isDirectory();
    }

    public static String getFileName(String path) {
        try {
            char separatorChar = '/';
            int index = path.lastIndexOf(separatorChar);
            if (index < 0) {
                separatorChar = '\\';
                index = path.lastIndexOf(separatorChar);
            }
            return path.substring(index + 1);
        } catch (Exception ex) {
            return "Unknown";
        }
    }

    public static String getFileNameWithoutExtension(String path) {
        if (StringUtils.isNotEmpty(path)) {
            int i = path.lastIndexOf('.');
            if (i != -1) {
                return path.substring(0, i);
            }
            return path;
        }
        return StringConsts.EMPTY;
    }

    public static void delete(File dir) {
        if (dir != null) {
            if (dir.isDirectory()) {
                File[] files = dir.listFiles();
                if (ArrayUtils.isNotEmpty(files)) {
                    for (int i = 0; i < files.length; i++) {
                        delete(files[i]);
                    }
                }
            }

            if (!dir.delete()) {
                throw new RuntimeException("file delete failed:" + dir.getAbsolutePath());
            }
        }
    }

    public static String getMD5(String file) {
        if (StringUtils.isNotEmpty(file)) {
            return getMD5(new File(file));
        }
        return StringConsts.EMPTY;
    }

    private static File tempPath = null;

    public static File getTempPath() {
        if (tempPath == null) {
            synchronized (FileUtil.class) {
                if (tempPath == null) {
                    File _temp = new File(System.getProperty("java.io.tmpdir"));
                    if (!_temp.exists()) {
                        _temp = new File(System.getProperty("user.home"));
                    }

                    tempPath = _temp;
                }
            }
        }

        return tempPath;
    }

    public static String getMD5(File file) {
        if (file != null && file.exists()) {
            InputStream is = null;
            try {
                is = new FileInputStream(file);
                return DigestUtils.md5Hex(is);
            } catch (Throwable e) {
                e.printStackTrace();
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (Throwable e) {
                    }
                }
            }
        }

        return StringConsts.EMPTY;
    }

    public static String getMD5(byte[] bytes) {
        if (bytes != null) {
            try {
                return DigestUtils.md5Hex(bytes);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        return StringConsts.EMPTY;
    }

    public static void write(String content, File target) {
        if (StringUtils.isNotEmpty(content) && target != null) {
            File path = target.getParentFile();
            if (path.exists() || path.mkdirs()) {
                BufferedWriter writer = null;
                try {
                    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(target), "UTF-8"));
                    writer.write(content);
                    writer.flush();
                } catch (Throwable e) {
                    e.printStackTrace();
                } finally {
                    if (writer != null) {
                        try {
                            writer.close();
                        } catch (IOException e) {
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println(Extension.isAllowed(FileUtils.readFileToByteArray(new File(
                "/Users/yuanjq/Music/MusicConverter/test.mp3"))));
        System.out.println(Extension.isAllowed(FileUtils.readFileToByteArray(new File(
                "/Users/yuanjq/Music/MusicConverter/dxycomvoice.wav"))));
        System.out.println(Extension.isAllowed(FileUtils.readFileToByteArray(new File(
                "/Users/yuanjq/Music/MusicConverter/dxycomvoice.ogg"))));
    }
}
