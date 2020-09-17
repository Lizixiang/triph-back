package tripleh.happyhappyhappy.com.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Author: zixli
 * Date: 2020/9/16 13:47
 * FileName: RedisUUID
 * Description: 唯一ID保存到redis
 */
@Component
public class RedisUUID {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    // 过期时间
    private final static long expiration = 1000*60*5;

    // 过期前一分钟
    private final static long lastTime = 1000*60;

    /**
     * 根据key保存唯一ID到redis
     * @param key
     * @return
     */
    public String create(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        String secretKey = "";
        if (redisTemplate.hasKey(key)) {
            if (redisTemplate.boundHashOps(key).getExpire() < lastTime) {
                redisTemplate.opsForValue().set(key, DigestUtils.md5Hex(UUID.randomUUID().toString()), expiration, TimeUnit.SECONDS);
            }
            secretKey = (String) redisTemplate.opsForValue().get(key);
        } else {
            secretKey = DigestUtils.md5Hex(UUID.randomUUID().toString());
            redisTemplate.opsForValue().set(key, secretKey, expiration, TimeUnit.SECONDS);
        }
        return secretKey;
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
