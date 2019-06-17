package com.petterp.latte.ec.main.personal.settings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.petterp.latte.ec.R;
import com.petterp.latte.ec.R2;
import com.petterp.latte.ec.main.personal.PersonalClickListener;
import com.petterp.latte.ec.main.personal.address.AddresDelegate;
import com.petterp.latte.ec.main.personal.list.ListAdapter;
import com.petterp.latte.ec.main.personal.list.ListBean;
import com.petterp.latte.ec.main.personal.list.ListItemType;
import com.petterp.latte_core.delegates.LatteDelegate;
import com.petterp.latte_core.util.callback.CallbackManager;
import com.petterp.latte_core.util.callback.CallbackType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Petterp on 2019/6/17
 * Summary:
 * 邮箱：1509492795@qq.com
 */
public class SettingsDelegate extends LatteDelegate {

    @BindView(R2.id.rv_settings)
    RecyclerView mRecyclearView=null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_settings;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        final ListBean push = new ListBean.Builder()
                //写入标记
                .setItemType(ListItemType.ITEM_SWITCH)
                .setText("消息推送")
                .setDelegate(new AddresDelegate())
                .setOnCheckedChangeListener((buttonView, isChecked) -> {
                    if (isChecked){
                        CallbackManager.getInstance().getCallback(CallbackType.TAG_OPEN_PUSH).executeCallback(null);
                        Toast.makeText(getContext(), "打开推送", Toast.LENGTH_SHORT).show();
                    }else{
                        CallbackManager.getInstance().getCallback(CallbackType.TAG_STOP_PUSH).executeCallback(null);
                        Toast.makeText(getContext(), "关闭推送", Toast.LENGTH_SHORT).show();
                    }
                })
                .setId(1)
                .build();
        final ListBean about = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_BORNAL)
                .setId(2)
                .setDelegate(new AboutDelegate())
                .setText("关于")
                .build();

        //数据
        final List<ListBean> data = new ArrayList<>();
        data.add(push);
        data.add(about);

        //设置RecyclearView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclearView.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRecyclearView.setAdapter(adapter);
        //添加点击事件
        mRecyclearView.addOnItemTouchListener(new SettingsClickListener(this));
    }
}
