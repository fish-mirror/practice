package com.zjicm.common.lang.io;

import com.zjicm.common.Server;
import com.zjicm.common.lang.consts.StringConsts;
import com.zjicm.common.lang.util.Base64Util;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Collection;

public final class IoUtil {
    public static String getBase64ImageExtension(String data) {
        if (StringUtils.isNotEmpty(data)) {
            int start = data.indexOf("image/");
            int end = data.indexOf(';');
            if (start != -1 && end > start) {
                return data.substring(start + "image/".length(), end);
            }
        }
        return StringConsts.EMPTY;
    }

    public static void saveBase64ImageToFile(File file, String data) {
        if (StringUtils.isNotEmpty(data)) {
            byte[] bytes = getBase64ImageFileByteArray(data);
            if (ArrayUtils.isNotEmpty(bytes)) {
                IoUtil.writeToFile(bytes, file);
            }
        }
    }

    public static byte[] getBase64ImageFileByteArray(String data) {
        int idx = data.indexOf(";base64,");
        if (idx != -1) {
            return Base64Util.decode(data.substring(idx + (";base64,".length())));
        }
        return null;
    }


    public static void md5ToPath(StringBuilder buff, String md5) {
        if (StringUtils.isNotEmpty(md5) && buff != null) {
            for (int i = 0; i < 3; i++) {
                if (i > 0) {
                    buff.append("/");
                }
                buff.append(md5.substring(i * 2, i * 2 + 2));
            }
        }
    }

    public static String readToString(Class<?> clazz, String resource) {
        File file = getResourceAsFile(clazz, resource);
        if (file != null) {
            try {
                return readToString(file);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String readToString(InputStream is) throws IOException {
        if (is != null) {
            Writer writer = new StringWriter();
            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                is.close();
            }
            return writer.toString();
        }
        return null;
    }

    public static void readToFile(InputStream is, File file) throws IOException {
        if (is != null && file != null) {
            OutputStream os = null;
            try {
                os = new FileOutputStream(file);
                byte[] buff = new byte[1024];
                int n;
                while ((n = is.read(buff)) != -1) {
                    os.write(buff, 0, n);
                }
                os.flush();
            } finally {
                CloseUtil.closeQuiet(is);
                CloseUtil.closeQuiet(os);
            }
        }
    }

    public static void readStringLineByLine(Collection<String> container, File source) throws IOException {
        if (container != null) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(source));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    container.add(line);
                }
                reader.close();
            } finally {
                CloseUtil.closeQuiet(reader);
            }
        }
    }

    public static String readToString(File source) throws IOException {
        return readToString(source, Server.CHARSET);
    }

    public static String readToString(File source, String encoding) throws IOException {
        byte[] data = readToBytes(source);
        if (data != null) {
            return encoding == null ? new String(data) : new String(data, encoding);
        }
        return null;
    }

    public static byte[] readToBytes(File source) throws IOException {
        if (source != null) {
            FileChannel sourceChannel = null;
            FileInputStream sourceInputStream = null;
            WritableByteChannel targetChannel = null;
            try {
                sourceInputStream = new FileInputStream(source);
                sourceChannel = sourceInputStream.getChannel();
                ByteArrayOutputStream baos = new ByteArrayOutputStream((int) sourceChannel.size());
                targetChannel = Channels.newChannel(baos);
                sourceChannel.transferTo(0, sourceChannel.size(), targetChannel);
                return baos.toByteArray();
            } finally {
                CloseUtil.closeQuiet(sourceInputStream);
                CloseUtil.closeQuiet(sourceChannel);
                CloseUtil.closeQuiet(targetChannel);
            }
        }
        return null;
    }

    public static File getResourceAsFile(Class<?> clazz, String name) {
        if (clazz != null && name != null) {
            URL url = clazz.getResource(name);
            if (url != null) {
                String file = url.getFile();
                if (file != null && file.length() > 0) {
                    return new File(file);
                }
            }
        }
        return null;
    }

    public static void writeToFile(byte[] bytes, File output) {
        if (bytes != null && bytes.length > 0 && output != null) {
            if (!output.getParentFile().exists()) {
                output.getParentFile().mkdirs();
            }

            FileOutputStream os = null;
            try {
                os = new FileOutputStream(output);
                os.write(bytes);
                os.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                CloseUtil.closeQuiet(os);
            }
        }
    }

    public static File mkdirIfPossible(File file) {
        if (file != null) {
            if (file.isDirectory()) {
                if (!file.exists()) {
                    file.mkdirs();
                }
            } else {
                File path = file.getParentFile();
                if (!path.exists()) {
                    path.mkdirs();
                }
            }
        }
        return file;
    }
}
