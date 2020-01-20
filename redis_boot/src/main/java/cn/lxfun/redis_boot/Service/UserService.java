package cn.lxfun.redis_boot.Service;

import cn.lxfun.redis_boot.entity.User;

/**
 * @author luoxiang
 * @date 2020/1/20 0020 下午 14:06
 * @description
 **/
public interface UserService {

    User getUser(String key);

    void   listPushUser(String key,Long id,String username);

    User   listPopUser(String key);

    void setUser(Long id);
}
