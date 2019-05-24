package com.chanchifeng.curd;

import com.chanchifeng.curd.model.User;
import com.chanchifeng.curd.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurdApplicationTests {

    /* 测试jpa */
    @Resource
    private UserService userService;

    @Test
    public void testRepository(){
        //查询所有数据
        List<User> userList =  userService.findAll();
        System.out.println("findAll() :" + userList.size());
        //通过name查询数据
        List<User> userList2 = userService.findByName("李四");
        System.out.println("findByName() :" + userList2.size());
        Assert.isTrue(userList2.get(0).getName().equals("李四"),"data error!");
        //通过name模糊查询数据
        List<User> userList3 = userService.findByNameLike("%四%");
        System.out.println("findByNameLike() :" + userList3.size());
        Assert.isTrue(userList3.get(0).getName().equals("李四"),"data error!");
        //通过id列表查询数据
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        List<User> userList4 = userService.findByIdIn(ids);
        System.out.println("findByIdIn() :" + userList4.size());
        //分页查询数据
        PageRequest pageRequest = new PageRequest(0,10);
        Page<User> userList5 =  userService.findAll(pageRequest);
        System.out.println("page findAll():" + userList5.getTotalPages() + "/" + userList5.getSize());
        //新增数据
        User ayUser = new User();
//        ayUser.setId(4);
        ayUser.setName("刘六");
        ayUser.setPassword("e10adc3949ba59abbe56e057f20f883e");
        userService.save(ayUser);
        //删除数据
        userService.delete(4);
    }

}
