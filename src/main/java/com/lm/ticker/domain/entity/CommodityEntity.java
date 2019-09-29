package com.lm.ticker.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author LM
 * @version v1.0
 * @description:
 * @date 2019/9/29 11:37
 */
@EqualsAndHashCode(callSuper = true)
@TableName("commodity")
@Data
public class CommodityEntity extends IdEntity{

    private Long itemId;

    private String itemName;

}
