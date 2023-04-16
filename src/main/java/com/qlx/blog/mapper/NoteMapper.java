package com.qlx.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qlx.blog.model.Note;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

@Mapper
public interface NoteMapper extends BaseMapper<Note> {
}
