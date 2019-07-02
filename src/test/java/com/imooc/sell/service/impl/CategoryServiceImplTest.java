package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.ProductCategory;
import com.imooc.sell.service.CategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by niuchengming
 *
 * @ Date: 2019/6/14 11:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    public void findOne() throws Exception{
        ProductCategory productCategory = categoryService.findOne(1);
        Assert.assertEquals(new Integer(1),productCategory.getCategoryId());
    }

    @Test
    public void findAll() {
        List<ProductCategory> productCategories =categoryService.findAll();
        Assert.assertNotEquals(0,productCategories.size());
    }

    @Test
    public void findByCategoryTyprIn() {
        List<ProductCategory> productCategories = categoryService.findByCategoryTyprIn(Arrays.asList(1,2,3,4));
        Assert.assertNotEquals(0,productCategories.size());
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("男生最爱");
        productCategory.setCategoryType(10);
        ProductCategory result =categoryService.save(productCategory);
        Assert.assertNotNull(result);

    }
}