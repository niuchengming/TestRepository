package com.imooc.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by niuchengming
 *
 * @ Date: 2019/6/13 20:10
 */
@Entity
//自动更新时间的注解
@DynamicUpdate
//自动生成get,set方法的注解
@Data
public class ProductCategory {
    /** 类目Id **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    /** 类目名称 **/
    private String categoryName;
    /** 类目编号 **/
    private Integer categoryType;

    public ProductCategory(){

    }

}
