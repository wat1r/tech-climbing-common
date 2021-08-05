package com.frankcooper.akka;

import org.springframework.stereotype.Service;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/8/4 17:06
 * @description:
 */
@Service
public class BusinessService {


    public void perform(Object o) {

        System.out.print("this is a test\n");
    }
}