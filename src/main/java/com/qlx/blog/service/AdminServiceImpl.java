package com.qlx.blog.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qlx.blog.mapper.AdminMapper;
import com.qlx.blog.model.Admin;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
}
