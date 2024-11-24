package com.example.ff4j_api.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String getCurrentTime(){
        Date time = new Date(System.currentTimeMillis());
        return new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(time);
    }
}