package com.petterp.latte_core.delegates;


import android.Manifest;
import android.support.annotation.NonNull;

import com.petterp.latte_core.delegates.web.camera.LatteCamera;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

/**
 * Auther: Petterp on 2019/4/14
 * Summary://中间层，用去权限的判定
 */
@RuntimePermissions
public abstract class PermissionCheckerDelegate extends BaseDelegate{

    //不是直接调用方法
    @NeedsPermission(Manifest.permission.CAMERA)
    void startCamera(){
        LatteCamera.start(this);
    }


    /**
     * 这个是真正调用的方法
     */
    public void startCameraWithCheck(){

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionCheckerDelegatePermissionsDispatcher.onRequestPermissionsResult(this,requestCode,grantResults);
    }
}
