package cn.lxfun.elasticsearch_boot.controller;

import cn.lxfun.elasticsearch_boot.entity.Goods;
import cn.lxfun.elasticsearch_boot.service.GoodsService;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author luoxiang
 * @date 2020/1/19 0019 下午 15:31
 * @description
 **/
@RestController
public class goodsController {

    @Autowired
    GoodsService goodsService;

    //springboot自带操作es的template
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 创建index,设置mapping
     * @return
     */
    @PostMapping("/createIndex")
    public String createIndex(){
        try{
            elasticsearchTemplate.createIndex(Goods.class);
            elasticsearchTemplate.putMapping(Goods.class);
        }catch (Exception e){
            return "create index and set mapping failure!";
        }
        return "successful create!";
    }


    @PostMapping("/putGoods")
    public String putgoods(Integer id,String name,String description){
        Goods goods = new Goods(id,name,new BigDecimal("100.00"),description);
        try {
            goodsService.save(goods);
        }catch (Exception e){
            return "add goods into es failure!";
        }
        return "successful add goods!";
    }

    /**
     * 正式开发一般不会用这个方法，因为es中的数据量很庞大
     * @return
     */
    @GetMapping("/getAllGoods")
    public Iterable<Goods> getAllGoods(){
        Iterable<Goods> all = goodsService.findAll();
        return all;
    }

    /**
     * 采用分页查询
     * PageRequest.of()方法参数   arg1:从多少页开始开始查    arg2: 每页几条数据
     * @return
     */
    @GetMapping("/getGoodsByPage")
    public List<Goods> getGoodsByPage(){
        Pageable pageable = PageRequest.of(0,5);
        return goodsService.findAll(pageable).getContent();
    }

    /**
     * 采用ES原生进行查询，可使用querystring、term等ES中特有查询方式
     * @return
     */
    @GetMapping("/searchGoods")
    public List<Goods> searchGoods(){
        //构造查询条件
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.queryStringQuery("华为吃苹果").defaultField("name"))
                .withPageable(PageRequest.of(0,5))
                .build();

        //使用查询条件进行查询
        return elasticsearchTemplate.queryForList(searchQuery,Goods.class);
    }

}
