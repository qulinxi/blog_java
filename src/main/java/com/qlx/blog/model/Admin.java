package com.qlx.blog.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("admin_table")
public class Admin {
    @TableId
    private Integer id;
    private String name;
    private String pass;
    private String cover;
    private String information;
}
