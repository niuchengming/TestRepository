package com.imooc.sell.repository;

import com.imooc.sell.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 订单详情表
 * Created by niuchengming
 *
 * @ Date: 2019/6/17 16:10
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String > {

    List<OrderDetail> findByOrderId(String orderId);

}
