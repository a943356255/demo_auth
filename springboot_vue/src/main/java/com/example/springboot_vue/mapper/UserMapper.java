package com.example.springboot_vue.mapper;

import com.example.springboot_vue.pojo.user.RouteAndRole;
import com.example.springboot_vue.pojo.user.User;
import com.example.springboot_vue.pojo.user.UserAndRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UserMapper {

    int register(User user);

    User selUser(@Param("phone") String phone);

    UserAndRole getRole(int id);

    ArrayList<RouteAndRole> getInfo(int id);
}
