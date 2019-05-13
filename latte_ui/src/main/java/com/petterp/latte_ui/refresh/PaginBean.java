package com.petterp.latte_ui.refresh;

/**
 * @author Petterp on 2019/4/24
 * Summary:数据类
 * 邮箱：1509492795@qq.com
 */
public class PaginBean {
    //当前是第二页
    private int mPageIndex=0;
    //总数据条数
    private int mTotal=0;
    //一页显示几条数据
    private int mPageSize=0;
    //当前已经显示了几条数据
    private int mCurrentCount=0;
    //加载延迟
    private int mDelayed=0;

    public int getPageIndex() {
        return mPageIndex;
    }

    public PaginBean setPageIndex(int mPageIndex) {
        this.mPageIndex = mPageIndex;
        return this;
    }

    public int getTotal() {
        return mTotal;
    }

    public PaginBean setTotal(int mTotal) {
        this.mTotal = mTotal;
        return this;

    }

    public int getPageSize() {
        return mPageSize;
    }

    public PaginBean setPageSize(int mPageSize) {
        this.mPageSize = mPageSize;
        return this;

    }

    public int getCurrentCount() {
        return mCurrentCount;
    }

    public PaginBean setCurrentCount(int mCurrentCount) {
        this.mCurrentCount = mCurrentCount;
        return this;

    }

    public int getDelayed() {
        return mDelayed;
    }

    public PaginBean setDelayed(int mDelayed) {
        this.mDelayed = mDelayed;
        return this;
    }

    //告诉我们当前的index已经+1
    PaginBean addIndex(){
        mPageIndex++;
        return this;
    }
}
