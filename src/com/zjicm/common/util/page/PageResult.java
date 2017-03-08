package com.zjicm.common.util.page;

import com.dxy.commons.pager.PageBuilder;

import java.io.Serializable;
import java.util.List;

public class PageResult<V> implements Serializable {
    private static final long serialVersionUID = 2150299738100832543L;
    private List<V> result;
    private PageBuilder pageBuilder;
    private int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public PageResult() {
        pageBuilder = new PageBuilder();
    }

    /**
     * @return Returns the result.
     */
    public List getResult() {
        return result;
    }

    /**
     * @param result The result to set.
     */
    public void setResult(List<V> result) {
        this.result = result;
    }

    /**
     * @return Returns the pageBuilder.
     */
    public PageBuilder getPageBuilder() {
        return pageBuilder;
    }

    /**
     * @param pageBuilder The pageBuilder to set.
     */
    public void setPageBuilder(PageBuilder pageBuilder) {
        this.pageBuilder = pageBuilder;
    }

    public void setParameters(int total, int pge, int limit) {
        this.pageBuilder.items(total);
        this.pageBuilder.itemsPerPage(limit);
        this.pageBuilder.page(pge);
    }

    public int getStart() {
        return this.pageBuilder.offset();
    }

    public int getLimit() {
        return this.pageBuilder.length();
    }

    public String getPagerJsonStr() {
        StringBuilder pageJson = new StringBuilder();
        pageJson.append("total_items");
        pageJson.append("\":");
        pageJson.append(this.getPageBuilder().getItems());
        pageJson.append(",\"");

        pageJson.append("items_per_page");
        pageJson.append("\":");
        pageJson.append(this.getPageBuilder().itemsPerPage());
        pageJson.append(",\"");

        pageJson.append("current_item_count");
        pageJson.append("\":");
        pageJson.append(this.getLimit());
        pageJson.append(",\"");

        pageJson.append("total_pages");
        pageJson.append("\":");
        pageJson.append(this.getPageBuilder().pages());
        pageJson.append(",\"");

        pageJson.append("page_index");
        pageJson.append("\":");
        pageJson.append(this.getPageBuilder().page());
        pageJson.append(",\"");

        pageJson.append("start_index");
        pageJson.append("\":");
        pageJson.append(this.getPageBuilder().beginIndex());

        return pageJson.toString();
    }

}
