package com.petterp.latte.ec.main.cart_gouwuche;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewStubCompat;
import android.view.View;
import android.widget.Toast;

import com.joanzapata.iconify.widget.IconTextView;
import com.petterp.latte.ec.R;
import com.petterp.latte.ec.R2;
import com.petterp.latte.ec.pay.demo.IcartAlPay;
import com.petterp.latte.ec.pay.demo.TestPay;
import com.petterp.latte_core.delegates.bottom.BottomItemDelegate;
import com.petterp.latte_core.net.RestClient;
import com.petterp.latte_core.net.callBack.ISuccess;
import com.petterp.latte_ui.retyclear.MulitpleItemEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.WeakHashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Petterp on 2019/4/28
 * Summary:
 * 邮箱：1509492795@qq.com
 */
public class ShopCartDelegate extends BottomItemDelegate implements ISuccess, IcartItemListener, IcartAlPay {
    private ShopCartAdapter madapter = null;
    //当前要删除item的数量
    private int mCurrentCount = 0;
    private int mTotalCount = 0;
    @BindView(R2.id.rv_shop_cart)
    RecyclerView mrecyclerView = null;
    @BindView(R2.id.icon_shop_cart_select_all)
    IconTextView mIconSelectAll = null;

    @BindView(R2.id.rv_shop_cart_stub)
    ViewStubCompat mStubCompat = null;

    @BindView(R2.id.tv_shop_cart_total_price)
    AppCompatTextView mTotalPeice = null;

    private View stubView;

    //回调信息接口

    //点击全选事件
    @OnClick(R2.id.icon_shop_cart_select_all)
    void onClickSelectAll() {
        //每次点击全选设置价格为初始状态
        final int tag = (int) mIconSelectAll.getTag();
        //如果未选择
        boolean mode;
        if (tag == 0) {
            mIconSelectAll.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.app_main));
            mIconSelectAll.setTag(1);
            setState(true);
            //更新购物车价钱
        } else {
            mIconSelectAll.setTextColor(Color.GRAY);
            mIconSelectAll.setTag(0);
            setState(false);
        }
        //更新RecyclearView 指定位置的ui
        madapter.notifyItemRangeChanged(0, madapter.getItemCount());
        setMoney();
    }

    @OnClick(R2.id.tv_top_cart_remove_selected)
    void onClickRemoveSelectedItem() {
        final int tag = (int) mIconSelectAll.getTag();
        if (tag == 1) {
            mIconSelectAll.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.app_main));
        } else {
            mIconSelectAll.setTextColor(Color.GRAY);
        }
        final List<MulitpleItemEntity> data = madapter.getData();
        //找到要删除的数据
        final List<MulitpleItemEntity> deleteEntities = new ArrayList<>();
        for (MulitpleItemEntity entity : data) {
            final boolean isSelected = entity.getField(ShopCartItemFields.IS_SELECTED);
            if (isSelected) {
                deleteEntities.add(entity);
            }
        }
        //遍历remove数据
        for (MulitpleItemEntity entity : deleteEntities) {
            final int entityPosition = entity.getField(ShopCartItemFields.POSITION);
            madapter.remove(entityPosition);
            madapter.notifyItemRangeChanged(entityPosition, madapter.getItemCount());
            //数据更新，改变对象所指的postion
            List<MulitpleItemEntity> list = madapter.getData();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getField(ShopCartItemFields.IS_SELECTED)) {
                    list.get(i).setFild(ShopCartItemFields.POSITION, i);
                }
            }
        }
        //检查购物车是否空了
        checkItemCount();
    }

    @SuppressLint("RestrictedApi")
    private void checkItemCount() {
        setMoney();
        final int count = madapter.getItemCount();
        if (count == 0) {
            if (stubView == null) {
                //stubView只可以inflate一次，否则报错
                stubView = mStubCompat.inflate();
                final AppCompatTextView tvToBuy = stubView.findViewById(R.id.tv_stub_to_buy);
                tvToBuy.setOnClickListener(v -> Toast.makeText(getContext(), "购物车已经空啦", Toast.LENGTH_SHORT).show());
            }
            mrecyclerView.setVisibility(View.GONE);
        } else {
            mrecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R2.id.tv_top_car_clear)
    void onCliclClear() {
        madapter.getData().clear();
        madapter.notifyDataSetChanged();
        checkItemCount();
    }

    @OnClick(R2.id.tv_shop_cart_pay)
    void onClickPay() {
//        FastPay.create(this).beginDialog();
        final double money = Double.parseDouble(mTotalPeice.getText().toString());
        if (money != 0) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("money", String.valueOf(money));
            hashMap.put("subject", "商品付款");
            hashMap.put("body", "手机信息");
            new TestPay(getProxyActivity(), this).setPayInfo(hashMap).beginDialog();
        } else {
            Toast.makeText(_mActivity, "未选择任何商品", Toast.LENGTH_SHORT).show();
        }
    }

    //创建订单，注意，和支付是没有关系
    private void createOrder() {
        final String orderUrl = "2016093000634409";
        final WeakHashMap<String, Object> oradParms = new WeakHashMap<>();
        oradParms.put("userid", orderUrl);
        //总价
        oradParms.put("amount", 0.01);
        //描述
        oradParms.put("comment", "测试支付");
        //约定
        oradParms.put("type", 1);
        oradParms.put("oradertype", 0);
        oradParms.put("isanonymous", true);
        oradParms.put("followeduser", 0);
        //这里通过网络提交订单
        /*RestClient.builder()
                .url(orderUrl)
                */
        //请求的内容
//        String respone = null;
//        final int orderId=JSON.parseObject(respone).getInteger("result");
        int orderId = 0;
    }

    //选中值的个数
//    private int mIconSelectedCount=0;
    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View view) {
        mIconSelectAll.setTag(0);
        RestClient.builder()
                .url("/data/shop_cart_data.json")
                .loader(getContext())
                .success(this)
                .build()
                .get();
    }


    @Override
    public void onSuccess(String response) {
        final ArrayList<MulitpleItemEntity> data = new ShopChartConverter()
                .setJsonData(response).
                        convert();
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mrecyclerView.setLayoutManager(manager);
        madapter = new ShopCartAdapter(data);
        madapter.setmIcartItemListener(this);
        mrecyclerView.setAdapter(madapter);
    }

    @Override
    public void onItemClick() {
        //更新购物车价格
        setMoney();
    }

    private void setMoney() {
        double mTotalPrice = 0.0;
        final List<MulitpleItemEntity> data = madapter.getData();
        for (MulitpleItemEntity entity : data) {
            final boolean mode = entity.getField(ShopCartItemFields.IS_SELECTED);
            if (mode) {
                final double price = entity.getField(ShopCartItemFields.PRICE);
                final int count = entity.getField(ShopCartItemFields.COUNT);
                final double total = price * count;
                mTotalPrice += total;
            }
        }
        mTotalPeice.setText(String.valueOf(mTotalPrice));
    }

    private void setState(boolean mode) {
        final List<MulitpleItemEntity> data = madapter.getData();
        for (MulitpleItemEntity entity : data) {
            entity.setFild(ShopCartItemFields.IS_SELECTED, mode);
        }
    }

    //执行成功逻辑
    @Override
    public void SUCCESS() {
        onClickRemoveSelectedItem();
    }
}
