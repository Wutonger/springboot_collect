package cn.lxfun.redis_boot.Service;

import org.springframework.stereotype.Component;

/**
 * @author luoxiang
 * @date 2020/1/20 0020 下午 16:31
 * @description
 **/
@Component
public class Receiver {

    public void receiveMessage(String message){
        System.out.println("接收到消息："+message);
    }
}
