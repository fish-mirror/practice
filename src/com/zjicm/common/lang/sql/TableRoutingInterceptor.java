package com.zjicm.common.lang.sql;

import org.apache.commons.lang.StringUtils;
import org.hibernate.EmptyInterceptor;

public class TableRoutingInterceptor extends EmptyInterceptor {
    private static final long serialVersionUID = -4398374440690082265L;
    private String table;

    public TableRoutingInterceptor(String table) {
        this.table = table;
    }

    @Override
    public String onPrepareStatement(String sql) {
        String stmt = super.onPrepareStatement(sql);
        String _table = PartitionContext.getTable();
        if (StringUtils.isNotEmpty(table) &&
            StringUtils.isNotEmpty(_table) &&
            StringUtils.isNotEmpty(stmt) &&
            !table.equals(_table) &&
            !stmt.contains(_table)) {
            stmt = stmt.replaceAll(table, _table);
        }
        return stmt;
    }
}
