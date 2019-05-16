package com.petterp.latte.ec.main.personal.list.order;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.petterp.latte.ec.R;
import com.petterp.latte.ec.R2;
import com.petterp.latte.ec.main.personal.PersonalDelegate;
import com.petterp.latte_core.delegates.LatteDelegate;
import com.petterp.latte_core.net.RestClient;
import com.petterp.latte_core.net.callBack.ISuccess;
import com.petterp.latte_ui.retyclear.BaseDecoration;
import com.petterp.latte_ui.retyclear.MulitpleItemEntity;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;

/**
 * @author Petterp on 2019/5/12
 * Summary:个人中心——订单列表
 * 邮箱：1509492795@qq.com
 */
public class OrderListDelegate extends LatteDelegate {
    private String mType=null;

    @BindView(R2.id.rv_order_list)
    RecyclerView mRecyclearView=null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_list;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final  Bundle args=getArguments();
        assert args != null;
        mType=args.getString(PersonalDelegate.ORDER_TYPE);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }

    //懒加载
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .loader(getContext())
                .url("data/order_list.json")
                .params("type",mType)
                .success(response -> {
                    Log.e("",response);
                    final LinearLayoutManager manager=new LinearLayoutManager(getContext());
                    mRecyclearView.setLayoutManager(manager);
                    final List<MulitpleItemEntity> data=new OrderListDataConverter().setJsonData(response).convert();
                    final OrderListAdapter adapter=new OrderListAdapter(data);
                    mRecyclearView.setAdapter(adapter);
                    //设置RecyclearView下划线
                    mRecyclearView.addItemDecoration(BaseDecoration.create(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.app_background), 5));

                })
                .build()
                .get();
    }
}
