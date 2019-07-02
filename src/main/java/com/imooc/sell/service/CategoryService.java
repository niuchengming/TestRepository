package com.imooc.sell.service;

import com.imooc.sell.dataobject.ProductCategory;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * Created by niuchengming
 *
 * @ Date: 2019/6/14 11:14
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);
    List<ProductCategory> findAll();
    List<ProductCategory>findByCategoryTyprIn(List<Integer> categoryTypeList);
    ProductCategory save(ProductCategory productCategory);
}
