package com.study.proxy;

import javax.interceptor.InvocationContext;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Author: jicai
 * @Date: 2021/9/7
 * @Description:
 */
public class InvocationHandlerAdapter implements InvocationContext {

    private final Object target;

    private final Method method;

    private Object[] args;

    public InvocationHandlerAdapter(Object target, Method method, Object[] args) {
        this.target = target;
        this.method = method;
        this.args = args;
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
        return args;
    }

    @Override
    public void setParameters(Object[] params) {
        this.args = params;
    }

    @Override
    public Map<String, Object> getContextData() {
        return null;
    }

    @Override
    public Object proceed() throws Exception {
        return method.invoke(getTarget(), getParameters());
    }
}
