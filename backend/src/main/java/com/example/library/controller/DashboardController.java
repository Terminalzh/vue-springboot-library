package com.example.library.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.library.commom.LoginUser;
import com.example.library.commom.Result;
import com.example.library.entity.Book;
import com.example.library.entity.LendRecord;
import com.example.library.entity.User;
import com.example.library.mapper.BookMapper;
import com.example.library.mapper.LendRecordMapper;
import com.example.library.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    @Resource
    private UserMapper userMapper;
    @Resource
    private LendRecordMapper lendRecordMapper;
    @Resource
    private BookMapper bookMapper;

    @GetMapping
    public Result<?> dashboardRecords() {
        int visitCount = LoginUser.getVisitCount();
        QueryWrapper<User> queryWrapper1 = new QueryWrapper();
        Long userCount = userMapper.selectCount(queryWrapper1);
        QueryWrapper<LendRecord> queryWrapper2 = new QueryWrapper();
        Long lendRecordCount = lendRecordMapper.selectCount(queryWrapper2);
        QueryWrapper<Book> queryWrapper3 = new QueryWrapper();
        Long bookCount = bookMapper.selectCount(queryWrapper3);
        Map<String, Object> map = new HashMap<>();//键值对形式
        map.put("visitCount", visitCount);//放置visitCount到map中
        map.put("userCount", userCount);
        map.put("lendRecordCount", lendRecordCount);
        map.put("bookCount", bookCount);
        return Result.success(map);
    }
}
