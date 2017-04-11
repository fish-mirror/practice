package com.zjicm.common.lang.util;

import com.zjicm.common.Server;
import com.zjicm.common.lang.character.consts.CharacterConsts;

import java.util.ArrayList;
import java.util.Collection;

public final class ByteUtil {
    /**
     * 字节数组转16进制
     *
     * @param bytes
     * @return
     */
    public static String toHex(byte[] bytes) {
        if (bytes != null && bytes.length > 0) {
            StringBuilder buff = new StringBuilder(bytes.length << 1);
            String tmp = null;
            for (int i = 0; i < bytes.length; i++) {
                tmp = (Integer.toHexString(bytes[i] & 0xFF));
                if (tmp.length() == 1) {
                    buff.append(CharacterConsts.ZERO);
                }
                buff.append(tmp);
            }
            return buff.toString();
        }

        return null;
    }

    /**
     * 16进制转字节数组
     *
     * @param hex
     * @return
     */
    public static byte[] fromHex(String hex) {
        if (hex != null && hex.length() > 1) {
            try {
                byte bytes[] = new byte[hex.length() / 2];
                for (int i = 0; i < bytes.length; i++) {
                    bytes[i] = (byte) Integer.parseInt(hex.substring(i << 1, (i << 1) + 2), 16);
                }
                return bytes;
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     * 字符串转字节数组
     *
     * @param s
     * @return
     */
    public static byte[] getBytes(String s) {
        if (s != null) {
            try {
                return s.getBytes(Server.CHARSET);
            } catch (Throwable e) {
            }
        }

        return null;
    }

    /**
     * 字符型集合中的字符串转为字节数组
     *
     * @param ss
     * @return
     */
    public static Collection<byte[]> getBytes(Collection<String> ss) {
        Collection<byte[]> rv = new ArrayList<byte[]>(ss.size());
        for (String s : ss) {
            rv.add(getBytes(s));
        }
        return rv;
    }

    /**
     * int型转字节数组
     *
     * @param res
     * @return
     */
    public static byte[] fromInt(int res) {
        byte[] targets = new byte[4];
        targets[0] = (byte) (res & 0xff);// 最低位
        targets[1] = (byte) ((res >> 8) & 0xff);// 次低位
        targets[2] = (byte) ((res >> 16) & 0xff);// 次高位
        targets[3] = (byte) (res >>> 24);// 最高位,无符号右移。
        return targets;
    }

    /**
     * 字节数组转int型
     *
     * @param res
     * @return
     */
    public static int toInt(byte[] res) {
        return (res[0] & 0xff) | ((res[1] << 8) & 0xff00) | ((res[2] << 24) >>> 8) | (res[3] << 24);
    }
}
