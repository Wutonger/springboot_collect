package cn.lxfun.redis_boot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author luoxiang
 * @date 2020/1/20 0020 下午 14:04
 * @description
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private Long id;

    private String username;

    private String password;

    private String email;

    private Date birthday;
}
