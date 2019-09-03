package com.petterp.latte.ec.main.personal.payment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.petterp.latte.ec.R;
import com.petterp.latte.ec.R2;
import com.petterp.latte.ec.main.personal.PersonalDelegate;
import com.petterp.latte.ec.main.personal.order.OrderListAdapter;
import com.petterp.latte.ec.main.personal.order.OrderListDataConverter;
import com.petterp.latte_core.delegates.LatteDelegate;
import com.petterp.latte_core.net.RestClient;
import com.petterp.latte_core.net.callBack.ISuccess;
import com.petterp.latte_ui.retyclear.BaseDecoration;
import com.petterp.latte_ui.retyclear.MulitpleItemEntity;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;

/**
 * @author Petterp on 2019/05/17
 * Summary:待付款订单页面
 * 邮箱：1509492795@qq.com
 */
public class PaymentListDelegete extends LatteDelegate {

    private String mType=null;
    @BindView(R2.id.rv_payment_list)
    RecyclerView mRecyclearview=null;
    @Override
    public Object setLayout() {
        return R.layout.delegate_payment_list;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args=getArguments();
        assert args != null;
        mType=args.getString(PersonalDelegate.PAYMENT_TYPE);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
            .loader(getContext())
                .url("data/order_list.json")
                .params("type",mType)
                .success(response -> {
                    final LinearLayoutManager manager=new LinearLayoutManager(getContext());
                    mRecyclearview.setLayoutManager(manager);
                    final List<MulitpleItemEntity> data=new PaymentListDataConverter().setJsonData(response).convert();
                    final PaymentListAdapter adapter=new PaymentListAdapter(data);
                    mRecyclearview.setAdapter(adapter);
                    //设置RecyclearView下划线
                    mRecyclearview.addItemDecoration(BaseDecoration.create(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.app_background), 5));
                })
                .build()
                .get();

    }
}
