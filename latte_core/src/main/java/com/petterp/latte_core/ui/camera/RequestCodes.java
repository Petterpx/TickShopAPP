package com.petterp.latte_core.ui.camera;


import com.yalantis.ucrop.UCrop;

/**
 * @author Petterp on 2019/6/15
 * Summary:请求码存储
 * 邮箱：1509492795@qq.com
 */
public class RequestCodes {
    public static final int TAKE_PHOTO = 4;
    public static final int PICK_PHOTO = 5;
    public static final int CROP_PHOTO = UCrop.REQUEST_CROP;
    public static final int CROP_ERROR = UCrop.RESULT_ERROR;
    public static final int SCAN = 7;
}
