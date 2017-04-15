package com.zjicm.common.lang.util;


import com.zjicm.common.lang.consts.StringConsts;
import com.zjicm.common.lang.io.CloseUtil;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class HashUtil {
    private static final String HMAC_SHA1 = "HmacSHA1";
    private static final String SHA_256 = "SHA-256";
    private static final String SHA_1 = "SHA-1";

    public static String md5(String value) {
        if (StringUtils.isNotEmpty(value)) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.reset();
                md.update(value.getBytes("UTF-8"));
                return ByteUtil.toHex(md.digest());
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return value;
    }

    public static String md5(String... strings) {
        if (ArrayUtils.isNotEmpty(strings)) {
            StringBuilder buff = new StringBuilder(128);
            for (String str : strings) {
                if (StringUtils.isNotEmpty(str)) {
                    if (buff.length() > 0) {
                        buff.append("::");
                    }
                    buff.append(str);
                }
            }
            return md5(buff.toString());
        }
        return null;
    }

    public static boolean matchMd5(String hash, String value) {
        if (StringUtils.isNotEmpty(hash)) {
            return hash.toLowerCase().equals(md5(value));
        }
        return false;
    }

    public static String sha256(String val) {
        if (val != null && val.length() > 0) {
            try {
                MessageDigest md = MessageDigest.getInstance(SHA_256);
                md.update(val.getBytes());
                return Base64Util.encodeToString(md.digest(), false);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static String hmac(byte[] data, byte[] key) {
        if (data.length > 0 && key.length > 0) {
            try {
                SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_SHA1);
                Mac mac = Mac.getInstance(HMAC_SHA1);
                mac.init(signingKey);
                return Base64Util.encodeToString(mac.doFinal(data), false);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static String hmac(String data, String key) {
        if (data != null && key != null) {
            return hmac(data.getBytes(), key.getBytes());
        }

        return null;
    }

    public static String sha1(String str) {
        return sha1(StringUtils.defaultString(str).getBytes());
    }

    public static String sha1(byte[] data) {
        if (data != null && data.length > 0) {
            try {
                return new String(ByteUtil.toHex(MessageDigest.getInstance(SHA_1).digest(data)));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static String sha1(File file) {
        if (file != null) {
            FileInputStream in = null;
            try {
                in = new FileInputStream(file);
                MessageDigest messagedigest = MessageDigest.getInstance(SHA_1);
                byte[] buff = new byte[1024 * 1024];
                int len = 0;
                while ((len = in.read(buff)) > 0) {
                    messagedigest.update(buff, 0, len);
                }
                return new String(ByteUtil.toHex(messagedigest.digest()));
            } catch (Throwable e) {
                e.printStackTrace();
            } finally {
                CloseUtil.closeQuiet(in);
            }
        }
        return StringConsts.EMPTY;
    }

    public static int hash(int k) {
        k ^= k >>> 16;
        k *= 0x85ebca6b;
        k ^= k >>> 13;
        k *= 0xc2b2ae35;
        k ^= k >>> 16;
        return k;
    }

    public static int hash(long value) {
        return (int) (value ^ (value >>> 32));
    }

    public static int hash(String value) {
        if (StringUtils.isNotEmpty(value)) {
            int hash = 0;
            char val[] = value.toCharArray();
            for (int i = 0; i < val.length; i++) {
                hash = 31 * hash + val[i];
            }
            return hash;
        }
        return 0;
    }
}
