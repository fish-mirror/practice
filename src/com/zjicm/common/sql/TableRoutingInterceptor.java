package com.zjicm.common.sql;

import com.dxy.base.util.StringUtil;
import com.dxy.commons.sql.dao.PartitionContext;
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
        if (StringUtil.isNotEmpty(table, _table, stmt) && !table.equals(_table) && stmt.indexOf(_table) == -1) {
            stmt = stmt.replaceAll(table, _table);
        }
        return stmt;
    }
}
