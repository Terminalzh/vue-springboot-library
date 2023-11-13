package com.example.library.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.library.commom.Result;
import com.example.library.entity.LendRecord;
import com.example.library.mapper.LendRecordMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/LendRecord")
public class LendRecordController {
    @Resource
    LendRecordMapper LendRecordMapper;

    //删除一条记录
    @PostMapping("/deleteRecord")
    public Result<?> deleteRecord(@RequestBody LendRecord LendRecord) {
        Map<String, Object> map = new HashMap<>();
        map.put("isbn", LendRecord.getIsbn());
        map.put("borrownum", LendRecord.getBorrownum());
        LendRecordMapper.deleteByMap(map);
        return Result.success();
    }

    @PostMapping("/deleteRecords")
    public Result<?> deleteRecords(@RequestBody List<LendRecord> LendRecords) {
        for (LendRecord curRecord : LendRecords) {
            Map<String, Object> map = new HashMap<>();
            map.put("isbn", curRecord.getIsbn());
            map.put("borrownum", curRecord.getBorrownum());
            LendRecordMapper.deleteByMap(map);
        }
        return Result.success();
    }

    @PostMapping
    public Result<?> save(@RequestBody LendRecord LendRecord) {
        LendRecordMapper.insert(LendRecord);
        return Result.success();
    }

    @DeleteMapping("/{isbn}")
    public Result<?> delete(@PathVariable String isbn) {
        Map<String, Object> map = new HashMap<>();
        map.put("isbn", isbn);
        LendRecordMapper.deleteByMap(map);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody LendRecord lendRecord) {
        UpdateWrapper<LendRecord> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("isbn", lendRecord.getIsbn()).eq("reader_id", lendRecord.getReaderId());
        LendRecord lendrecord = new LendRecord();
        lendrecord.setLendTime(lendRecord.getLendTime());
        lendrecord.setReturnTime(lendRecord.getReturnTime());
        lendrecord.setStatus(lendRecord.getStatus());
        LendRecordMapper.update(lendrecord, updateWrapper);
        return Result.success();
    }

    @PutMapping("/{lendTime}")
    public Result<?> update2(@PathVariable Date lendTime, @RequestBody LendRecord lendRecord) {
        UpdateWrapper<LendRecord> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("lendTime", lendTime);
        LendRecord lendrecord = new LendRecord();
        lendrecord.setReturnTime(lendRecord.getReturnTime());
        lendrecord.setStatus(lendRecord.getStatus());
        LendRecordMapper.update(lendrecord, updateWrapper);
        return Result.success();
    }

    @PutMapping("/borrownum")
    public Result<?> update2(@RequestBody LendRecord lendRecord) {
        UpdateWrapper<LendRecord> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("isbn", lendRecord.getIsbn()).eq("reader_id", lendRecord.getReaderId()).eq("borrownum", lendRecord.getBorrownum());
        LendRecord lendrecord = new LendRecord();
        lendrecord.setReturnTime(lendRecord.getReturnTime());
        lendrecord.setStatus(lendRecord.getStatus());
        LendRecordMapper.update(lendrecord, updateWrapper);
        return Result.success();
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search1,
                              @RequestParam(defaultValue = "") String search2,
                              @RequestParam(defaultValue = "") String search3) {
        LambdaQueryWrapper<LendRecord> wrappers = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(search1)) {
            wrappers.like(LendRecord::getIsbn, search1);
        }
        if (StringUtils.isNotBlank(search2)) {
            wrappers.like(LendRecord::getBookname, search2);
        }
        if (StringUtils.isNotBlank(search3)) {
            wrappers.like(LendRecord::getReaderId, search3);
        }
        Page<LendRecord> LendRecordPage = LendRecordMapper.selectPage(new Page<>(pageNum, pageSize), wrappers);
        return Result.success(LendRecordPage);
    }

}
