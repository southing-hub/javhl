package com.javhl.course.dynmicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description 动态代理测试类
 */
public class DynamicProxyTest {

    public static void main(String[] args){

        ProxyTest proxyTest = new ProxyTest();

        InvocationHandler handler = new MyInvocationHandler(proxyTest);

        ProxyTestInterface proxyTestInterface = (ProxyTestInterface) Proxy.newProxyInstance(proxyTest.getClass().getClassLoader(),ProxyTest.class.getInterfaces(),handler);

        Integer ret = proxyTestInterface.add(1,2);

        System.out.println(ret);

        //将动态代理生成的类输出到硬盘上,便于查看
        ProxyUtils.generateClassFile(proxyTest.getClass(),"test");
    }

    static class MyInvocationHandler implements InvocationHandler{

        public MyInvocationHandler(ProxyTest test){

            proxyTest = test;
        }

        ProxyTest proxyTest;

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            System.out.println(method.getName());

            Object obj = method.invoke(proxyTest,args);

            return obj;
        }
    }
}
