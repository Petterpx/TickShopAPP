package com.petterp.latte_ui.date;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * @author Petterp on 2019/6/15
 * Summary:时间工具类
 * 邮箱：1509492795@qq.com
 */
public class DateDialogUtil {
    //选择时间后进行回调
    public interface IDateListener{
        void onDateChanage(String date);
    }
    private IDateListener mDateListener=null;
    public void setDateListener(IDateListener listener){
        this.mDateListener=listener;
    }
    public void showDialog(Context context){
        DatePickerDialog dialog = new DatePickerDialog(context, (view, year, month, dayOfMonth) -> {
            final Calendar calendar=Calendar.getInstance();
            calendar.set(year,month+1,dayOfMonth);
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
            final String data=simpleDateFormat.format(calendar.getTime());
            if (mDateListener != null) {
                mDateListener.onDateChanage(data);
            }
        },1990,1,1);
        dialog.setTitle("选择日期");
        dialog.show();
    }
}
