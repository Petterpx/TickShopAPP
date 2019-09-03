package com.petterp.latte.ec.main.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.petterp.latte.ec.R;
import com.petterp.latte.ec.R2;
import com.petterp.latte.ec.main.sort.SortDelegate;
import com.petterp.latte.ec.main.sort.list.SortRecyclearAdapter;
import com.petterp.latte.ec.main.sort.list.VerticalListDateConverter;
import com.petterp.latte_core.delegates.LatteDelegate;
import com.petterp.latte_core.net.RestClient;
import com.petterp.latte_ui.retyclear.BaseDecoration;
import com.petterp.latte_ui.retyclear.MulitpleItemEntity;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;

/**
 * @author Petterp on 2019/4/27
 * Summary:
 * 邮箱：1509492795@qq.com
 */
public class ContentDelegate extends LatteDelegate {
    @BindView(R2.id.rv_list_content)
    RecyclerView mRecyclerView = null;
    private static final String ARG_CONTENT_ID = "CONTENT_ID";
    private int mContentId = -1;
    private List<SectionBean> mData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mContentId = args.getInt(ARG_CONTENT_ID);
        }
    }

    public static ContentDelegate newInstance(int contentId) {
        final Bundle args = new Bundle();
        args.putInt(ARG_CONTENT_ID, contentId);
        final ContentDelegate delegate = new ContentDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_list_content;
    }

    private void initData() {
        if (mContentId>2){
            mContentId=1;
        }
        RestClient.builder()
                .url("/data/sort_content_list_data"+mContentId+".json")
                .loader(getContext())
                .success(response -> {
                    mData=new SectionDataConverter().convert(response);
                    final SectionAdapter sectionAdapter=new SectionAdapter(R.layout.item_section_content,R.layout.item_section_header,mData);
                    mRecyclerView.setAdapter(sectionAdapter);
                })
                .build()
                .get();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View view) {
        //瀑布流  （左右能显示的数量，垂直显示）
        final StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
//        mRecyclerView.addItemDecoration(BaseDecoration.create(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.app_background),1));
        initData();
    }
}
