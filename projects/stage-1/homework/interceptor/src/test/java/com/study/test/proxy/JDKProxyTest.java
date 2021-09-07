package com.study.test.proxy;

import com.study.interceptor.BulkheadInterceptor;
import com.study.interceptor.TimeoutInterceptor;
import com.study.proxy.AroundInvokeInvocationHandler;
import com.study.test.interceptor.ITestService;
import com.study.test.interceptor.TestService;
import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * @Author: jicai
 * @Date: 2021/9/7
 * @Description:
 */
public class JDKProxyTest {

    @Test
    public void testProxy() {
        ITestService iTestService = (ITestService)Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{
                ITestService.class
        }, new AroundInvokeInvocationHandler(new TestService(), new TimeoutInterceptor(), new BulkheadInterceptor()));
        iTestService.service("hello world");
    }

}
