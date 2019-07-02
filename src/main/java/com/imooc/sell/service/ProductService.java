package com.imooc.sell.service;

import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品
 * Created by niuchengming
 *
 * @ Date: 2019/6/14 15:24
 */
public interface ProductService {

    ProductInfo findOne(String productId);

    /**
     * 查询所有在架商品列表
     * @return
     * */
    List<ProductInfo> findUpAll();

    /**分页显示*/
    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    /**加库存*/
    void increaseStock(List<CartDTO> cartDTOList);

    /**减库存*/
    void decreaseStock(List<CartDTO> cartDTOList);
}
