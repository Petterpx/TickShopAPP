package com.petterp.latte.ec.main.personal.address;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.petterp.latte.ec.R;
import com.petterp.latte.ec.R2;
import com.petterp.latte_core.delegates.LatteDelegate;
import com.petterp.latte_core.net.RestClient;
import com.petterp.latte_core.net.callBack.ISuccess;
import com.petterp.latte_core.util.log.LatteLogger;
import com.petterp.latte_ui.retyclear.MulitpleItemEntity;

import java.util.List;

import butterknife.BindView;

/**
 * @author Petterp on 2019/6/16
 * Summary:
 * 邮箱：1509492795@qq.com
 */
public class AddresDelegate extends LatteDelegate implements ISuccess {

    @BindView(R2.id.rv_address)
    RecyclerView mRecyclearView=null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_address;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        RestClient.builder()
                .url("data/address.json")
                .loader(getContext())
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {
        LatteLogger.e("Address",response);
        final LinearLayoutManager manager=new LinearLayoutManager(getContext());
        mRecyclearView.setLayoutManager(manager);
        final List<MulitpleItemEntity> data=new AddressDataConvert().setJsonData(response).convert();
        final AddressItemAdapter adapter=new AddressItemAdapter(data);
        mRecyclearView.setAdapter(adapter);
    }
}
