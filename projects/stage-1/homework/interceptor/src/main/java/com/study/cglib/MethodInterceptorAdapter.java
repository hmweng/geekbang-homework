package com.study.cglib;

import net.sf.cglib.proxy.MethodProxy;

import javax.interceptor.InvocationContext;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Author: jicai
 * @Date: 2021/9/7
 * @Description:
 */
public class MethodInterceptorAdapter implements InvocationContext {

    private final Object target;

    private final Method method;

    private Object[] parameter;

    private final MethodProxy proxy;

    public MethodInterceptorAdapter(Object target, Method method, Object[] parameter, MethodProxy proxy) {
        this.target = target;
        this.method = method;
        this.parameter = parameter;
        this.proxy = proxy;
    }

    @Override
    public Object getTarget() {
        return target;
    }

    @Override
    public Object getTimer() {
        return null;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Constructor<?> getConstructor() {
        return null;
    }

    @Override
    public Object[] getParameters() {
        return parameter;
    }

    @Override
    public void setParameters(Object[] params) {
        this.parameter = params;
    }

    @Override
    public Map<String, Object> getContextData() {
        return null;
    }

    @Override
    public Object proceed() throws Exception {
        try {
            return proxy.invokeSuper(getTarget(), getParameters());
        } catch (Throwable throwable) {
            throw new Exception(throwable);
        }
    }
}
