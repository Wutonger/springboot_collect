package cn.lxfun.redis_boot.Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author luoxiang
 * @date 2020/4/14 0014 上午 11:46
 * @description
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisLockTest {
    @Autowired
    private RedisLock redisLock;

    @Test
    public void test01(){
        redisLock.subInventory();
    }

}
