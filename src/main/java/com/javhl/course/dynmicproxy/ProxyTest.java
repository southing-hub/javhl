package com.javhl.course.dynmicproxy;

import org.springframework.stereotype.Component;

/**
 * @Description 实体类
 */
@Component
public class ProxyTest implements ProxyTestInterface{

    @Override
    public Integer add(Integer a, Integer b) {

        return a+b;
    }

}
