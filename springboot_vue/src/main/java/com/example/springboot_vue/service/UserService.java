package com.example.springboot_vue.service;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface UserService {

    String getCode(String phone);

    String register(String phone, String password);

    JSONObject login(Map<String, String> map);

    JSONObject getInfo(String token);
}
