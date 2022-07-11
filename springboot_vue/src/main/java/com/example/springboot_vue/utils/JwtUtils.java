package com.example.springboot_vue.utils;

import com.example.springboot_vue.pojo.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtils {

    public static final String SUBJECT = "test";
    // 密钥
    public static final String SECRET = "test";
    // 过期时间设置为30分钟
    public static final long EXPIRE = 1000 * 60 * 30;

    public static String getToken(User user) {
        if (user == null) {
            return null;
        }

        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("id", user.getId())
                .claim("nickname", user.getNickname())
                .claim("phone", user.getPhone())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                // 设置签名方式以及密钥
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();

        return token;
    }

    // 由返回的结果可以拿到token中的数据
    public static Claims checkToken(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }

}
