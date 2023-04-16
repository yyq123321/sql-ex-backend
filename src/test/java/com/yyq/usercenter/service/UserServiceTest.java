package com.yyq.usercenter.service;

import com.yyq.usercenter.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void testInsert() {
        User user = new User();
        user.setUsername("hanyi");
        user.setUserAccount("zhy123");
        user.setGender(0);
        user.setUserPassword("123");
        user.setAvatarUrl("");
        user.setPhone("156");
        user.setEmail("hanyi@123");
        user.setUserStatus(0);
        user.setIsDelete(0);


        boolean result = userService.save(user);
        assertTrue(result);
        System.out.println(user);
        System.out.println(result);
    }

    @Test
    void userRegister() {
        String userAccount = "hanyi";
        String userPassword = "123456";
        String checkPassword = "123456";
        String planetCode = "01";
        long result = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        System.out.println(result);
        Assertions.assertEquals(-1, result); //失败的断言

    }
}