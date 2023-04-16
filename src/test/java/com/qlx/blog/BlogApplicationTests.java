package com.qlx.blog;

import com.qlx.blog.mapper.NoteMapper;
import com.qlx.blog.service.NoteServiceImpl;
import com.qlx.blog.utils.JsonUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {
    @Autowired
    NoteMapper noteDao;
    @Autowired
    NoteServiceImpl noteService;
    @Test
    void contextLoads() throws Exception {
    }
}
