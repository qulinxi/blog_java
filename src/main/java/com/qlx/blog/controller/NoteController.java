package com.qlx.blog.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qlx.blog.model.Note;
import com.qlx.blog.service.NoteServiceImpl;
import com.qlx.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@ResponseBody
@RequestMapping("/note")
public class NoteController {
    @Autowired
    private NoteServiceImpl noteService;
    @PostMapping("/save")
    public Result add(@RequestBody Note note){
        boolean save = noteService.save(note);
        if(save){
            return new Result("保存成功",null,200);
        }
        return new Result("保存失败",null,500);
    }
    @PostMapping("/remove/{i}")
    public Result delete(@PathVariable("i") Integer i){
        boolean remove = noteService.removeById(i);
        if(remove){
            return new Result("删除成功",null,200);
        }
        return new Result("删除失败",null,500);
    }
    @PostMapping("/update")
    public Result update(@RequestBody Note note){
        boolean update = noteService.updateById(note);
        if(update){
            return new Result("更新成功",null,200);
        }
        return new Result("更新失败",null,500);
    }
    @GetMapping("/get/{i}")
    public Result getById(@PathVariable("i") Integer i){
        Note note = noteService.getById(i);
        if (note!=null){
            return new Result("查询成功",note,200);
        }
        return new Result("查询失败",null,500);
    }
    @GetMapping("/page/{pageNum}/{pageSize}")
    public Result page(@PathVariable("pageNum") Integer pageNum,@PathVariable("pageSize") Integer pageSize){
        Page<Note> page = new Page<>(pageNum,pageSize);
        Page<Note> note = noteService.page(page);
        List<Note> notes = note.getRecords();
        long total = note.getTotal();
        Map<String,Object> map = new HashMap<>();
        map.put("noteList",notes);
        map.put("total",total);
        if (notes.size()>0){
            return new Result("查询成功",map,200);
        }
        return new Result("查询失败",null,500);
    }
    @GetMapping("/search/{s}")
    public Result search(@PathVariable("s") String s){
        QueryWrapper<Note> wrapper = new QueryWrapper<>();
        wrapper.like("title",s).or().like("text",s);
        List<Note> notes = noteService.list(wrapper);
        if (notes.size()>0){
            return new Result("查询成功",notes,200);
        }
        return new Result("查询失败",notes,500);
    }
}
