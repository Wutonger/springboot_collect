package cn.lxfun.elasticsearch_boot.service;

import cn.lxfun.elasticsearch_boot.entity.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author luoxiang
 * @date 2020/1/19 0019 下午 15:24
 * @description
 **/

public interface GoodsService {

    void save(Goods goods);

    void delete(Goods goods);

    Iterable<Goods> findAll();

    Page<Goods> findAll(Pageable pageable);
}
