package com.zjicm.common.lang.sql;

import com.zjicm.common.lang.function.Consumer;
import com.zjicm.common.lang.sql.util.JdbcUtil;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Map;

public class ConnectionBroker implements Serializable {
    private static final long serialVersionUID = 1779195623158455737L;
    private String url;
    private String username;
    private String password;
    private Map<Integer, String> urls;

    public ConnectionBroker() {
        try {
            DriverManager.registerDriver((Driver) (Class.forName(getDriverName()).newInstance()));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    protected String getDriverName() {
        return "com.mysql.jdbc.Driver";
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void useConnection(Consumer<Connection> processor) {
        if (processor != null) {
            Connection conn = null;
            try {
                conn = get();
                processor.accept(conn);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            } finally {
                JdbcUtil.closeQuiet(conn);
            }
        }
    }

    public void useConnection(int key, Consumer<Connection> processor) {
        if (processor != null) {
            Connection conn = null;
            try {
                conn = get(key);
                processor.accept(conn);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            } finally {
                JdbcUtil.closeQuiet(conn);
            }
        }
    }

    private Connection get() throws Throwable {
        return DriverManager.getConnection(url, username, password);
    }

    private Connection get(int key) throws Throwable {
        if (urls != null && urls.size() > 0) {
            String url = urls.get(key);
            if (url != null) {
                return DriverManager.getConnection(url, username, password);
            }
        }

        return get();
    }

    public Map<Integer, String> getUrls() {
        return urls;
    }

    public void setUrls(Map<Integer, String> urls) {
        this.urls = urls;
    }
}
