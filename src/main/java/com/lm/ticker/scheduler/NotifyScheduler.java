package com.lm.ticker.scheduler;

import com.lm.ticker.config.LoopTimeConfig;
import com.lm.ticker.constants.MailConstant;
import com.lm.ticker.domain.dto.DynStoreDto;
import com.lm.ticker.domain.dto.TradeDto;
import com.lm.ticker.domain.entity.CustomerCommodityEntity;
import com.lm.ticker.enums.ShelfEnum;
import com.lm.ticker.service.MailService;
import com.lm.ticker.service.TicketServicePlus;
import com.lm.ticker.service.bean.CustomerCommodityService;
import com.lm.ticker.service.impl.TicketServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @author LM
 * @version v1.0
 * @description:
 * @date 2019/9/29 10:16
 */
@Component
@Slf4j
public class NotifyScheduler {

    @Resource
    private TicketServicePlus ticketServicePlus;

    @Resource
    private MailService mailService;

    @Resource
    private CustomerCommodityService customerCommodityService;

    @Resource
    private LoopTimeConfig loopTimeConfig;

    @Scheduled(fixedRate = 50 * 1000)
    public void doLister() {

        try {
            //随机时间
            Thread.sleep(loopTimeConfig.getRandomTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<CustomerCommodityEntity> list = customerCommodityService.list();
        Map<String, List<CustomerCommodityEntity>> groupByItemId = list.stream().collect(Collectors.groupingBy(CustomerCommodityEntity::getItemId));
        Set<Map.Entry<String, List<CustomerCommodityEntity>>> entries = groupByItemId.entrySet();
        for (Map.Entry<String, List<CustomerCommodityEntity>> item: entries) {
            List<CustomerCommodityEntity> value = item.getValue();
            String noticeStr = ticketServicePlus.getNoticeStr(item.getKey(), value.get(0));
            if (!StringUtils.isEmpty(noticeStr)) {

                List<CustomerCommodityEntity> itemValue = item.getValue();
                mailService.sendSimpleMailBatch(itemValue, noticeStr);
            }
        }
    }


    @PreDestroy
    public void closeNotice() {
        mailService.sendSimpleMail(MailConstant.myNoticeEmail, "服务器关闭");
    }

}
