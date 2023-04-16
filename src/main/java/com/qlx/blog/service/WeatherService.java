package com.qlx.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qlx.blog.model.Weather;

import java.time.LocalDateTime;
import java.util.List;

public interface WeatherService extends IService<Weather> {
    List<Weather> getWeatherByTime();
}
