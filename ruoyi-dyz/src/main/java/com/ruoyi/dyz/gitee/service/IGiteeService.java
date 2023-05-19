package com.ruoyi.dyz.gitee.service;

import com.alibaba.fastjson2.JSONObject;

import java.util.Map;

public interface IGiteeService {

    JSONObject getFileContent(String path);

    JSONObject getTreeContent(Map<String, Object> param);
}
