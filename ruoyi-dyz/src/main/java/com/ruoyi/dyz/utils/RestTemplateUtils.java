package com.ruoyi.dyz.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Collections;

/**
 * @author : dongyz8
 * @Date : 2023/4/24 11:05
 **/
@Component
public class RestTemplateUtils {

    @Autowired
    private RestTemplate restTemplate;

    public String post(String url,String jsonParam){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAcceptCharset(Collections.singletonList(Charset.forName("utf-8")));
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(jsonParam,headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST,entity,String.class);
        if(responseEntity == null){
            return null;
        }
        return responseEntity.getBody();
    }

    public String get(String url){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAcceptCharset(Collections.singletonList(Charset.forName("utf-8")));
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET,entity,String.class);
        if(responseEntity == null){
            return null;
        }
        return responseEntity.getBody();
    }
}
