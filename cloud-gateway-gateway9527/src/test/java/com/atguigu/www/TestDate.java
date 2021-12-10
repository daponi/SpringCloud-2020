package com.atguigu.www;

import org.junit.Test;

import java.time.ZonedDateTime;

//@SpringBootTest
public class TestDate {
    @Test
    public void TestGetDate(){
        ZonedDateTime now =  ZonedDateTime.now();
        System.out.println(now);
    }
}
