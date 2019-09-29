package com.lm.ticker.service;

import com.lm.ticker.domain.entity.CustomerCommodityEntity;
import org.springframework.mail.SimpleMailMessage;

import java.util.List;

/**
 * @author LM
 * @version v1.0
 * @description:
 * @date 2019/9/29 9:31
 */
public interface MailService {

    /**
     * 发送普通文本邮件
     * @param to 收件人
     * @param content 内容
     */
    void sendSimpleMail(String to, String content);

    void sendSimpleMailBatch(List<CustomerCommodityEntity> customerCommodityEntities, String content);

}
