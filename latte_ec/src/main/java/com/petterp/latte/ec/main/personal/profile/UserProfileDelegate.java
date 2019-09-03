package com.petterp.latte.ec.main.personal.profile;

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
import com.petterp.latte.ec.main.personal.payment.PaymentListAdapter;
import com.petterp.latte.ec.main.personal.payment.PaymentListDataConverter;
import com.petterp.latte.ec.main.personal.settings.NameDelegate;
import com.petterp.latte_core.delegates.LatteDelegate;
import com.petterp.latte_ui.retyclear.MulitpleItemEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Petterp on 2019/05/17
 * Summary:个人信息配置
 * 邮箱：1509492795@qq.com
 */
public class UserProfileDelegate extends LatteDelegate {
    @BindView(R2.id.rv_profile_list)
    RecyclerView mRecyclearView=null;
    @Override
    public Object setLayout() {
        return R.layout.delegate_user_proifile;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {


        final ListBean image = new ListBean.Builder()
                //写入标记
                .setItemType(ListItemType.ITEM_AVATAR)
                .setId(1)
                .setImageUrl("http://101.132.64.249/data/name.png")
                .build();
        //系统设置
        final ListBean name = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_BORNAL)
                .setId(2)
                .setText("姓名")
                .setValue("Petterp")
                .setDelegate(new NameDelegate())
                .build();
        final ListBean gender = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_BORNAL)
                .setId(3)
                .setText("性别")
                .setValue("男")
                .build();
        final ListBean birth = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_BORNAL)
                .setId(4)
                .setText("生日")
                .setValue("1998")
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(image);
        data.add(name);
        data.add(gender);
        data.add(birth);

        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclearView.setLayoutManager(manager);
        final ListAdapter adapter=new ListAdapter(data);
        mRecyclearView.setAdapter(adapter);
        mRecyclearView.addOnItemTouchListener(new UsetProfileClickListener(this,adapter));

    }


}
