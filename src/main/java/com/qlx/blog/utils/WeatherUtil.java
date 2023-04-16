package com.qlx.blog.utils;

public class WeatherUtil {
    public static final String key = "811ff07de39a49c9a74fc7a4912c318e";
    public static final String location = "101251501"; //代表吉首
    public static final String baseUrl = "https://devapi.qweather.com/v7";
    public static final String weatherUrl = String.format("%s/weather/7d?key=%s&location=%s",baseUrl,key,location);
    public static final String airUrl = String.format("%s/air/5d?key=%s&location=%s",baseUrl,key,location);
    public static final String waringUrl = String.format("%s/warning/now?key=%s&location=%s&lang=en",baseUrl,key,location);

}
