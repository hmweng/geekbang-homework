package com.study.proxy;

import com.study.cglib.ChainInvocationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: jicai
 * @Date: 2021/9/7
 * @Description:
 */
public class AroundInvokeInvocationHandler implements InvocationHandler {

    private final Object source;

    private final Object[] interceptors;

    public AroundInvokeInvocationHandler(Object source, Object... interceptors) {
        this.source = source;
        this.interceptors = interceptors;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        InvocationHandlerAdapter invocationHandlerAdapter = new InvocationHandlerAdapter(source, method, args);
        ChainInvocationContext chainInvocationContext = new ChainInvocationContext(invocationHandlerAdapter, interceptors);
        return chainInvocationContext.proceed();
    }
}
