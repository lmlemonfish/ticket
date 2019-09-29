package com.lm.ticker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TickerApplicationTests<S> {

    @Autowired
    protected S service;

    @Test
    public void contextLoads() {
    }

}
