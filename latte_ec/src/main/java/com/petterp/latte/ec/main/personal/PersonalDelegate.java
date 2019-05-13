package com.petterp.latte.ec.main.personal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.petterp.latte.ec.R;
import com.petterp.latte.ec.R2;
import com.petterp.latte.ec.main.personal.list.ListAdapter;
import com.petterp.latte.ec.main.personal.list.ListBean;
import com.petterp.latte.ec.main.personal.list.ListItemType;
import com.petterp.latte.ec.main.personal.list.order.OrderListDelegate;
import com.petterp.latte_core.delegates.bottom.BottomItemDelegate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Petterp on 2019/4/30
 * Summary:个人中心配置
 * 邮箱：1509492795@qq.com
 */
public class PersonalDelegate extends BottomItemDelegate {

    @BindView(R2.id.rv_personal_setting)
    RecyclerView mRvSettings = null;
    private Bundle mArgs = null;

    private void startOrderListByType(){
        final OrderListDelegate delegate=new OrderListDelegate();
        delegate.setArguments(mArgs);
        getParentDelegate().getSupportDelegate().start(delegate);
        //a
    }
    @OnClick(R2.id.tv_all_order)
    void onClickAllOrder(){
        mArgs.putString(ORDER_TYPE,"all");
        startOrderListByType();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArgs=new Bundle();
    }

    public static final String ORDER_TYPE = "ORDER_TYPE";

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        final ListBean address = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_BORNAL)
                .setText("收货地址")
                .build();
        //系统设置
        final ListBean system = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_BORNAL)
                .setId(2)
                .setText("系统设置")
                .build();

        //数据
        final List<ListBean> data = new ArrayList<>();
        data.add(address);
        data.add(system);

        //设置RecyclearView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRvSettings.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRvSettings.setAdapter(adapter);
    }
}
