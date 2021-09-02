package com.study.test.interceptor;

import org.eclipse.microprofile.faulttolerance.Bulkhead;
import org.eclipse.microprofile.faulttolerance.Timeout;

import java.time.temporal.ChronoUnit;

/**
 * @Author: jicai
 * @Date: 2021/8/31
 * @Description:
 */
@Bulkhead
@Timeout(value = 1, unit = ChronoUnit.NANOS)
public class TestService extends TestSuperService {

//    @Asynchronous
    public void service(String str) {
        System.out.println(str);
    }

    public void serviceTime(String str) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(str);
    }

}
