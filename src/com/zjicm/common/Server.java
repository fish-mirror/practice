package com.zjicm.common;

import com.zjicm.common.lang.consts.StringConsts;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * 服务器字符编码，ID，GROUP，environmentd等 需要运行时传入jvm参数
 */
public final class Server {
    public static final String CHARSET = StringConsts.CHARSET_UTF8;
    public static final int ID = Integer.valueOf(System.getProperty("server.id", "1"));
    public static final int GROUP = Integer.valueOf(System.getProperty("server.group", "1"));
    public static final int GLOBAL_ID = GROUP * 10000 + ID;
    public static final ScheduledExecutorService SCHEDULER = new ScheduledThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors() * 4);
    public static final Executor EXECUTOR = SCHEDULER;

    public static final boolean DEV = "dev".equals(System.getProperty("environment"));
    public static final boolean QAS = "qas".equals(System.getProperty("environment"));
    public static final boolean PRD = !(DEV || QAS);

    public static final boolean MASTER = "master".equals(System.getProperty("server.role"));
    public static final boolean TASK = "task".equals(System.getProperty("server.role"));
}
