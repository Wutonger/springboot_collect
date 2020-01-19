package cn.lxfun.elasticsearch_boot.service;

import cn.lxfun.elasticsearch_boot.dao.GoodsDao;
import cn.lxfun.elasticsearch_boot.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author luoxiang
 * @date 2020/1/19 0019 下午 15:25
 * @description
 **/

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;


    @Override
    public void save(Goods goods) {
        goodsDao.save(goods);
    }

    @Override
    public void delete(Goods goods) {
        goodsDao.delete(goods);
    }

    @Override
    public Iterable<Goods> findAll() {
        Iterable<Goods> iterable = goodsDao.findAll();
        return iterable;
    }

    @Override
    public Page<Goods> findAll(Pageable pageable) {
        return goodsDao.findAll(pageable);
    }
}
