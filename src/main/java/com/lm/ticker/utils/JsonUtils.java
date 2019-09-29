package com.lm.ticker.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author tang
 * @date 2018/11/16
 */
@Slf4j
public class JsonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        //禁用 未知字段失败
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //启用 空字符串转null
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    }

    public static String toJson(Object o) {
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logger.warn("Fail to transfer object {} to String,throws Exception {}", o, e);
        }
        return null;
    }

    public static <T> T toPojo(String json, Class<T> clz) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        try {
            return mapper.readValue(json, clz);
        } catch (IOException e) {
            logger.warn("Fail to transfer Json {} to Pojo {},throws Exception {}", json, clz, e);
        }
        return null;
    }

    public static <T> List<T> toList(String json, Class<T> eleClz) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        JavaType listType = mapper.getTypeFactory().constructCollectionType(List.class, eleClz);
        try {
            return mapper.readValue(json, listType);
        } catch (IOException e) {
            e.printStackTrace();
            logger.warn("Fail to transfer Json {} to List Element type {},throws Exception {}", json, eleClz, e);
        }
        return null;
    }

    public static Map toMap(String json) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        try {
            return mapper.readValue(json, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
            logger.warn("Fail to transfer Map,throws Exception {}", json, e);
        }
        return null;
    }

}
