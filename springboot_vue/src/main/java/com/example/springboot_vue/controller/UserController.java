package com.example.springboot_vue.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.springboot_vue.service.UserService;
import com.example.springboot_vue.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userServiceImpl;

    @Autowired
    HttpServletRequest request;

//    @Autowired
//    RedisTemplate<String, Object> redisTemplate;

    // 存放验证码
    public static Map<String, String> map = new HashMap<>();

    @RequestMapping("/getCode")
    public JSONObject getCode(@RequestParam("phone") String phone) {

        String code = userServiceImpl.getCode(phone);
        map.put(phone, code);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("phoneCode", code);

        return jsonObject;
    }

    @RequestMapping("/register")
    public JSONObject register(@RequestBody Map<String, String> map) {
        JSONObject jsonObject = new JSONObject();

        String phone = map.get("phone");
        String code = UserController.map.get(phone);

        // 验证码不一致
        if (code == null || !code.equals(map.get("code"))) {
            jsonObject.put("code", -1);
            return jsonObject;
        }

        String token = userServiceImpl.register(phone, code);
        if (token == null) {
            jsonObject.put("code", -1);
            return jsonObject;
        }
        jsonObject.put("token", token);
        jsonObject.put("code", 200);

        return jsonObject;
    }

    @RequestMapping("/login")
    public JSONObject login(@RequestBody Map<String, String> map) {
        return userServiceImpl.login(map);
    }

    @RequestMapping("/getInfo")
    public JSONObject getInfo() {
        // 从请求头中获取token
        String token = request.getHeader("token");

        return userServiceImpl.getInfo(token);
    }
}
