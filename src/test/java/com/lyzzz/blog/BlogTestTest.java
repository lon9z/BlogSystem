package com.lyzzz.blog;

import com.lyzzz.common.utils.Md5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * @location： BlogSystem\com.lyzzz.blog
 * @creatTime: 2020/7/17  21:09
 * @author: Administrator
 * @remark:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BlogTestTest {
    @Autowired
    private Md5Util md5Util;

    @Test
    public void test1() {
        String tycoding = md5Util.encryptPassword("tycoding");
        System.out.println(tycoding);
    }
}