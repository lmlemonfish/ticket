package com.lm.ticker.service.impl;

import com.lm.ticker.TickerApplicationTests;
import com.lm.ticker.domain.dto.DynStoreDto;
import com.lm.ticker.domain.dto.TradeDto;
import org.junit.Test;

/**
 * @author LM
 * @version v1.0
 * @description:
 * @date 2019/9/29 10:33
 */
public class TicketServiceImplTest extends TickerApplicationTests<TicketServiceImpl> {

    @Test
    public void getTradeDto() throws Exception {

        TradeDto tradeDto = service.getTradeDto("599834886497");
        System.out.println(tradeDto);
    }


    @Test
    public void getDynStoreDto() throws Exception {

        DynStoreDto dynStoreDto = service.getDynStoreDto("599834886497");
        System.out.println(dynStoreDto);
    }
}