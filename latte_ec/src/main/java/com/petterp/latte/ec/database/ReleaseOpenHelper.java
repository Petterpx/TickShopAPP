package com.petterp.latte.ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * @author Petterp on 2019/4/21
 * Summary:
 * 邮箱：1509492795@qq.com
 */
public class ReleaseOpenHelper extends DaoMaster.OpenHelper {

    public ReleaseOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onCreate(Database db) {
        super.onCreate(db);
    }


}
