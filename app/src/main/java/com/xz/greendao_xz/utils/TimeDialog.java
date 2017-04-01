package com.xz.greendao_xz.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;


import com.xz.greendao_xz.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * lys
 * 可以隐藏年月也可以隐藏时分，也可以全部显示
 */
public class TimeDialog extends Dialog implements DatePicker.OnDateChangedListener, TimePicker.OnTimeChangedListener {
    private String initDateTime;
    private Context context;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private String dateTime;
    private Button cancelBtn, ensureBtn;
    private String goneStr = "";
    int year, month, day, hour, min;


    private OnSaveButtonClickListener clickListener;

    public interface OnSaveButtonClickListener {
        void onSaveClick(String date);
    }

    public void setOnSaveButtonClickListener(
            OnSaveButtonClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public TimeDialog(Context context) {
        super(context);
        this.context = context;
    }

    public TimeDialog(Context context, String initDateTime, String goneStr) {
        super(context);
        this.context = context;
        this.initDateTime = initDateTime;
        this.goneStr = goneStr;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent);
        setContentView(R.layout.dialog_datetime);
        initView();
        init();
        initEvent();
    }

    private void initEvent() {
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        ensureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onSaveClick(getDateTime());
                dismiss();
            }
        });
    }

    private String getDateTime() {
        if (TextUtils.isEmpty(dateTime)) {
            if (goneStr.equals("隐藏时分")) {
                dateTime = DateUtils.getTodayDate();
            } else if (goneStr.equals("隐藏年月日")) {
                dateTime = DateUtils.getTodayDateHourMin();
            } else {
                dateTime = DateUtils.getTodayDate() + DateUtils.getTodayDateHourMin();
            }
        }
        return dateTime;
    }

    private void init() {
        timePicker.setIs24HourView(true);
        timePicker.setOnTimeChangedListener(this);
        if (goneStr.equals("隐藏时分")) {
            timePicker.setVisibility(View.GONE);
            year = DateUtils.stringChinaCalendar(initDateTime).get(Calendar.YEAR);
            month = DateUtils.stringChinaCalendar(initDateTime).get(Calendar.MONTH);
            day = DateUtils.stringChinaCalendar(initDateTime).get(Calendar.DAY_OF_MONTH);
            init(datePicker, year, month, day);
        } else if (goneStr.equals("隐藏年月日")) {
            datePicker.setVisibility(View.GONE);
            hour = Integer.parseInt(initDateTime.split(":")[0]);
            min = Integer.parseInt(initDateTime.split(":")[1]);
            init(timePicker, hour, min);

        } else {
            init(datePicker, timePicker);
        }
    }

    public void init(DatePicker datePicker, int year, int month, int day) {
//        Calendar calendar = Calendar.getInstance();
        datePicker.init(year, month, day, this);
//        datePicker.updateDate(year, month, day);

    }

    public void init(TimePicker timePicker, int hour, int min) {
        Calendar calendar = Calendar.getInstance();
        if (!((hour + "").equals("00") && (hour + "").equals("23"))) {
            timePicker.setCurrentHour(hour);
            timePicker.setCurrentMinute(min);
        } else {
            timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
            timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
        }
//        datePicker.updateDate(year, month, day);

    }

    private void initView() {
        cancelBtn = (Button) findViewById(R.id.btn_dialog_date_cancel);
        ensureBtn = (Button) findViewById(R.id.btn_dialog_date_ensure);
        datePicker = (DatePicker) findViewById(R.id.datepicker);
        timePicker = (TimePicker) findViewById(R.id.timepicker);
    }

    private void init(DatePicker datePicker, TimePicker timePicker) {
        Calendar calendar = Calendar.getInstance();

        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), this);
        timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
        timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
    }

    private String formatDateTime(Calendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        return sdf.format(calendar.getTime());
    }

    private Calendar getCalendarByInintData(String initDateTime) {
        Calendar calendar = Calendar.getInstance();
        // 将初始日期时间2012年07月02日 16:45 拆分成年 月 日 时 分 秒
        String date = spliteString(initDateTime, " ", "index", "front"); // 日期
        String time = spliteString(initDateTime, " ", "index", "back"); // 时间
        String yearStr = spliteString(date, "-", "index", "front"); // 年份
        String monthAndDay = spliteString(date, "-", "index", "back"); // 月日
        String monthStr = spliteString(monthAndDay, "-", "index", "front"); // 月
        String dayStr = spliteString(monthAndDay, "-", "index", "back"); // 日
        String hourStr = spliteString(time, ":", "index", "front"); // 时
        String minuteAndSec = spliteString(time, ":", "index", "back"); // 分秒
        String minuteStr = spliteString(minuteAndSec, ":", "index", "front"); // 分
        String secondStr = spliteString(minuteAndSec, ":", "index", "back"); // 秒

        int currentYear = Integer.valueOf(yearStr.trim()).intValue();
        int currentMonth = Integer.valueOf(monthStr.trim()).intValue() - 1;
        int currentDay = Integer.valueOf(dayStr.trim()).intValue();
        int currentHour = Integer.valueOf(hourStr.trim()).intValue();
        int currentMinute = Integer.valueOf(minuteStr.trim()).intValue();

        calendar.set(currentYear, currentMonth, currentDay, currentHour,
                currentMinute);
        return calendar;
    }

    public static String spliteString(String srcStr, String pattern,
                                      String indexOrLast, String frontOrBack) {
        String result = "";
        int loc = -1;
        if (indexOrLast.equalsIgnoreCase("index")) {
            loc = srcStr.indexOf(pattern); // 取得字符串第一次出现的位置
        } else {
            loc = srcStr.lastIndexOf(pattern); // 最后一个匹配串的位置
        }
        if (frontOrBack.equalsIgnoreCase("front")) {
            if (loc != -1)
                result = srcStr.substring(0, loc); // 截取子串
        } else {
            if (loc != -1)
                result = srcStr.substring(loc + 1, srcStr.length()); // 截取子串
        }
        return result;
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        // 获得日历实例
        Calendar calendar = Calendar.getInstance();

        calendar.set(datePicker.getYear(), datePicker.getMonth(),
                datePicker.getDayOfMonth(), timePicker.getCurrentHour(),
                timePicker.getCurrentMinute());
        if (goneStr.equals("隐藏时分")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
            dateTime = sdf.format(calendar.getTime());
        } else if (goneStr.equals("隐藏年月日")) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            dateTime = sdf.format(calendar.getTime());
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateTime = sdf.format(calendar.getTime());
        }

    }

    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        onDateChanged(null, 0, 0, 0);
    }
}
