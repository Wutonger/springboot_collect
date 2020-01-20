package cn.lxfun.redis_boot.Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author luoxiang
 * @date 2020/1/20 0020 下午 16:35
 * @description  Redis利用sub/pub模式，设置消息自动消费
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PubSubServiceTest {

    @Autowired
    private PubSubService pubSubService;

    @Test
    public void sendMessage() {
        try {
            pubSubService.sendMessage("hello redis");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
