package com.lm.ticker.service.impl;

import com.lm.ticker.constants.MailConstant;
import com.lm.ticker.domain.entity.CustomerCommodityEntity;
import com.lm.ticker.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LM
 * @version v1.0
 * @description:
 * @date 2019/9/29 9:32
 */
@Service
@Slf4j
public class MailServiceImpl implements MailService {

    @Resource
    private JavaMailSender javaMailSender;

    private final String subject = "【通知信息】";

    private String from;

    public MailServiceImpl(@Value("${spring.mail.username}") String from) {
        this.from = from;
    }

    @Override
    public void sendSimpleMail(String to, String content) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        simpleMailMessage.setFrom(from);

        try {
            javaMailSender.send(simpleMailMessage);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("发送邮件失败, 邮箱号:{}, e:{}", to, e);
        }

    }

    @Override
    public void sendSimpleMailBatch(List<CustomerCommodityEntity> customerCommodityEntities, String content) {

        List<SimpleMailMessage> batchRequest = new ArrayList<>();

        for (CustomerCommodityEntity info: customerCommodityEntities) {
            String email = info.getEmail();
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(email);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(content);
            simpleMailMessage.setFrom(from);
            batchRequest.add(simpleMailMessage);
        }

        SimpleMailMessage[] batchRe = new SimpleMailMessage[batchRequest.size()];

        try {
            javaMailSender.send(batchRequest.toArray(batchRe));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("群发送邮件失败, 邮箱:{}, e:{}", batchRe, e);
            sendSimpleMail(MailConstant.myNoticeEmail, "您好，群发邮件失败");
        }

    }

}
