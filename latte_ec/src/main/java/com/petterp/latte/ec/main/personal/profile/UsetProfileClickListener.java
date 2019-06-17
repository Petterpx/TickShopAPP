package com.petterp.latte.ec.main.personal.profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.petterp.latte.ec.R;
import com.petterp.latte.ec.main.personal.list.IListOnclick;
import com.petterp.latte.ec.main.personal.list.ListAdapter;
import com.petterp.latte.ec.main.personal.list.ListBean;
import com.petterp.latte.ec.main.personal.list.ListIconDialog;
import com.petterp.latte_core.delegates.LatteDelegate;
import com.petterp.latte_core.net.RestClient;
import com.petterp.latte_core.net.callBack.ISuccess;
import com.petterp.latte_core.util.callback.CallbackManager;
import com.petterp.latte_core.util.callback.CallbackType;
import com.petterp.latte_core.util.callback.IGlobalCallback;
import com.petterp.latte_core.util.log.LatteLogger;
import com.petterp.latte_ui.date.DateDialogUtil;

/**
 * @author Petterp on 2019/6/15
 * Summary:个人中心点击设置
 * 邮箱：1509492795@qq.com
 */
public class UsetProfileClickListener extends SimpleClickListener {

    private String[] mGenders=new String[]{"男","女","保密"};
    private final LatteDelegate DELEGATE;
    private final ListAdapter ADAPTER;
    public UsetProfileClickListener(LatteDelegate  delegate, ListAdapter adapter) {
        this.DELEGATE=delegate;
        this.ADAPTER=adapter;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final ListBean bean= (ListBean) baseQuickAdapter.getData().get(position);
        final  int id=bean.getmId();
        switch (id){
            case 1:
                ADAPTER.listOnclick=new IListOnclick() {
                    @Override
                    public void Rvonclik() {
                        //开始照相机或选择图片
                        CallbackManager.getInstance()
                                .addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
                                    @Override
                                    public void executeCallback(Uri args) {
                                        LatteLogger.e("ON_CROP", String.valueOf(args));
                                        final ImageView avator=view.findViewById(R.id.img_arrow_avatar);
                                        Glide.with(DELEGATE)
                                                .load(args)
                                                .into(avator);
                                        RestClient.builder()
                                                .url(UploadConfig.UPLOAD_IMG)
                                                .loader(DELEGATE.getContext())
                                                .file(args.getPath())
                                                .success(new ISuccess() {
                                                    @Override
                                                    public void onSuccess(String response) {
                                                        LatteLogger.e("ON_CROP_UPLOAD",response);
                                                        //通知服务器更新信息
                                                        String path=JSON.parseObject(response).getJSONObject("result").getString("path");
                                                        RestClient.builder()
                                                                .url("")        //填你的PHP文件
                                                                .params("avator",path)
                                                                .loader(DELEGATE.getContext())
                                                                .success(new ISuccess() {
                                                                    @Override
                                                                    public void onSuccess(String response) {
                                                                        //获取更新后的用户信息，然后更新本地数据库
                                                                        //没有本地数据的APP,每次打开APP都请求API，获取信息
                                                                    }
                                                                })
                                                                .build()
                                                                .post();
                                                    }
                                                })
                                                .build()
                                                .upload();
                                    }
                                });
                        DELEGATE.startCameraWithCheck();
                    }

                    @Override
                    public void Imonclick() {
                        new ListIconDialog(view.getContext()).show();
                    }
                };
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
