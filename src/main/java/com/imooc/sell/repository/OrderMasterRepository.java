package com.imooc.sell.repository;

import com.imooc.sell.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.soap.SAAJResult;

/**
 * Created by niuchengming
 *
 * @ Date: 2019/6/19 10:08
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    /**按照买家的openId查询，且分页*/
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
