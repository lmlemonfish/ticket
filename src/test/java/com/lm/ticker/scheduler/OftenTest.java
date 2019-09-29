package com.lm.ticker.scheduler;

import org.junit.Test;

import java.util.Random;

/**
 * @author LM
 * @version v1.0
 * @description:
 * @date 2019/9/29 14:49
 */
public class OftenTest {

    @Test
    public void test() {
        Random random = new Random();
        System.out.println(random.nextInt(2000));
    }

}
