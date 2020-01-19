package cn.lxfun.elasticsearch_boot.dao;

import cn.lxfun.elasticsearch_boot.entity.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author luoxiang
 * @date 2020/1/19 0019 下午 15:22
 * @description
 **/
@Repository
public interface GoodsDao extends ElasticsearchRepository<Goods,Integer> {
}
