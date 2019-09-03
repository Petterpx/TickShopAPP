package com.petterp.latte.ec.main.sort.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.petterp.latte.ec.R;
import com.petterp.latte.ec.R2;
import com.petterp.latte.ec.main.sort.SortDelegate;
import com.petterp.latte_core.delegates.LatteDelegate;
import com.petterp.latte_core.net.RestClient;
import com.petterp.latte_ui.retyclear.MulitpleItemEntity;

import java.util.List;

import butterknife.BindView;

/**
 * @author Petterp on 2019/4/27
 * Summary:垂直列表
 * 邮箱：1509492795@qq.com
 */
public class VerticalListDelegate extends LatteDelegate {

    @BindView(R2.id.rv_vertical_menu_list)
    RecyclerView mrecyclerView=null;
    @Override
    public Object setLayout() {
        return R.layout.delegate_vertical_list;
    }

    private void initRecyclearView(){
        final LinearLayoutManager manager=new LinearLayoutManager(getContext());
        mrecyclerView.setLayoutManager(manager);
        //屏蔽动画效果
        mrecyclerView.setItemAnimator(null);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View view) {
        initRecyclearView();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("/data/sort_list_data.json")
                .loader(getContext())
                .success(response -> {
                    final List<MulitpleItemEntity> data=new VerticalListDateConverter().setJsonData(response).convert();
                    final SortDelegate delegate=getParentDelegate();
                    final SortRecyclearAdapter adapter=new SortRecyclearAdapter(data,delegate);
                    mrecyclerView.setAdapter(adapter);
                })
                .build()
                .get();

    }
}
