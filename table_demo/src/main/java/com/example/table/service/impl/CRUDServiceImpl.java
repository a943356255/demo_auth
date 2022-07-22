package com.example.springboot_vue.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.springboot_vue.mapper.crud.CRUDMapper;
import com.example.springboot_vue.service.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class CRUDServiceImpl implements CRUDService {

    @Autowired
    CRUDMapper crudMapper;

    @Override
    public JSONObject getCrudValue(Map<String, Object> map) {

        JSONObject jsonObject = new JSONObject();
        // 获取要操作的表
        String table = (String) map.get("table");
        // 要执行的操作
        String operate = (String) map.get("operate");
        System.out.println("本次操作是：" + operate);

        // 需要操作的字段
        LinkedHashMap<String, Object> columns = (LinkedHashMap) map.get("column");
        System.out.println("columns = " + columns);
        // key为要限制的字段，value为具体的限制结果
        LinkedHashMap<String, Object> condition = (LinkedHashMap) map.get("condition");
        System.out.println("condition = " + condition);

        // 如果不存在字段，直接返回
        if (columns == null) {
            jsonObject.put("code", -1);
            return jsonObject;
        }

        if (operate.equals("add")) {
            // 添加数据
            crudMapper.insert(columns, table);
        } else if (operate.equals("select")) {

            int pageNo = (int) map.get("pageNo");
            int pageSize = (int) map.get("pageSize");

            ArrayList<Map<String, Object>> list = crudMapper.select(columns, condition, table, (pageNo - 1) * pageSize, pageSize);
            jsonObject.put("valueList", list);

            int size = crudMapper.selectCount(columns, condition, table);
            jsonObject.put("size", size);

        } else if (operate.equals("delete")) {
            // 删除操作,如果不传条件，默认删除失败
            if (condition == null) {
                jsonObject.put("code", -1);
                return jsonObject;
            }
            crudMapper.delete(table, condition);
        } else if (operate.equals("update")) {
            // 修改操作
            crudMapper.update(columns, table, condition);
        } else {
            // 不是上述四种操作，直接返回结果
            jsonObject.put("code", -1);
            return jsonObject;
        }

        return jsonObject;
    }
}
