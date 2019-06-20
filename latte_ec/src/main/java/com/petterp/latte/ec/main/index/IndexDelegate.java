package com.petterp.latte.ec.main.index;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.joanzapata.iconify.widget.IconTextView;
import com.petterp.latte.ec.R;
import com.petterp.latte.ec.R2;
import com.petterp.latte.ec.main.EcBottomDelgate;
import com.petterp.latte.ec.main.index.search.SearchDelegate;
import com.petterp.latte.ec.main.personal.PersonalClickListener;
import com.petterp.latte_core.util.Bootomzhaungtai.EachTooblar;
import com.petterp.latte_core.delegates.bottom.BottomItemDelegate;
import com.petterp.latte_core.util.callback.CallbackManager;
import com.petterp.latte_core.util.callback.CallbackType;
import com.petterp.latte_core.util.callback.IGlobalCallback;
import com.petterp.latte_ui.refresh.RefreshHandler;
import com.petterp.latte_ui.retyclear.BaseDecoration;

import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Petterp on 2019/4/22
 * Summary:首頁
 * 邮箱：1509492795@qq.com
 */
public class IndexDelegate extends BottomItemDelegate implements View.OnFocusChangeListener {
    private int dys;
    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout = null;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar = null;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan = null;

    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView = null;
    @BindView(R2.id.zhuangtai)
    TextView textView = null;
    @OnClick(R2.id.icon_index_scan)
    void onClickScanQrCode(){
        startScanWithCheck(this.getParentDelegate());
    }

    private RefreshHandler mRefreHandler = null;

    /**
     * 初始化工作
     */
    @SuppressLint("ResourceAsColor")
    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light

        );
        //设置为true，在下拉的过程中，圆圈会由小变大，第二个参数是起始高度，第三个参数是终止高度
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }


    //初始化RecyclearView布局
    private void initRecyclearView() {
        //网格布局
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(manager);
        //得到父级容器
//        final EcBottomDelgate ecBottomDelgate = getParentDelegate();
        mRecyclerView.addOnItemTouchListener(new IndexItemClickListener(this));
    }


//    @SuppressLint("NewApi")
//    @Override
//    public void onSupportVisible() {
//        super.onSupportVisible();
//        if (isImmersionBarEnabled()) {
//            initImmersionBar();
//        }
//    }
//
//    public void initImmersionBar() {
//        ImmersionBar.with(this)
//                .keyboardEnable(true)
//
//                .fitsSystemWindows(true)
//                .init();
//    }


    private boolean isImmersionBarEnabled() {
        return true;
    }


    /**
     * 用来处理懒加载数据，加载稍慢
     *
     * @param savedInstanceState
     */
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        mRefreHandler.fristPage("data/index.json");
        //设置RecyclearView 分割线
        mRecyclerView.addItemDecoration(BaseDecoration.create(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.app_background), 5));
        mRecyclerView.addOnScrollListener(
                //单例模式获取EachTooblar对象，并传入mToolbar,这个类实现了RecyclearView的监听
                EachTooblar.
                        builder().
                        setToolbar(mToolbar).
                        setAndroidNativeLightStatusBar(true)
                        .setTooblarPadding(0, 0)
        );
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View view) {
        //传入下拉刷新事件,并生成了Adapter。。。
        mRefreHandler = RefreshHandler.create(mRefreshLayout, mRecyclerView, new IndexDataConverter());
        initRecyclearView();
        //回调二维码扫描结果
        CallbackManager.getInstance().addCallback(CallbackType.ON_SCAN, new IGlobalCallback<String>() {
            @Override
            public void executeCallback(@Nullable String args) {
                Toast.makeText(getContext(), args, Toast.LENGTH_SHORT).show();
            }
        });
        //适配Toolbar变色
        mSearchView.setOnFocusChangeListener(this);
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus){
            getParentDelegate().getSupportDelegate().start(new SearchDelegate());
        }
    }

    /*@Override
    public void onSupportInvisible() {
        super.onSupportInvisible();

    }*/
}
