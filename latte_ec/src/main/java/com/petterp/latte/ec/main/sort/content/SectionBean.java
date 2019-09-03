package com.petterp.latte.ec.main.sort.content;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * @author Petterp on 2019/4/27
 * Summary:
 * 邮箱：1509492795@qq.com
 */
public class SectionBean extends SectionEntity<SectionContentItemEntity> {

    private boolean mIsMore=false;
    private int mId=-1;

    public SectionBean(SectionContentItemEntity sectionContentItemEntity) {
        super(sectionContentItemEntity);
    }

    //isHaeader是不是标题
    public SectionBean(boolean isHeader, String header) {
        super(isHeader, header);
    }


    public boolean isMore() {
        return mIsMore;
    }

    public void setMore(boolean mIsMore) {
        this.mIsMore = mIsMore;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }
}
