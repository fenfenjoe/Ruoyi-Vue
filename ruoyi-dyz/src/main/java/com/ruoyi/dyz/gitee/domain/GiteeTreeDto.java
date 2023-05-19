package com.ruoyi.dyz.gitee.domain;

import lombok.Data;

/**
 * @author : dongyz8
 * @Date : 2023/4/21 16:36
 **/
@Data
public class GiteeTreeDto {

    private String path;
    private String mode;
    private String type;
    private String sha;
    private String size;
    private String url;


}
