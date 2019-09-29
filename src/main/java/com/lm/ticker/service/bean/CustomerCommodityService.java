package com.lm.ticker.service.bean;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lm.ticker.constants.MailConstant;
import com.lm.ticker.domain.entity.CustomerCommodityEntity;
import com.lm.ticker.domain.entity.CustomerEntity;
import com.lm.ticker.mapper.CustomerCommodityMapper;
import com.lm.ticker.mapper.CustomerMapper;
import com.lm.ticker.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author LM
 * @version v1.0
 * @description:
 * @date 2019/9/29 11:45
 */
@Service
@Slf4j
public class CustomerCommodityService extends ServiceImpl<CustomerCommodityMapper, CustomerCommodityEntity> {

    @Resource
    private MailService mailService;

    public void updateNotify(String itemId, Integer shelfed) {
        UpdateWrapper<CustomerCommodityEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("shelfed", shelfed);
        updateWrapper.eq("item_id", itemId);
        if (!this.update(updateWrapper)) {
            try {
                mailService.sendSimpleMail(MailConstant.myNoticeEmail, "修改数据库字段失败");
            } catch (Exception e) {
                e.printStackTrace();
                log.error("错误通知发送失败, e:{}", e);
            }
        }
    }


}
