package com.example.springboot_vue.mapper.crud;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Map;

@Repository
public interface CRUDMapper {

    ArrayList<Map<String, Object>> select(
            @Param("columns") Map<String, Object> columns,
            @Param("condition") Map<String, Object> condition,
            @Param("table") String table,
            @Param("start") int start,
            @Param("end") int end
    );

    int selectCount(@Param("columns") Map<String, Object> columns,
                    @Param("condition") Map<String, Object> condition,
                    @Param("table") String table);

    int insert(@Param("columns") Map<String, Object> map,
               @Param("table") String table);

    int update(@Param("columns") Map<String, Object> map,
               @Param("table") String table,
               @Param("condition") Map<String, Object> condition);

    int delete(@Param("table") String table,
               @Param("condition") Map<String, Object> condition);
}
