package com.study.test.interceptor;

import org.eclipse.microprofile.faulttolerance.Asynchronous;
import org.eclipse.microprofile.faulttolerance.Bulkhead;
import org.eclipse.microprofile.faulttolerance.Timeout;

import java.time.temporal.ChronoUnit;

/**
 * @Author: jicai
 * @Date: 2021/9/7
 * @Description:
 */
@Bulkhead
@Timeout(value = 1000, unit = ChronoUnit.NANOS)
public interface ITestService {
    void service(String str);

    void serviceTime(String str);

    String serviceAsyn(String str);
}
