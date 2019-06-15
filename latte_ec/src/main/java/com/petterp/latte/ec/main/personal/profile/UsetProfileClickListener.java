package com.petterp.latte.ec.main.personal.profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.petterp.latte.ec.R;
import com.petterp.latte.ec.main.personal.list.ListBean;
import com.petterp.latte_core.delegates.LatteDelegate;
import com.petterp.latte_ui.date.DateDialogUtil;

/**
 * @author Petterp on 2019/6/15
 * Summary:个人中心点击设置
 * 邮箱：1509492795@qq.com
 */
public class UsetProfileClickListener extends SimpleClickListener {

    private String[] mGenders=new String[]{"男","女","保密"};
    private final LatteDelegate DELEGATE;

    public UsetProfileClickListener(LatteDelegate  delegate) {
        this.DELEGATE=delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final ListBean bean= (ListBean) baseQuickAdapter.getData().get(position);
        final  int id=bean.getmId();
        switch (id){
            case 1:
                //开始照相机或选择图片
                DELEGATE.startCameraWithCheck();
                break;
            case 2:
                final LatteDelegate nameDelagate=bean.getmDelegate();
                DELEGATE.getSupportDelegate().start(nameDelagate);
                break;
            case 3:
                getGenderDialog((dialog, which) -> {
                    final TextView textView=view.findViewById(R.id.tv_arrow_value);
                    textView.setText(mGenders[which]);
                    dialog.cancel();
                });
                break;
            case 4:
                final DateDialogUtil dateDialogUtil=new DateDialogUtil();
                dateDialogUtil.setDateListener(date -> {
                    final TextView textView=view.findViewById(R.id.tv_arrow_value);
                    textView.setText(date);
                });
                dateDialogUtil.showDialog(DELEGATE.getContext());
                break;
            default: break;
        }
    }

    private void getGenderDialog(DialogInterface.OnClickListener listener){
        final AlertDialog.Builder builder=new AlertDialog.Builder(DELEGATE.getContext());
        builder.setSingleChoiceItems(mGenders,0,listener);
        builder.show();
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
