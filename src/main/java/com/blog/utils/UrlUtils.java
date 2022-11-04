package com.blog.utils;

public class UrlUtils {
    public static String getUrl(String str){
        String url =  str.trim().toLowerCase().replaceAll("\\s+", "-");
        url = url.replaceAll("[^A-Za-z0-9]", "-");
        return url;
    }
}
