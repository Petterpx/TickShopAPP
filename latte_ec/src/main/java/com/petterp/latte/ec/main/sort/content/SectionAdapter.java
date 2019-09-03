package com.petterp.latte.ec.main.sort.content;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.petterp.latte.ec.R;

import java.util.List;

/**
 * @author Petterp on 2019/4/27
 * Summary:
 * 邮箱：1509492795@qq.com
 */
public class SectionAdapter extends BaseSectionQuickAdapter<SectionBean,BaseViewHolder> {

    private static final RequestOptions OPTIONS=new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate();

    public SectionAdapter(int layoutResId, int sectionHeadResId, List<SectionBean> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, SectionBean item) {
        helper.setText(R.id.header,item.header);
        //如果isMore 是true显示，否则不显示
        helper.setVisible(R.id.more,item.isMore());
        helper.addOnClickListener(R.id.more);
    }

    //商品转换
    @Override
    protected void convert(BaseViewHolder helper, SectionBean item) {
        //item.t返回SectionBean类型
        final String thumb=item.t.getMgoodsThumb();
        final String name=item.t.getMgoodsName();
        final int goodsId=item.getmId();
        final SectionContentItemEntity entry=item.t;
        helper.setText(R.id.tv,name);
        final AppCompatImageView goodImage=helper.getView(R.id.iv);
        Glide.with(mContext)
                .load(thumb)
                .into(goodImage);

    }
}
