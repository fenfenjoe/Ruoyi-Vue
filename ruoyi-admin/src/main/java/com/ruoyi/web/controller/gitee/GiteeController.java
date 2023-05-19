package com.ruoyi.web.controller.gitee;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.dyz.gitee.service.IGiteeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author : dongyz8
 * @Date : 2023/4/21 16:08
 **/
@RestController
@RequestMapping(("/gitee"))
public class GiteeController extends BaseController {

    @Autowired
    private IGiteeService giteeService;

    @RequestMapping("/getFileContent")
    public AjaxResult getFileContent(@RequestParam Map<String,Object> param) {
        try{
            String url = (String)param.get("url");
            JSONObject result = giteeService.getFileContent(url);
            return new AjaxResult(200,"成功",result);
        }catch (Exception e){
            return AjaxResult.error(e.getMessage());
        }
    }

    @RequestMapping("/getTreeContent")
    public AjaxResult getTreeContent(@RequestParam Map<String,Object> param) {
        try{
            JSONObject result = giteeService.getTreeContent(param);
            return new AjaxResult(200,"成功",result);
        }catch (Exception e){
            return AjaxResult.error(e.getMessage());
        }
    }
}
