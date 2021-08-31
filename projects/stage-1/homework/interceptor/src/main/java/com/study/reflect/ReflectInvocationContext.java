package com.study.reflect;

import javax.interceptor.InvocationContext;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: jicai
 * @Date: 2021/8/31
 * @Description:
 */
public class ReflectInvocationContext implements InvocationContext {

    private final Object target;

    private final Method method;

    private Object[] paramters;

    private Map<String, Object> contextData;

    public ReflectInvocationContext(Object target, Method method, Object... paramters) {
        this.target = target;
        this.method = method;
        this.paramters = paramters;
        contextData = new HashMap<>();
    }

    @Override
    public Object getTarget() {
        return target;
    }

    @Override
    public Object getTimer() {
        throw new RuntimeException();
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Constructor<?> getConstructor() {
        throw new RuntimeException();
    }

    @Override
    public Object[] getParameters() {
        return paramters;
    }

    @Override
    public void setParameters(Object[] params) {
        this.paramters = params;
    }

    @Override
    public Map<String, Object> getContextData() {
        return contextData;
    }

    @Override
    public Object proceed() throws Exception {
        return method.invoke(getTarget(), getParameters());
    }
}
