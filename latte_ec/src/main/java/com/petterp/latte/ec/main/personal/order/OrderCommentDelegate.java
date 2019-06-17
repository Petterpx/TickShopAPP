package com.petterp.latte.ec.main.personal.order;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import com.petterp.latte.ec.R;
import com.petterp.latte.ec.R2;
import com.petterp.latte_core.delegates.LatteDelegate;
import com.petterp.latte_core.util.callback.CallbackManager;
import com.petterp.latte_core.util.callback.CallbackType;
import com.petterp.latte_core.util.callback.IGlobalCallback;
import com.petterp.latte_ui.widget.AutoPhotoLayout;
import com.petterp.latte_ui.widget.StarLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Petterp on 2019/6/17
 * Summary:
 * 邮箱：1509492795@qq.com
 */
public class OrderCommentDelegate extends LatteDelegate {

    @BindView(R2.id.custom_star_layout)
    StarLayout mStarLayout=null;

    @BindView(R2.id.custom_auto_photo_layout)
    AutoPhotoLayout mAutoPhotoLayour=null;

    @OnClick(R2.id.top_tv_comment_commit)
    void onClickSubmit(){
        Toast.makeText(getContext(), "评分："+mStarLayout.getStarCount(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_order_comment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mAutoPhotoLayour.setDelegate(this);
        CallbackManager.getInstance().addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
            @Override
            public void executeCallback(@Nullable Uri args) {
                mAutoPhotoLayour.onCropTarget(args);
            }

        });
    }

}
