package com.academia.crosstrainer.helper;

import java.text.SimpleDateFormat;

public class DateUtil {

    public static String dateActual(){
        long date = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        return  simpleDateFormat.format(date);
    }
}
