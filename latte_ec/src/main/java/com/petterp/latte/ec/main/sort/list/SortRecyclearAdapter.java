package com.petterp.latte.ec.main.sort.list;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.petterp.latte.ec.R;
import com.petterp.latte.ec.main.sort.SortDelegate;
import com.petterp.latte.ec.main.sort.content.ContentDelegate;
import com.petterp.latte_core.delegates.LatteDelegate;
import com.petterp.latte_ui.retyclear.ItemType;
import com.petterp.latte_ui.retyclear.MulitipleViewHolder;
import com.petterp.latte_ui.retyclear.MulitpleItemEntity;
import com.petterp.latte_ui.retyclear.MultipleFidls;
import com.petterp.latte_ui.retyclear.MultipleRecyclearAdapter;

import java.util.List;

import me.yokeyword.fragmentation.SupportHelper;
import retrofit2.http.DELETE;

/**
 * @author Petterp on 2019/4/27
 * Summary:渲染左边List列表··
 * 邮箱：1509492795@qq.com
 */
public class SortRecyclearAdapter extends MultipleRecyclearAdapter {

    private final SortDelegate DELEGATE;
    private int mPrePostion=0;

    protected SortRecyclearAdapter(List<MulitpleItemEntity> data, SortDelegate delegate) {
        super(data);
        this.DELEGATE = delegate;
        //添加垂直菜单布局
        addItemType(ItemType.VERTICAL_MENU_LIST, R.layout.item_vertical_menu_list);
    }

    @Override
    public void convert(MulitipleViewHolder holder, MulitpleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case ItemType.VERTICAL_MENU_LIST:
               final  String text=entity.getField(MultipleFidls.TEXT);
               final  boolean isClicked=entity.getField(MultipleFidls.TAG);
               final AppCompatTextView name=holder.getView(R.id.tv_vertical_item_name);
               final View line=holder.getView(R.id.view_line);
               final View itemView=holder.itemView;
               itemView.setOnClickListener(v -> {
                    final int currentPosition=holder.getAdapterPosition();
                    if (mPrePostion!=currentPosition){
                        //还原上一个
                        getData().get(mPrePostion).setFild(MultipleFidls.TAG,false);
                        notifyItemChanged(mPrePostion);

                        //更新选中的item
                        entity.setFild(MultipleFidls.TAG,true);
                        notifyItemChanged(currentPosition);
                        mPrePostion=currentPosition;
                        final int contentId = getData().get(currentPosition).getField(MultipleFidls.ID);
                        showContent(contentId);
                    }
               });
               if (!isClicked){
                   line.setVisibility(View.INVISIBLE);
                   name.setTextColor(ContextCompat.getColor(mContext,R.color.we_chat_black));
                   itemView.setBackgroundColor(ContextCompat.getColor(mContext,R.color.item_blackground));
               }else{
                   line.setVisibility(View.VISIBLE);
                   name.setTextColor(ContextCompat.getColor(mContext,R.color.app_main));
                   line.setBackgroundColor(ContextCompat.getColor(mContext,R.color.app_main));
                   itemView.setBackgroundColor(Color.WHITE);
               }
               holder.setText(R.id.tv_vertical_item_name,text);
                break;
            default:
                break;
        }
    }
    private void showContent(int contenId){
        final ContentDelegate delegate=ContentDelegate.newInstance(contenId);
        switchContent(delegate);
    }
    private void switchContent(ContentDelegate delegate){
        final LatteDelegate contentDelegate =
                SupportHelper.findFragment(DELEGATE.getChildFragmentManager(), ContentDelegate.class);
        if (contentDelegate != null) {
            contentDelegate.getSupportDelegate().replaceFragment(delegate, false);
        }
    }
}
