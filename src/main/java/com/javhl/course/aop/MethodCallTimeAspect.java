package com.javhl.course.aop;

import com.javhl.course.dynmicproxy.ProxyTestInterface;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Description AOP测试类，统计接口运行时长
 */
@Component
@Aspect
public class AOPTest {

    @Pointcut("execution(* com.javhl..*.*(..))")
    private void myPointCut(){

    }

    @Around("myPointCut()")
    public Object advice (ProceedingJoinPoint joinPoint) throws Throwable{

        //方法调用开始时间
        long startTime = System.currentTimeMillis();
        Object result = null;
        try {
            result = joinPoint.proceed();
        }finally {

            long timeUsed = System.currentTimeMillis() - startTime;
            System.out.println(String.format("方法耗时:%d",timeUsed));
        }
        return result;
    }

    public static void main(String[] args){

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("testBeans.xml");

        String[] names = applicationContext.getBeanDefinitionNames();

        Arrays.stream(names).forEach(e->System.out.println(e));

        ProxyTestInterface proxyTest = (ProxyTestInterface) applicationContext.getBean("proxyTest");

        Integer ret = proxyTest.add(1,2);

        System.out.println(ret);

        ((ClassPathXmlApplicationContext) applicationContext).close();
    }

}
