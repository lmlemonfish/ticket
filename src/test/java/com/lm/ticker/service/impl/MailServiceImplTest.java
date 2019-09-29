package com.lm.ticker.service.impl;

import com.lm.ticker.TickerApplicationTests;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author LM
 * @version v1.0
 * @description:
 * @date 2019/9/29 9:37
 */
public class MailServiceImplTest extends TickerApplicationTests<MailServiceImpl> {

    @Test
    public void sendSimpleMail() {

        service.sendSimpleMail("1005131949@qq.com", "您好, 测试内容");
    }
}