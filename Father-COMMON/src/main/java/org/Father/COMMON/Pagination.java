package org.Father.COMMON;

import java.io.Serializable;

import pcitc.imp.common.ettool.baseresrep.BaseResRep;

/*
 * 翻页实现类
 * 模块编号：pcitc_wm_common_class_Pagination
 * 作    者：pcitc
 * 创建时间：2017/09/21
 * 修改编号：1
 * 描    述：翻页实现类
 */
public class Pagination extends BaseResRep implements Serializable {

    /**
     * 总记录数
     */
    private long total;

    /*
    * 页面条数
    * */
    private int pageIndex;

    /**
     * 页面条数
     */
    private int pageSize;

    /**
     * 当前页数
     */
    private int pageNumber;

    public Long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageNumber = pageIndex+1;
        this.pageIndex = pageIndex;
    }
}