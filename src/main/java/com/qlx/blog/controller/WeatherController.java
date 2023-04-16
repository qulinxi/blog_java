package com.qlx.blog.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qlx.blog.model.Weather;
import com.qlx.blog.service.WeatherServiceImpl;
import com.qlx.blog.utils.JsonUtil;
import com.qlx.blog.utils.Result;
import com.qlx.blog.utils.WeatherUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class WeatherController {


    @Autowired
    private WeatherServiceImpl weatherService;

    @GetMapping("/weather")
    public Result getWeather(){
        List<Weather> weathers;
        weathers = weatherService.getWeatherByTime();
        if (weathers.size()>0){
            return new Result("已从数据库查询天气成功",weathers,200);
        }else {
            try {
                String weather = JsonUtil.loadJson(WeatherUtil.weatherUrl);
                String air = JsonUtil.loadJson(WeatherUtil.airUrl);
                String waring = JsonUtil.loadJson(WeatherUtil.waringUrl);
                JSONObject weatherObj = JSONObject.parseObject(weather);
                JSONObject airObj = JSONObject.parseObject(air);
                JSONObject waringObj = JSONObject.parseObject(waring);
                JSONArray airDaily = airObj.getJSONArray("daily");
                JSONArray weatherDaily = weatherObj.getJSONArray("daily");
                JSONArray warningList = waringObj.getJSONArray("warning");
                weathers = new ArrayList();
                for (int i = 0; i < weatherDaily.size(); i++) {
                    Weather w = new Weather();
                    String updateTime = weatherObj.getString("updateTime");
                    String sunrise = weatherDaily.getJSONObject(i).getString("sunrise");
                    String sunset = weatherDaily.getJSONObject(i).getString("sunset");
                    String tempMax = weatherDaily.getJSONObject(i).getString("tempMax");
                    String tempMin = weatherDaily.getJSONObject(i).getString("tempMin");
                    String textDay = weatherDaily.getJSONObject(i).getString("textDay");
                    String textNight = weatherDaily.getJSONObject(i).getString("textNight");
                    String fxDate = weatherDaily.getJSONObject(i).getString("fxDate");
                    String iconNight = weatherDaily.getJSONObject(i).getString("iconNight");
                    String iconDay = weatherDaily.getJSONObject(i).getString("iconDay");
                    w.setFxDate(LocalDate.parse(fxDate));
                    w.setSunrise(sunrise);
                    w.setSunset(sunset);
                    w.setTempMax(tempMax);
                    w.setTempMin(tempMin);
                    w.setTextDay(textDay);
                    w.setTextNight(textNight);
                    w.setIconDay(iconDay);
                    w.setIconNight(iconNight);
                    w.setUpdateTime(LocalDateTime.parse(updateTime.split("\\+")[0]));
                    weathers.add(w);
                }
                for (int i = 0; i < airDaily.size(); i++) {
                    for (int j = 0; j < weathers.size(); j++) {
                        Date fxDate = airDaily.getJSONObject(i).getDate("fxDate");
                        LocalDate weatherLocalDate = weathers.get(j).getFxDate();
                        LocalDate airlocalDate = fxDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        if (weatherLocalDate.compareTo(airlocalDate)==0){
                            String category = airDaily.getJSONObject(i).getString("category");
                            weathers.get(j).setCategory(category);
                        }
                    }
                }
                for (int i = 0; i < warningList.size(); i++) {
                    for (int j = 0; j < weathers.size(); j++) {
                        JSONObject jsonObject = warningList.getJSONObject(i);
                        if (LocalDate.parse(jsonObject.getString("startTime").split("T")[0]).compareTo(weathers.get(j).getFxDate())==0){
                            weathers.get(j).setWaringIcon(jsonObject.getString("type"));
                            weathers.get(j).setWaringText(jsonObject.getString("text"));
                            weathers.get(j).setWaringTitle(jsonObject.getString("title"));
                            weathers.get(j).setWaringLevelColor(jsonObject.getString("level"));
                        }
                    }
                }
                boolean saveBatch = weatherService.saveBatch(weathers);
                if (saveBatch){
                    return new Result("已从和风天气接口查询天气成功",weathers,200);
                }
                return new Result("查询天气失败",null,200);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }
}
