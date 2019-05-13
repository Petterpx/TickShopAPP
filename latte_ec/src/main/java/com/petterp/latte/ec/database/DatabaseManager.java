package com.petterp.latte.ec.database;

import android.content.Context;
import android.provider.ContactsContract;

import org.greenrobot.greendao.database.Database;

/**
 * @author Petterp on 2019/4/21
 * Summary:
 * 邮箱：1509492795@qq.com
 */
public class DatabaseManager  {
    private DaoSession mDaoSession=null;
    private UserProfileDao mDao=null;

    public DatabaseManager init(Context context){
        initDao(context);
        return this;
    }

    //单例
    private static  final class Holder{
        private static  final DatabaseManager INSTANCE=new DatabaseManager();
    }

    public static DatabaseManager getInstance(){
        return Holder.INSTANCE;
    }

    private void initDao(Context context){
        final ReleaseOpenHelper helper=new ReleaseOpenHelper(context,"fast_ec.db");
        final Database db=helper.getWritableDb();
        mDaoSession=new DaoMaster(db).newSession();
        mDao=mDaoSession.getUserProfileDao();
    }

    public final UserProfileDao getDao(){
        return mDao;
    }
}
