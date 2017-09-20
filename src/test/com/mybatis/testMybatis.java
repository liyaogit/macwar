package com.mybatis;


import com.mybatis.model.User;
import com.mybatis.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

/**
 * User: yli
 * Date: 2017/6/26
 * Time: 17:28
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class testMybatis{
    @Autowired
    private IUserService userService;
    @Test
    public void testUsers(){
        User user= userService.findUsers(Long.valueOf(1));
        System.out.println("\n"+user.getAddress());
        List<Object> mapList = userService.findAllUsers();
        Map map = (Map) mapList.get(0);
        System.out.println("\n"+map.get("address"));
    }
}
