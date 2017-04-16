package com.zjicm.common.lang.util;

import com.zjicm.common.lang.consts.StringConsts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedList;

public final class RuntimeUtil {
    private static String OS = System.getProperty("os.name").toLowerCase();
    private static final Collection<Runnable> HOOKS = new LinkedList<Runnable>();

    static {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                for (Runnable hook : HOOKS) {
                    try {
                        hook.run();
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public static void registerShutdownHook(Runnable hook) {
        synchronized (HOOKS) {
            if (hook != null && !HOOKS.contains(hook)) {
                HOOKS.add(hook);
            }
        }
    }

    public static String exec(String[] cmds, int timeoutInSeconds) {
        StringBuilder buff = new StringBuilder();
        try {
            return exec(cmds, buff, timeoutInSeconds);
        } finally {
            if (buff.length() > 0) {
                System.out.println(buff.toString());
            }
        }
    }

    public static String exec(String[] cmds, StringBuilder errorBuff) {
        return exec(cmds, errorBuff, 1200);
    }


    public static String exec(String[] cmds, StringBuilder errorBuff, int timeoutInSeconds) {
        return exec(cmds, errorBuff, null, timeoutInSeconds);
    }

    public static String exec(String[] cmds, StringBuilder errorBuff, StringBuilder stdBuff, int timeoutInSeconds) {
        try {
            if (cmds == null) {
                return "please input a correct command!";
            }
            StringBuilder cmdBuff = new StringBuilder();
            for (String cmd : cmds) {
                if (cmd != null) {
                    cmdBuff.append(cmd);
                    cmdBuff.append(' ');
                }
            }

            String cmd = cmdBuff.toString();
            System.out.println(cmd);
            if (cmd == null || cmd.length() == 0) {
                return "please input a correct command!";
            }

            Process proc = Runtime.getRuntime().exec(cmd);
            new StreamGobbler(proc.getErrorStream(), errorBuff).start();
            new StreamGobbler(proc.getInputStream(), stdBuff).start();

            proc.getOutputStream().close();

            int timeleft = timeoutInSeconds * 10;// 单位100毫秒
            while ((timeleft--) > 0) {
                Thread.sleep(100);
                try {
                    int exit = proc.exitValue();
                    if (exit == 0) {
                        return "success";
                    }

                    return "error code:" + exit;
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

                System.out.println(new Date());
                System.out.println("time out");

                return "time out";
            }

            return StringConsts.SUCCESS;
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return "unkown error";
    }

    private static final class StreamGobbler extends Thread {
        private InputStream is;
        private StringBuilder buff = null;

        public StreamGobbler(InputStream is, StringBuilder buff) {
            this.is = is;
            this.buff = buff;
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

    public static String getLocalAddress() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) interfaces.nextElement();
                Enumeration<InetAddress> addresses = ni.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress address = (InetAddress) addresses.nextElement();
                    if (address != null && address instanceof Inet4Address && !address.isLoopbackAddress()) {
                        return address.getHostAddress();
                    }
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return "0.0.0.0";
    }

    public static boolean isMacOS() {
        return OS.indexOf("mac") >= 0 && OS.indexOf("os") > 0;
    }
}
