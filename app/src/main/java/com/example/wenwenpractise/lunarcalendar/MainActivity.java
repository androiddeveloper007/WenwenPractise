package com.example.wenwenpractise.lunarcalendar;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.widget.TextView;

import com.example.wenwenpractise.R;

import java.util.Calendar;

public class MainActivity extends WearableActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.text);
        String str = "月像角度:" + calculateMoonfaceDegree() + getStr();
        tv.setText(str);
    }


    /**
     * @return 月相角度
     */
    private float calculateMoonfaceDegree() {
        int startDegree = 23;
        int totalDegree = 179;
        Calendar today = Calendar.getInstance();
        LunarCalendar lunar = LunarCalendar.solar2Lunar(today);//公历转农历
        int daysLengthOfMonth = (int) LunarUtils.lengthOfMonth(lunar.getLunarYear(), lunar.getLunarMonth(), lunar.isLeapMonth());
        float degreePercent = (float) (lunar.getDayOfLunarMonth() - 1) / (float) daysLengthOfMonth;
        return degreePercent * totalDegree + startDegree;
    }

    private String getStr() {
        Calendar today = Calendar.getInstance();
        LunarCalendar lunar = LunarCalendar.solar2Lunar(today);
        int month = lunar.getLunarMonth();
        return " 农历:" + month + "月" + lunar.getDayOfLunarMonth() + "日";
    }
}
