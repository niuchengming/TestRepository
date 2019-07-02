package com.imooc.sell.repository;

import com.imooc.sell.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by niuchengming
 *
 * @ Date: 2019/6/14 14:41
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {

    List<ProductInfo> findByProductStatus(Integer productStatus);
}
