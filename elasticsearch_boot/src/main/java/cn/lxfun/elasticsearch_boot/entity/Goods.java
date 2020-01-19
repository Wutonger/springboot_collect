package cn.lxfun.elasticsearch_boot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;

/**
 * @author luoxiang
 * @date 2020/1/19 0019 下午 15:06
 * @description
 **/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "goods",type = "goodsinfo")
public class Goods {

    @Id
    private Integer id;

    @Field(store=true,type = FieldType.Text,analyzer="ik_smart",searchAnalyzer="ik_smart")
    private String name;


    @Field(store = true,type = FieldType.Double)
    private BigDecimal price;

    @Field(analyzer="ik_smart",store=true,searchAnalyzer="ik_smart",type = FieldType.Text)
    private String description;


}
