package com.qlx.blog.controller;

import com.qlx.blog.utils.Result;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hot")
public class HotController {
    @GetMapping("/rank")
    public Result getHot(){
        Document document = null;
        Map<String,String> BaiduHotMap = new HashMap<>();
        try {
            //获取百度热点
            document = Jsoup.connect("https://www.baidu.com/").get();
            Element element = document.selectFirst("#hotsearch-content-wrapper");
            Elements children = element.children();
            for (Element child : children) {
                Elements a = child.getElementsByTag("a");
                Element span = child.selectFirst(".title-content-title");
                String href = a.attr("href");
                String text = span.text();
                BaiduHotMap.put(text,href);
            }
            //获取微博热点
            Document weiboDoc = Jsoup.connect("https://top.baidu.com/board").get();
            System.out.println(weiboDoc);
//            Map<String, Object> map = new HashMap<>();
            return new Result("获取热点成功",BaiduHotMap,500);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
