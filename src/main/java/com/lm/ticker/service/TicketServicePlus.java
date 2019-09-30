package com.lm.ticker.service;

import com.lm.ticker.constants.MailConstant;
import com.lm.ticker.domain.dto.DynStoreDto;
import com.lm.ticker.domain.dto.TradeDto;
import com.lm.ticker.domain.entity.CustomerCommodityEntity;
import com.lm.ticker.enums.ShelfEnum;
import com.lm.ticker.service.bean.CustomerCommodityService;
import com.lm.ticker.service.impl.TicketServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author LM
 * @version v1.0
 * @description:
 * @date 2019/9/30 10:02
 */
@Service
@Slf4j
public class TicketServicePlus {

    @Resource
    private TicketServiceImpl ticketServiceImpl;

    @Resource
    private MailService mailService;

    @Resource
    private CustomerCommodityService customerCommodityService;

    /**
     * 1.判断是否发送通知
     * 2.构造通知消息
     * @param itemId
     * @param config
     * @return
     */
    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 5000L, multiplier = 1.5))
    public String getNoticeStr(String itemId, CustomerCommodityEntity config) {
        TradeDto tradeDto = null;
        DynStoreDto dynStoreDto = null;
        try {
            tradeDto = ticketServiceImpl.getTradeDto(itemId);
            dynStoreDto = ticketServiceImpl.getDynStoreDto(itemId);
        } catch (Exception e) {
            log.error("数据返回异常，e:{}", e);
            mailService.sendSimpleMail(MailConstant.myNoticeEmail, "数据返回异常");
            throw new RuntimeException("数据返回异常");
        }

        log.info("tradeDto:{}, dynStoreDto:{}", tradeDto, dynStoreDto);
        if (tradeDto == null || dynStoreDto == null) {
            mailService.sendSimpleMail(MailConstant.myNoticeEmail, "数据返回异常");
            log.error("数据返回异常, tradeDto:{}, dynStoreDto:{}", tradeDto, dynStoreDto);
            throw new RuntimeException("数据返回异常");
        }


        TradeDto.DefaultModelBean defaultModel = tradeDto.getDefaultModel();
        TradeDto.DefaultModelBean.TradeResultBean tradeResult = defaultModel.getTradeResult();
        Boolean cartEnable = tradeResult.getCartEnable();
        Boolean tradeEnable = tradeResult.getTradeEnable();

        DynStoreDto.DataBeanX data = dynStoreDto.getData();
        DynStoreDto.DataBeanX.DynStockBean dynStock = data.getDynStock();
        String price = data.getPrice();
        Integer sellableQuantity = dynStock.getSellableQuantity();
        Integer holdQuantity = dynStock.getHoldQuantity();

        String itemName = config.getItemName();
        Integer shelfed = config.getShelfed();
        log.info("{}信息, itemId:{}, cartEnable:{}, tradeEnable:{}, price: {}, sellableQuantity: {}, holdQuantity: {}", itemName, itemId, cartEnable, tradeEnable, price, sellableQuantity, holdQuantity);

        if (tradeEnable) {

            if (ShelfEnum.UN_SHELF.getCode().equals(shelfed)) {
                customerCommodityService.updateNotify(itemId, ShelfEnum.SHELF.getCode());
                return String.format("您好, 商品上架通知, 商品ID为：%s, 商品名称: %s, 价格：%s, 当前总可售数量：%s, 淘宝后台总保留数量: %s", itemId, itemName, price, sellableQuantity, holdQuantity);
            }

        } else {
            customerCommodityService.updateNotify(itemId, ShelfEnum.UN_SHELF.getCode());
        }
        return null;
    }


}
