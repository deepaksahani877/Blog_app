package com.blog.utils;

public class UrlUtils {
    public static String getUrl(String str){
        return str.trim().toLowerCase().replaceAll("\\s+", "-").replaceAll("[^A-Za-z0-9]", "-");
    }
}
