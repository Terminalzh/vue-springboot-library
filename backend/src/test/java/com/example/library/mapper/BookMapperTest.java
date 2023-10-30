package com.example.library.mapper;

import com.example.library.entity.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookMapperTest {
    @Resource
    public UserMapper userMapper;

    @Test
    void testGetAllBook() {
        // 选择一个登录用户
        User user = new User(
                null,
                "Terminal",
                "Terminal",
                "2656454",
                "123456789",
                "北京",
                "15090542810",
                "",
                2
        );
        int inserted = userMapper.insert(user);
        // 开始测试
        Assertions.assertEquals(inserted, 1);
    }
}
