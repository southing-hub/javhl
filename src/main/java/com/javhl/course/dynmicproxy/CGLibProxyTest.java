package com.javhl.course.dynmicproxy;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Description TODO
 */
public class CGLibProxyTest{


    public static void main(String[] args){

        // 代理类class文件存入本地磁盘方便我们反编译查看源码
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\code");

        MethodInterceptor methodInterceptor = new MyMethodInterceptor();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ProxyTest.class);
        enhancer.setCallback(methodInterceptor);

        ProxyTest proxyTest = (ProxyTest) enhancer.create();
        Integer ret = proxyTest.add(1,2);

        System.out.println(ret);

    }

    static class MyMethodInterceptor implements MethodInterceptor{

        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

            System.out.println(o.getClass());

            //调用父类的方法
            Object ret = methodProxy.invokeSuper(o,objects);

            return ret;
        }

    }
}