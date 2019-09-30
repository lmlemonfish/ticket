package com.lm.ticker.service.impl;

import com.lm.ticker.domain.dto.DynStoreDto;
import com.lm.ticker.domain.dto.TradeDto;
import com.lm.ticker.service.TicketService;
import com.lm.ticker.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;

/**
 * @author LM
 * @version v1.0
 * @description:
 * @date 2019/9/29 10:26
 */
@Service
@Slf4j
public class TicketServiceImpl implements TicketService {

    @Resource
    private RestTemplate restTemplate;

    public TradeDto getTradeDto(String itemId) {

        String uriStr = String.format("https://mdskip.taobao.com/core/initItemDetail.htm?itemId=%s", itemId);
        URI uri = URI.create(uriStr);

        MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        header.add("Referer", "https://detail.tmall.com/item.htm");
        header.add("cookie", "thw=cn; enc=Oq46VnL6nvwc92%2B0E3kE%2FmyH3O9DT9dJr1qMYeWK6JVSfXuOC2vSbpln%2BUGAgSmV%2FBNF4yE3GiguLmaEcfrtQg%3D%3D; hng=CN%7Czh-CN%7CCNY%7C156; x=e%3D1%26p%3D*%26s%3D0%26c%3D0%26f%3D0%26g%3D0%26t%3D0; t=6befd150f25cb5cc9ceb92f2e48ef347; cookie2=15a0a57685b37fdb133499b622f5f6d8; _tb_token_=338866e13dd7; UM_distinctid=16d72230dda2cf-0b02333af84509-3c375c0f-13c680-16d72230ddb3b5; mt=ci=0_0; cna=jrLZFPnEtTYCAXeIkQ3zGEyQ; v=0; isg=BHd3HHU35sPLQWOjvCVHzv2sBmsBlECVKq5w3Mkkk8ateJe60Qzb7jVZWpiDkCMW; l=cBai7rqnv-7YK6vOBOCwourza77OSIRAguPzaNbMi_5ZV1Ysnw_Ok6wc9ev6VjWd_YLB4rRpHn99-etkwh9XJfIpXUJ1.");

        HttpEntity<Object> httpEntity = new HttpEntity<>(null, header);

        ResponseEntity<String> result = request(uri, HttpMethod.GET, httpEntity, String.class);
        String body = result.getBody();

        return JsonUtils.toPojo(body, TradeDto.class);
    }


    public DynStoreDto getDynStoreDto(String itemId) {

        String uriStr = String.format("https://detailskip.taobao.com/service/getData/1/p2/item/detail/sib.htm?itemId=%s&modules=qrcode,viewer,price,contract,duty,xmpPromotion,dynStock,delivery,upp,sellerDetail,activity,fqg,zjys,coupon&callback=onSibRequestSuccess", itemId);
        URI uri = URI.create(uriStr);

        MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        header.add("Referer", "https://item.taobao.com/item.htm");

        HttpEntity<Object> httpEntity = new HttpEntity<>(null, header);

        ResponseEntity<String> result = request(uri, HttpMethod.GET, httpEntity, String.class);
        String body = result.getBody();
        assert body != null;
        body = body.replace("onSibRequestSuccess(", "");
        body = body.replace(");", "");

        return JsonUtils.toPojo(body, DynStoreDto.class);
    }


    /**
     * 封装request请求
     * @param url
     * @param method
     * @param requestEntity
     * @param classType
     * @param <T>
     * @return
     */
    private <T> ResponseEntity<T> request(URI url, HttpMethod method, @Nullable HttpEntity<?> requestEntity, Class<T> classType) {
        ResponseEntity<T> result = null;
        try {
            result = restTemplate.exchange(url, method, requestEntity, classType);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Request请求发送失败，失败原因：{}", e);
            throw new RuntimeException("Request请求发送失败");
        }
        return result;
    }



}
