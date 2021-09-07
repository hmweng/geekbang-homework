package com.study.test.interceptor;

import org.eclipse.microprofile.faulttolerance.Asynchronous;
import org.eclipse.microprofile.faulttolerance.Bulkhead;
import org.eclipse.microprofile.faulttolerance.Timeout;

import java.time.temporal.ChronoUnit;

/**
 * @Author: jicai
 * @Date: 2021/8/31
 * @Description:
 */
@Bulkhead
@Timeout(value = 1000, unit = ChronoUnit.NANOS)
public class TestService implements ITestService/* extends TestSuperService*/ {

    @Override
    public void service(String str) {
        System.out.println(str);
    }

    @Override
    public void serviceTime(String str) {
        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println(str);
    }

    @Override
    @Asynchronous
    public String serviceAsyn(String str) {
        System.out.println(str);
        return str + " thank";
    }

}
