package com.qlx.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qlx.blog.model.Admin;
import com.qlx.blog.service.AdminServiceImpl;
import com.qlx.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminServiceImpl adminService;
    @PostMapping("/login")
    public Result login(@RequestBody Admin admin){
        String md5DigestAsHex = DigestUtils.md5DigestAsHex(admin.getPass().getBytes());
        admin.setPass(md5DigestAsHex);
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("name",admin.getName()).eq("pass",admin.getPass());
        Admin one = adminService.getOne(wrapper);
        if (one!=null){
            return new Result("查询成功",null,200);
        }
        return new Result("查询失败",null,500);
    }
    @PostMapping("/update")
    public Result update(@RequestBody Admin admin){
        String md5DigestAsHex = DigestUtils.md5DigestAsHex(admin.getPass().getBytes());
        admin.setPass(md5DigestAsHex);
        boolean updateById = adminService.updateById(admin);
        if (updateById){
            return new Result("修改成功",null,200);
        }
        return new Result("修改失败",null,500);
    }
    @PostMapping("/register")
    public Result register(@RequestBody Admin admin){
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("name",admin.getName());
        Admin exist = adminService.getOne(wrapper);
        if (exist!=null){
            return new Result("用户名已存在",null,501);
        }
        String md5DigestAsHex = DigestUtils.md5DigestAsHex(admin.getPass().getBytes());
        admin.setPass(md5DigestAsHex);
        boolean save = adminService.save(admin);
        if (save){
            return new Result("注册成功",null,200);
        }
        return new Result("注册失败",null,500);
    }
    @PostMapping("/remove")
    public Result remove(@RequestBody Admin admin){
        boolean remove = adminService.removeById(admin.getId());
        if (remove){
            return new Result("删除成功",null,200);
        }
        return new Result("删除失败",null,500);
    }
}
