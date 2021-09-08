package com.academia.crosstrainer.helper;

import java.text.SimpleDateFormat;

public class DateUtil {

    public static String dateActual(){
        long date = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return  simpleDateFormat.format(date);
    }
    public static String dateTimeActual(){
        long date = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        return  simpleDateFormat.format(date);
    }
    public static String dateActualShort(){
        long date = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM");
        return  simpleDateFormat.format(date);
    }
    public static String getMonthYear(String date){
        String ret[] = date.split("/");
        return ret[2]+ret[1];
    }

}
