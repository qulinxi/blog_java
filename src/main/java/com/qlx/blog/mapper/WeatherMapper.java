package com.qlx.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qlx.blog.model.Weather;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface WeatherMapper extends BaseMapper<Weather> {
    List<Weather> selectAllByUpdateTime();
}
