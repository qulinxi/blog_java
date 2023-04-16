package com.qlx.blog.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;

public class JsonUtil {
    public static String loadJson(String url) throws Exception {
        //读取url,返回json串
        StringBuilder json = new StringBuilder();
        URL oracle = new URL(url);
        URLConnection yc = oracle.openConnection();
        InputStream is;
        if(yc.getHeaderField("content-encoding")!=null&&yc.getHeaderField("content-encoding").equals("gzip")){
            is = new GZIPInputStream(yc.getInputStream());
        }else {
            is = yc.getInputStream();
        }
        BufferedReader  reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String inputLine = null;
        while((inputLine = reader.readLine()) != null){
            json.append(inputLine);
        }
        reader.close();

        return json.toString();
    }
}
