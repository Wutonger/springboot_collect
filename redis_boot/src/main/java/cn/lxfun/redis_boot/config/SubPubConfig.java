package cn.lxfun.redis_boot.config;

/**
 * @author luoxiang
 * @date 2020/1/20 0020 下午 16:32
 * @description
 **/

import cn.lxfun.redis_boot.Service.Receiver;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;


@Configuration
@AutoConfigureAfter({Receiver.class})
public class SubPubConfig {

    /**
     * 注入消息监听适配器
     */
    @Bean
    public MessageListenerAdapter getMessageListenerAdapter(Receiver receiver){
        //绑定监听事件执行的方法为Receiver对象的receiveMessage方法
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    /**
     * 注入消息监听容器
     */
    @Bean
    public RedisMessageListenerContainer getRedisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory, MessageListenerAdapter messageListenerAdapter){
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();

        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
        //绑定监听事件从哪个channel中获取消息
        redisMessageListenerContainer.addMessageListener(messageListenerAdapter, new PatternTopic("myChannel"));

        return redisMessageListenerContainer;
    }

}
