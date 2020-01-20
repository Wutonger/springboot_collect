package cn.lxfun.redis_boot.Service;

import cn.lxfun.redis_boot.entity.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author luoxiang
 * @date 2020/1/20 0020 下午 14:10
 * @description
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public User getUser(String key) {
        //判断key是否存在
        if (redisTemplate.hasKey(key)){
            String json = redisTemplate.opsForValue().get(key).toString();
            User user = JSON.parseObject(json,User.class);
            return user;
        }
        return null;
    }

    @Override
    public void listPushUser(String key,Long id,String username) {
        User user = new User(id,username,"zhangsan123456","zhangsan@qq.com",new Date());
        //设置key过期时间
        redisTemplate.expire(key,60*5,TimeUnit.SECONDS);
        //向list左边添加元素
        redisTemplate.opsForList().leftPush(key,JSON.toJSONString(user));

    }

    @Override
    public User listPopUser(String key) {
        if (redisTemplate.hasKey(key)){
            //获得list长度
            System.out.println("输出列表的长度"+redisTemplate.opsForList().size(key));
            //根据坐标获取list中元素，index为-1时，表示返回的是最后一个；当index大于实际的列表长度时，返回null
            redisTemplate.opsForList().index(key,-1);
            //返回一个list，第二个参数为坐标start,第三个参数为坐标end
            redisTemplate.opsForList().range(key, -2, -1);
            //从list右边获取元素
            String json = redisTemplate.opsForList().rightPop(key).toString();
            User user = JSON.parseObject(json,User.class);
            return user;
        }
        return null;
    }

    @Override
    public void setUser(Long id) {
        User user = new User(id,"张三","zhangsan123456","zhangsan@qq.com",new Date());
        //redis存储对象需要先将对象序列化 or 转换为 json字符串 两种方式各有优点
        String json = JSONObject.toJSONString(user);
        //set()方法有三种重载   可带过期时间
        redisTemplate.opsForValue().set(user.getUsername(),json,10,TimeUnit.SECONDS);
    }
}
