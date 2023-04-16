package com.qlx.blog.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qlx.blog.mapper.NoteMapper;
import com.qlx.blog.mapper.WeatherMapper;
import com.qlx.blog.model.Note;
import com.qlx.blog.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WeatherServiceImpl extends ServiceImpl<WeatherMapper, Weather> implements WeatherService{

    @Autowired
    private WeatherMapper  weatherMapper;
    @Override
    public List<Weather> getWeatherByTime() {
        List<Weather> weatherList = weatherMapper.selectAllByUpdateTime();
        return weatherList;
    }
}
