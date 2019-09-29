package com.lm.ticker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lm.ticker.domain.entity.CustomerCommodityEntity;
import com.lm.ticker.domain.entity.CustomerEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author LM
 * @version v1.0
 * @description:
 * @date 2019/9/29 11:44
 */
@Mapper
public interface CustomerCommodityMapper extends BaseMapper<CustomerCommodityEntity> {
}
