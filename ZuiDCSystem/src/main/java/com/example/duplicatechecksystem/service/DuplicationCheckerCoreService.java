package com.example.duplicatechecksystem.service;

import com.example.duplicatechecksystem.domain.vo.ResponseVo;

import java.util.Set;

/**
 * 查重业务接口
 */
public interface DuplicationCheckerCoreService {
    /**
     * @author 2023/9/13 杨易达 Bryan
     * 对两个字符串进行查询
     * @param str1 字符串1
     * @param str2 字符串2
     * @param set  白名单
     * @return
     */
    public ResponseVo check(String str1, String str2, Set<String> set);
}
