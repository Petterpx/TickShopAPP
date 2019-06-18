package com.petterp.latte_ui.refresh;

import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.petterp.latte_core.app.Latte;
import com.petterp.latte_core.net.RestClient;
import com.petterp.latte_core.net.callBack.ISuccess;
import com.petterp.latte_ui.retyclear.DataConverter;
import com.petterp.latte_ui.retyclear.MultipleRecyclearAdapter;

/**
 * @author Petterp on 2019/4/22
 * Summary:用来监听下拉操作
 * 邮箱：1509492795@qq.com
 */
public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {


    //下拉刷新
    private final SwipeRefreshLayout swipeRefreshLayout;
    private final PaginBean BEAN;
    private final RecyclerView RECYCLEARVIEW;
    private MultipleRecyclearAdapter mAdapter = null;
    private final DataConverter CONVERTER;


    public RefreshHandler(SwipeRefreshLayout swipeRefreshLayout,
                          RecyclerView recyclerView,
                          DataConverter converter,
                          PaginBean bean) {
        this.swipeRefreshLayout = swipeRefreshLayout;
        this.BEAN = bean;
        this.RECYCLEARVIEW = recyclerView;
        this.CONVERTER = converter;
        //设置监听事件
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    public static RefreshHandler create(SwipeRefreshLayout swipeRefreshLayout,
                                        RecyclerView recyclerView,
                                        DataConverter converter
    ) {
        return new RefreshHandler(swipeRefreshLayout, recyclerView, converter, new PaginBean());
    }


    private void paging(String url) {
        //每页显示的数据
        final int pageSize = BEAN.getPageSize();
        //当前页面显示数据
        final int currentCount = BEAN.getCurrentCount();
        //总数据条数
        final int total = BEAN.getTotal();
        //当前页码数
        final int index = BEAN.getPageIndex();

        if (mAdapter.getData().size() < pageSize || currentCount >= total) {
            mAdapter.loadMoreEnd(true);
        } else {
            Latte.getHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    RestClient.builder()
                            .url(url + index)
                            .success(new ISuccess() {
                                @Override
                                public void onSuccess(String response) {
                                    mAdapter.addData(CONVERTER.setJsonData(response).convert());
                                    //累加数量
                                    BEAN.setCurrentCount(mAdapter.getData().size());
                                    //加载结束
                                    mAdapter.loadMoreComplete();
                                    BEAN.addIndex();
                                }
                            })
                            .build().get();
                }
            }, 500);
        }
    }

    /**
     * 下拉刷新操作
     */
    @Override
    public void onRefresh() {
        refresh();
    }

    private void refresh() {
        //开始加载
        swipeRefreshLayout.setRefreshing(true);
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //消失
                fristPage("data/index.json");
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }

    /**
     * 加载第一页
     *
     * @param url
     */
    public void fristPage(String url) {
        BEAN.setDelayed(1000);
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject object = JSON.parseObject(response);
                        BEAN.setTotal(object.getInteger("total"))
                                .setPageSize(object.getInteger("page_size"));
                        //设置Adapter
                        mAdapter = MultipleRecyclearAdapter.create(CONVERTER.setJsonData(response));
                        //賦予RecyclearView下拉刷新
                        mAdapter.setOnLoadMoreListener(RefreshHandler.this, RECYCLEARVIEW);
                        RECYCLEARVIEW.setAdapter(mAdapter);
                        BEAN.addIndex();
                    }
                })
                .build()
                .get();
    }


    @Override
    public void onLoadMoreRequested() {
        paging("refresh.php?index=");
    }
}
