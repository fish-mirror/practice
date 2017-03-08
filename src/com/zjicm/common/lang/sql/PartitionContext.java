package com.zjicm.common.lang.sql;

import com.dxy.base.consts.StringConsts;
import com.dxy.base.util.NumberUtil;

public final class PartitionContext {
    private static final ThreadLocal<Integer> DATABASE = new ThreadLocal<Integer>();
    private static final ThreadLocal<String> TABLE = new ThreadLocal<String>();
    public static final int SIZE = NumberUtil.parseIntQuietly(System.getProperty("server.db.partitions"), 16);

    public static void set(Integer partition, String table) {
        setTable(table);
        setPartitionSeed(partition);
    }

    public static void clear() {
        clearTable();
        clearPartitionSeed();
    }

    public static void setTable(String table) {
        if (table != null && table.length() > 0) {
            try {
                TABLE.set(table);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    public static String getTable() {
        try {
            return TABLE.get();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return StringConsts.EMPTY;
    }

    public static void clearTable() {
        try {
            TABLE.remove();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static void setPartitionSeed(Integer partition) {
        if (partition != null) {
            try {
                DATABASE.set(partition);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    public static String getPartitionKey() {
        try {
            Integer partition = DATABASE.get();
            if (partition != null && partition != 0) {
                return (partition < 0 ? "S" : "M") + Math.abs(partition);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return "default";
    }

    public static void clearPartitionSeed() {
        try {
            DATABASE.remove();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
