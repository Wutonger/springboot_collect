package cn.lxfun.redis_boot.Service;

/**
 * @author luoxiang
 * @date 2020/1/20 0020 下午 15:52
 * @description
 **/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * redis实现sub/pub 消息队列
 */
@Service
public class PubSubService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 向myChannel的管道中发送一条消息
     * @param message
     * @throws Exception
     */
    public void sendMessage(String message) throws Exception{
        redisTemplate.convertAndSend("myChannel",message);
    }
}
