package com.imooc.sell.repository;

import com.imooc.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by niuchengming
 *
 * @ Date: 2019/6/13 20:15
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
