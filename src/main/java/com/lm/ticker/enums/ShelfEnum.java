package com.lm.ticker.enums;

import lombok.Getter;

/**
 * @author LM
 * @version v1.0
 * @description:
 * @date 2019/9/29 15:48
 */
@Getter
public enum ShelfEnum {

    /**
     * XX
     */

    UN_SHELF(0, "下架"),

    SHELF(1, "上架");

    private Integer code;

    private String msg;

    ShelfEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
