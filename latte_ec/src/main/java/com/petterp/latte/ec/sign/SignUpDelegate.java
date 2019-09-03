package com.petterp.latte.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.petterp.latte.ec.R;
import com.petterp.latte.ec.R2;
import com.petterp.latte_core.app.Latte;
import com.petterp.latte_core.delegates.LatteDelegate;
import com.petterp.latte_core.net.RestClient;
import com.petterp.latte_core.net.callBack.ISuccess;
import com.petterp.latte_core.util.log.LatteLogger;

import java.util.Objects;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Petterp on 2019/4/21
 * Summary:注册逻辑
 * 邮箱：1509492795@qq.com
 */
public class SignUpDelegate extends LatteDelegate {

    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mName = null;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mPhone = null;
    @BindView(R2.id.edit_sign_up_pswd)
    TextInputEditText mPswd = null;
    @BindView(R2.id.edit_sign_up_re_pswd)
    TextInputEditText mRePswd = null;

    private ISignListener mISignListener=null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.btn_sign_up)
    void onclickSignup() {
        if (chechForm()) {
//            TestGet();
            JSONObject json=new JSONObject();
            json.put("name",Objects.requireNonNull(mName.getText()).toString());
            json.put("email",Objects.requireNonNull(mEmail.getText()).toString());
            json.put("phone",Objects.requireNonNull(mPhone.getText()).toString());
            json.put("pswd",Objects.requireNonNull(mPswd.getText()).toString());
            //转为字符串
            String response=json.toJSONString();
            LatteLogger.json("USER_PROFILE",response);
            SignHandler.onSignUP(response,mISignListener);
        }else{
            Toast.makeText(getContext(), "验证失败", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 测试Demo
     */
    private void TestGet() {
        RestClient.builder()
                .url("data/test.json")
                .params("name",Objects.requireNonNull(mName.getText()).toString())
                .params("email",Objects.requireNonNull(mEmail.getText()).toString())
                .params("phone",Objects.requireNonNull(mPhone.getText()).toString())
                .params("password",Objects.requireNonNull(mPswd.getText()).toString())
                .success(response -> {
                    LatteLogger.json("USER_PROFILE",response);
                    SignHandler.onSignUP(response,mISignListener);
                    Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                })
                .build().get();
    }

    @OnClick(R2.id.tv_link_sign_in)
    void onClickLine(){
        getSupportDelegate().start(new SignInDelegate());
    }

    private boolean chechForm() {
        final String name = mName.getText().toString();
        final String email = mEmail.getText().toString();
        final String phone = mPhone.getText().toString();
        final String pswd = mPswd.getText().toString();
        final String repswd = mRePswd.getText().toString();

        boolean isPass = true;
        if (name.isEmpty()) {
            mName.setError("请输入姓名");
            isPass = false;
        } else {
            mName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;

        } else {
            mEmail.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 11) {
            mPhone.setError("手机号码错误");
            isPass = false;


        } else {
            mPhone.setError(null);
        }

        if (pswd.isEmpty() || pswd.length() < 6) {
            mPswd.setError("请填写至少6位密码");
            isPass = false;

        } else {
            mPswd.setError(null);
        }

        if (repswd.isEmpty() || repswd.length() < 6 || !(repswd.equals(pswd))) {
            mRePswd.setError("密码认证错误");
            isPass = false;

        } else {
            mRePswd.setError(null);
        }
        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View view) {

    }
}
