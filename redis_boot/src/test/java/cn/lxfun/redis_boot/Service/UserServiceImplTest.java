package cn.lxfun.redis_boot.Service;

import cn.lxfun.redis_boot.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 * @author luoxiang
 * @date 2020/1/20 0020 下午 14:21
 * @description
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void getUser() {
        User user = userService.getUser("张三");
        System.out.println(user);
    }

    @Test
    public void listPushUser() {
        userService.listPushUser("用户排名列表",2L,"jack ma");
        userService.listPushUser("用户排名列表",3L,"tom zhang");
        userService.listPushUser("用户排名列表",4L,"rose cai");
    }

    @Test
    public void listPopUser(){
        User user = userService.listPopUser("用户排名列表");
        System.out.println(user);
    }

    @Test
    public void setUser() {
        userService.setUser(1L);
    }
}
