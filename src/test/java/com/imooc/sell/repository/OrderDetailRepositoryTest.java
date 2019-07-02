package com.imooc.sell.repository;

import com.imooc.sell.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by niuchengming
 *
 * @ Date: 2019/6/19 14:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("123456712");
        orderDetail.setOrderId("11111111");
        orderDetail.setProductIcon("http://XXXX.jpg");
        orderDetail.setProductId("11111122");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductPrice(new BigDecimal(1.2));
        orderDetail.setProductQuantity(3);

        OrderDetail result = repository.save(orderDetail);
        Assert.assertNotNull(result);

    }

    @Test
    public void findByOrderId() throws Exception{

        List<OrderDetail> orderDetailList = repository.findByOrderId("11111111");
        Assert.assertNotEquals(0,orderDetailList.size());
    }

}