package com.lm.ticker.scheduler;

import com.lm.ticker.TickerApplicationTests;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author LM
 * @version v1.0
 * @description:
 * @date 2019/9/29 13:51
 */
public class NotifySchedulerTest extends TickerApplicationTests<NotifyScheduler> {

    @Test
    public void doLister() {

        service.doLister();

    }
}