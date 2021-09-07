package com.study.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: jicai
 * @Date: 2021/9/7
 * @Description:
 */
public class AroundInvokeMethodInterceptor implements MethodInterceptor {

    private final Object[] interceptors;

    public AroundInvokeMethodInterceptor(Object... interceptors) {
        this.interceptors = interceptors;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        MethodInterceptorAdapter methodInterceptorAdapter = new MethodInterceptorAdapter(obj, method, args, proxy);
        ChainInvocationContext chainInvocationContext = new ChainInvocationContext(methodInterceptorAdapter, interceptors);
        return chainInvocationContext.proceed();
    }
}
