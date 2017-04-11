package com.zjicm.common.lang.sql.util;

import com.zjicm.common.lang.consts.StringConsts;
import com.zjicm.common.lang.function.Consumer;
import com.zjicm.common.lang.function.Function;
import com.zjicm.common.lang.sql.ConnectionBroker;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

public final class JdbcUtil {
    private static final Pattern TABLE_NAME_PATTERN = Pattern.compile("^[a-z0-9_\\$\\.]+$", Pattern.CASE_INSENSITIVE);


    public static boolean isValidTableName(String name) {
        if (StringUtils.isNotEmpty(name)) {
            return TABLE_NAME_PATTERN.matcher(name).matches();
        }
        return false;
    }

    public static String quote(String value) {
        if (value == null) {
            return "NULL";
        } else {
            StringBuilder var1 = new StringBuilder("\'");

            for (int var2 = 0; var2 < value.length(); ++var2) {
                char var3 = value.charAt(var2);
                switch (var3) {
                    case '\u0000':
                        var1.append('\\');
                        var1.append(var3);
                        break;
                    case '\n':
                        var1.append('\\');
                        var1.append('n');
                        break;
                    case '\r':
                        var1.append('\\');
                        var1.append('r');
                        break;
                    case '\u001a':
                        var1.append('\\');
                        var1.append('Z');
                        break;
                    case '\"':
                        var1.append('\\');
                        var1.append('\"');
                        break;
                    case '\'':
                        var1.append('\\');
                        var1.append('\'');
                        break;
                    case '\\':
                        var1.append('\\');
                        var1.append('\\');
                        break;
                    default:
                        var1.append(var3);
                }
            }

            var1.append('\'');
            return var1.toString();
        }
    }

    public static void rollback(Connection conn) {
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static String escape(String keyword) {
        if (StringUtils.isNotEmpty(keyword)) {
            return StringEscapeUtils.escapeSql(keyword);
        }
        return keyword;
    }

    public static void closeQuiet(Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Throwable e) {
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (Throwable e) {
            }
        }
    }

    public static void closeQuiet(Connection conn) {
        if (conn != null) {
            try {
                if (!conn.getAutoCommit()) {
                    conn.setAutoCommit(true);
                }
            } catch (Throwable e) {
            }

            try {
                conn.close();
            } catch (Throwable e) {
            }
        }
    }

    public static void closeQuiet(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (Throwable e) {
            }
        }
    }

    public static void closeQuiet(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Throwable e) {
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (Throwable e) {
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (Throwable e) {
            }
        }
    }

    /**
     * @param broker
     * @param table
     * @param prefix
     * @param resultSetProcessor
     * @throws Throwable
     */
    public static void iterate(ConnectionBroker broker,
                               String table,
                               String prefix,
                               Consumer<ResultSet> resultSetProcessor) throws Throwable {
        iterate(broker, table, prefix, null, null, 500, null, resultSetProcessor);
    }

    /**
     * @param broker
     * @param table
     * @param prefix
     * @param suffix
     * @param column
     * @param batchSize
     * @param statementProcessor
     * @param resultSetProcessor
     * @throws Throwable
     */
    public static void iterate(ConnectionBroker broker,
                               final String table,
                               final String prefix,
                               final String suffix,
                               final String column,
                               final int batchSize,
                               final Consumer<PreparedStatement> statementProcessor,
                               final Consumer<ResultSet> resultSetProcessor) throws Throwable {
        if (broker != null) {
            broker.useConnection(new Consumer<Connection>() {
                @Override
                public void accept(Connection conn) {
                    try {
                        iterate(conn, table, prefix, suffix, column, batchSize, statementProcessor, resultSetProcessor);
                    } catch (Throwable e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }

    /**
     * @param conn
     * @param table
     * @param prefix
     * @param resultSetProcessor
     * @throws SQLException
     */
    public static void iterate(Connection conn,
                               String table,
                               String prefix,
                               Consumer<ResultSet> resultSetProcessor) throws SQLException {
        iterate(conn, table, prefix, null, null, 500, null, resultSetProcessor);
    }

    /**
     * @param conn
     * @param table
     * @param prefix
     * @param suffix
     * @param column
     * @param batchSize
     * @param statementProcessor
     * @param resultSetProcessor
     * @throws SQLException
     */
    public static void iterate(Connection conn,
                               String table,
                               String prefix,
                               String suffix,
                               String column,
                               int batchSize,
                               Consumer<PreparedStatement> statementProcessor,
                               Consumer<ResultSet> resultSetProcessor) throws SQLException {
        if (conn != null && resultSetProcessor != null && StringUtils.isNotEmpty(table)) {
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                int max = 0;
                stmt = conn.prepareStatement(
                        "select max(" + StringUtils.defaultIfEmpty(column, "id") + ") from " + table);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    max = rs.getInt(1);
                }
                rs.close();
                stmt.close();
                if (max > 0) {
                    StringBuilder buff = new StringBuilder();
                    buff.append(StringUtils.defaultIfEmpty(prefix, "select *"));
                    buff.append(" from ");
                    buff.append(table);
                    buff.append(" where ");
                    buff.append(StringUtils.defaultIfEmpty(column, "id"));
                    buff.append(" between ? and ?");
                    if (StringUtils.isNotEmpty(suffix)) {
                        buff.append(" and (");
                        buff.append(suffix);
                        buff.append(")");
                    }
                    int offset = 0;
                    while (offset < max + batchSize) {
                        stmt = conn.prepareStatement(buff.toString());
                        stmt.setInt(1, offset);
                        stmt.setInt(2, offset + batchSize - 1);
                        if (statementProcessor != null) {
                            statementProcessor.accept(stmt);
                        }

                        rs = stmt.executeQuery();
                        while (rs.next()) {
                            resultSetProcessor.accept(rs);
                        }
                        rs.close();
                        stmt.close();
                        offset += batchSize;
                    }
                }
            } finally {
                closeQuiet(stmt, rs);
            }
        }
    }

    public static void useConnection(DataSource dataSource, Consumer<Connection> processor) {
        useConnection(dataSource, processor, false);
    }

    public static void useConnection(DataSource dataSource, Consumer<Connection> processor, boolean transaction) {
        if (dataSource != null && processor != null) {
            Connection conn = null;
            try {
                conn = dataSource.getConnection();
                if (transaction) {
                    conn.setAutoCommit(false);
                }
                processor.accept(conn);
                if (transaction) {
                    conn.commit();
                }
            } catch (Throwable e) {
                if (transaction) {
                    rollback(conn);
                }
                throw new RuntimeException(e);
            } finally {
                closeQuiet(conn);
            }
        }
    }

    public static <T> T useConnection(DataSource dataSource, Function<Connection, T> func) {
        return useConnection(dataSource, func, false);
    }

    public static <T> T useConnection(DataSource dataSource, Function<Connection, T> func, boolean transaction) {
        if (dataSource != null && func != null) {
            Connection conn = null;
            try {
                conn = dataSource.getConnection();
                if (transaction) {
                    conn.setAutoCommit(false);
                }
                T value = func.apply(conn);
                if (transaction) {
                    conn.commit();
                }
                return value;
            } catch (Throwable e) {
                if (transaction) {
                    rollback(conn);
                }

                throw new RuntimeException(e);
            } finally {
                closeQuiet(conn);
            }
        }
        return null;
    }
}
