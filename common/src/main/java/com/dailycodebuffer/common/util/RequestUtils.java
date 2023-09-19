package com.dailycodebuffer.common.util;

public class RequestUtils {
    public static int getPage(Integer page){
        return ((page == null) || page < 1) ? 0 : page - 1;
    }

    public static int getSize(Integer size){
        return (size == null || size < 1) ? 10 : size;
    }
}
