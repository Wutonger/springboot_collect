package cn.lxfun.redis_boot.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * @author luoxiang
 * @date 2020/4/14 0014 上午 10:12
 * @description 模拟Redis实现分布式锁  主要原理  set nx px命令
 **/
@Service
public class RedisLock {

    //redis key
    private static final String KEY = "redis_lock";

    //商品库存
    private static int inventory = 100;

    private static LinkedBlockingQueue<Runnable> linkedBlockingQueue = new LinkedBlockingQueue<>();

    @Autowired
    private StringRedisTemplate redisTemplate;


    //减库存
    public void subInventory() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(inventory, inventory,
                10L, TimeUnit.SECONDS, linkedBlockingQueue);

        //循环添加任务扣库存
        Stream.iterate(1, item -> item + 1).limit(100).forEach(
                item -> threadPoolExecutor.execute(() -> {
                    //value为uuid，防止其它任务解锁当前任务的锁
                    String uuid = UUID.randomUUID().toString();
                    lock(uuid);
                    inventory--;
                    System.out.println(inventory);
                    unlock(uuid);
                })
        );
    }


    //加锁
    private boolean lock(String uuid) {
        //自旋操作
        for (; ; ) {
            //必须设置过期时间防止执行过程中出现异常，发生死锁
            boolean success = redisTemplate.opsForValue().setIfAbsent(KEY, uuid, 10, TimeUnit.SECONDS);
            if (success) {
                return true;
            }
            //100ms后再次获取，防止频繁获取锁
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //释放锁,必须保证其原子性,故采用lua脚本操作
    private boolean unlock(String uuid) {
        String lua = "if redis.call('get', KEYS[1]) == ARGV[1] then " +
                "return redis.call('del', KEYS[1]) else return 0 end";
        RedisScript<Long> script = new DefaultRedisScript<>(lua, Long.class);
        Long result = redisTemplate.execute(script, Collections.singletonList(KEY), uuid);
        return 1L == result;

    }

}
