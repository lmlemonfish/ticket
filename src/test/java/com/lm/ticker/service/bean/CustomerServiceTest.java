package com.lm.ticker.service.bean;

import com.lm.ticker.TickerApplicationTests;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author LM
 * @version v1.0
 * @description:
 * @date 2019/9/29 11:46
 */
public class CustomerServiceTest extends TickerApplicationTests<CustomerService> {

    @Test
    public void test() {
        System.out.println(service.list());
    }


}