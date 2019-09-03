package com.petterp.latte.ec.main.index.search;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.StringUtils;
import com.choices.divider.Divider;
import com.choices.divider.DividerItemDecoration;
import com.petterp.latte.ec.R;
import com.petterp.latte.ec.R2;
import com.petterp.latte.ec.main.sort.content.SectionDataConverter;
import com.petterp.latte_core.delegates.LatteDelegate;
import com.petterp.latte_core.net.RestClient;
import com.petterp.latte_core.net.callBack.ISuccess;
import com.petterp.latte_core.util.storage.LatterPreference;
import com.petterp.latte_ui.retyclear.MulitpleItemEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Petterp on 2019/6/18
 * Summary:
 * 邮箱：1509492795@qq.com
 */
public class SearchDelegate extends LatteDelegate {

    @BindView(R2.id.rv_search)
    RecyclerView mRecyclearView=null;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchEdit=null;

    @OnClick(R2.id.tv_top_search)
    void onCliclSearch(){
        //搜索时网络请求
//        RestClient.builder()
//                .url("")
//                .success(new ISuccess() {
//                    @Override
//                    public void onSuccess(String response) {
//
//                    }
//                })
//                .build().get();
        final String searchItemText=Objects.requireNonNull(mSearchEdit.getText()).toString();
        saveItem(searchItemText);
        mSearchEdit.setText("");
    }

    @OnClick(R2.id.icon_top_search_back)
    void onClickBack(){
        getSupportDelegate().pop();
    }

    private void saveItem(String item){
        if (!StringUtils.isEmpty(item) && !StringUtils.isSpace(item)) {
            List<String> history;
            final String historyStr =
                    LatterPreference.getCustomAppProfile(SerachDataConverter.TAG_SEARCH_HISTORY);
            if (StringUtils.isEmpty(historyStr)) {
                history = new ArrayList<>();
            } else {
                history = JSON.parseObject(historyStr, ArrayList.class);
            }
            history.add(item);
            final String json = JSON.toJSONString(history);

            LatterPreference.addCustomAppProfile(SerachDataConverter.TAG_SEARCH_HISTORY, json);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_serach;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        final LinearLayoutManager manager=new LinearLayoutManager(getContext());
        mRecyclearView.setLayoutManager(manager);

        final List<MulitpleItemEntity> data=new SerachDataConverter().convert();
        final SearchAdapter adapter=new SearchAdapter(data);
        mRecyclearView.setAdapter(adapter);
        final DividerItemDecoration itemDecoration=new DividerItemDecoration();
        itemDecoration.setDividerLookup(new DividerItemDecoration.DividerLookup() {
            @Override
            public Divider getVerticalDivider(int position) {
                return null;
            }

            @Override
            public Divider getHorizontalDivider(int position) {
                return new  Divider.Builder()
                        .size(2)
                        .margin(20,20)
                        .color(Color.GRAY)
                        .build();
            }
        });
        mRecyclearView.addItemDecoration(itemDecoration);
    }
}
