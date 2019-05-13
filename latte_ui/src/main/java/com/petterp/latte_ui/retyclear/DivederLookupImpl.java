package com.petterp.latte_ui.retyclear;


import com.choices.divider.Divider;
import com.choices.divider.DividerItemDecoration;

/**
 * @author Petterp on 2019/4/24
 * Summary: RecyclearView分割线的实现
 * 邮箱：1509492795@qq.com
 */
public class DivederLookupImpl implements DividerItemDecoration.DividerLookup {
    private final int COLOR;
    private final int SIZE;

    public DivederLookupImpl(int color, int size) {
        this.COLOR = color;
        this.SIZE = size;
    }

    @Override
    public Divider getVerticalDivider(int position) {
        return new Divider.Builder()
                .size(SIZE)
                .color(COLOR)
                .build();
    }

    @Override
    public Divider getHorizontalDivider(int position) {
        return new Divider.Builder()
                .size(SIZE)
                .color(COLOR)
                .build();
    }
}
