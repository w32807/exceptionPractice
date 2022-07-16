package com.study.exceptionpractice.web.exception.advice;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.Arrays;

@WebMvcTest
class BindExAdviceTest {

    @Autowired
    DefaultListableBeanFactory bf;

    @Autowired
    private BindExAdvice bindExAdvice;

    @Test
    @DisplayName("BindExAdvice가 Bean인지 확인하기")
    public void isBeanTest() throws Exception{
        String[] beanDefinitionNames = bf.getBeanDefinitionNames();

        Arrays.asList(beanDefinitionNames).forEach(beanName -> System.out.println("beanName = " + beanName));
        Assertions.assertThat(beanDefinitionNames).contains("bindExAdvice");
    }

    @DisplayName("BindExAdvice가 AOP의 프록시 객체인지 확인하기")
    public void isAopTest() throws Exception{
        boolean isAop = AopUtils.isAopProxy(bindExAdvice);

        Assertions.assertThat(isAop).isFalse();
    }

}