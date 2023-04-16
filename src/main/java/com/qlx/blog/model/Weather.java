package com.qlx.blog.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@TableName("weather_table")
public class Weather {
    @TableId
    private Integer id;
    private LocalDate fxDate;
    private String sunrise;
    private String sunset;
    private String tempMax;
    private String tempMin;
    private String textDay;
    private String textNight;
    private String category; //空气质量
    private String iconDay;
    private String iconNight;
    private String waringTitle;
    private String waringLevelColor;
    private String waringIcon;
    private String waringText;
    private LocalDateTime updateTime;
}
