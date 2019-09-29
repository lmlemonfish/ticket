package com.lm.ticker.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LM
 * @version v1.0
 * @description:
 * @date 2019/9/29 10:48
 */
@NoArgsConstructor
@Data
public class DynStoreDto {


    /**
     * code : {"code":0,"message":"SUCCESS"}
     * data : {"viewer":{"admin":false,"bs":"","buyDomain":"buy.taobao.com","buyerId":"","cartDomain":"cart.taobao.com","cc":false,"ctUser":false,"lgin":false,"serviceTab":"ITEM","tkn":"e79519fe1beb8"},"deliveryFee":{"data":{"areaId":440300,"areaName":"广东深圳","sendCity":"江苏无锡","serviceInfo":{"list":[{"id":"100_-4","info":"快递 <span class=\"wl-yen\">&yen;<\/span>10.00","isDefault":true}]}},"dataUrl":"//detailskip.taobao.com/json/deliveryFee.htm","message":"ok","success":true},"activity":{},"price":"569.00","dynStock":{"holdQuantity":0,"sellableQuantity":2,"stock":2,"stockType":"normal"}}
     */

    private CodeBean code;
    private DataBeanX data;

    @NoArgsConstructor
    @Data
    public static class CodeBean {
        /**
         * code : 0
         * message : SUCCESS
         */

        private Integer code;
        private String message;
    }

    @NoArgsConstructor
    @Data
    public static class DataBeanX {
        /**
         * viewer : {"admin":false,"bs":"","buyDomain":"buy.taobao.com","buyerId":"","cartDomain":"cart.taobao.com","cc":false,"ctUser":false,"lgin":false,"serviceTab":"ITEM","tkn":"e79519fe1beb8"}
         * deliveryFee : {"data":{"areaId":440300,"areaName":"广东深圳","sendCity":"江苏无锡","serviceInfo":{"list":[{"id":"100_-4","info":"快递 <span class=\"wl-yen\">&yen;<\/span>10.00","isDefault":true}]}},"dataUrl":"//detailskip.taobao.com/json/deliveryFee.htm","message":"ok","success":true}
         * activity : {}
         * price : 569.00
         * dynStock : {"holdQuantity":0,"sellableQuantity":2,"stock":2,"stockType":"normal"}
         */

        private String price;
        private DynStockBean dynStock;

        @NoArgsConstructor
        @Data
        public static class DynStockBean {
            /**
             * holdQuantity : 0
             * sellableQuantity : 2
             * stock : 2
             * stockType : normal
             */

            private Integer holdQuantity;
            private Integer sellableQuantity;
            private Integer stock;
            private String stockType;
        }
    }
}
