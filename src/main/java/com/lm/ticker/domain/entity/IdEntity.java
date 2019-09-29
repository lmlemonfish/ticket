package com.lm.ticker.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author LM
 * @version v1.0
 * @description:
 * @date 2019/9/29 11:40
 */
@Data
public class IdEntity {

    @TableId(type = IdType.AUTO)
    protected Long id;

}
