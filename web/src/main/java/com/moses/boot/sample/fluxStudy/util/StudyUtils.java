package com.moses.boot.sample.fluxStudy.util;

public class StudyUtils {

    public static void println(Object value){
        String threadName = Thread.currentThread().getName();
        System.out.printf("[线程: %s] %s \n", threadName, value);
    }
}
