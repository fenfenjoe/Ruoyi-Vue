package com.ruoyi.dyz.gitee.service.impl;


import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.sign.Base64;
import com.ruoyi.dyz.gitee.domain.GiteeResponse;
import com.ruoyi.dyz.gitee.service.IGiteeService;
import com.ruoyi.dyz.utils.RestTemplateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author : dongyz8
 * @Date : 2023/4/21 16:12
 **/
@Service
public class GiteeServiceImpl implements IGiteeService {

    @Value("${gitee.api.url}")
    private String gitee_url;
    @Value("${gitee.api.access_token}")
    private String gitee_access_token;
    @Value("${gitee.api.owner}")
    private String gitee_owner;
    @Value("${gitee.api.repo}")
    private String gitee_repo;

    @Autowired
    private RestTemplateUtils restTemplateUtils;


    @Override
    public JSONObject getFileContent(String url) {
        System.out.println(url);
        String resultStr  = restTemplateUtils.get(url);
        JSONObject result = null;
        if(StringUtils.isNotEmpty(resultStr)){
            //BASE64解码
            result = JSONObject.parseObject(resultStr);
        }
        return result;
    }

    @Override
    public JSONObject getTreeContent(Map<String, Object> param) {
        //根节点：传sha   子树节点：传url
        String paramUrl = (String)param.get("url");
        String sha = (String)param.get("sha");
        String url = null;
        if(StringUtils.isNotEmpty(paramUrl)){
            url = paramUrl;
        }else{
            url = gitee_url + "/repos/" + gitee_owner + "/" + gitee_repo + "/git/trees/" + sha;
        }
        //带上token
        String param1 = "access_token=" + gitee_access_token;
        url = url + "?" + param1;
        System.out.println(url);
        String result = restTemplateUtils.get(url);
        return JSONObject.parseObject(result);
    }

}
