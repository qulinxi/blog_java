package com.qlx.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qlx.blog.model.Note;
import com.qlx.blog.model.Weather;
import org.springframework.stereotype.Service;

import java.util.List;

public interface NoteService extends IService<Note> {
    List<Weather> getWeatherByTime ();
}
