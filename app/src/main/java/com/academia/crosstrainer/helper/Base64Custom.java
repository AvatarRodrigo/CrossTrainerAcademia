package com.academia.crosstrainer.helper;

import android.util.Base64;

public class Base64Custom {
    public static String codeBase64(String str){
        return Base64.encodeToString(str.getBytes(), Base64.DEFAULT).replaceAll("(\\n|\\r)","");
    }

    public static String decodeBase64(String str){
        return new String(Base64.decode(str,Base64.DEFAULT));
    }

}
