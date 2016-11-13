/*
 * Copyright (c) 2016 咖枯 <kaku201313@163.com | 3772304@qq.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.ptu.tinynews.model.http;


import com.ptu.tinynews.model.bean.NewsDetail;
import com.ptu.tinynews.model.bean.NewsSummary;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Administrator on 2016/9/10.
 */
public interface WangyiApis
{

    String NETEAST_HOST = "http://c.m.163.com/";

    @GET("nc/article/{type}/{id}/{startPage}-20.html")
    Observable<Map<String,List<NewsSummary>>> getNewsList(
          //  @Header("Cache-Control") String cacheControl,
            @Path("type") String type, @Path("id") String id,
            @Path("startPage") int startPage);

    @GET("nc/article/{postId}/full.html")
    Observable<Map<String,NewsDetail>> getNewDetail(
     //       @Header("Cache-Control") String cacheControl,
            @Path("postId") String postId);

    @GET
    Observable<ResponseBody> getNewsBodyHtmlPhoto(
            @Url String photoPath);

    // 房产TYPE
    String HOUSE_TYPE = "house";
    // 其他TYPE
    String OTHER_TYPE = "list";

    // 头条id
    String HEADLINE_ID = "T1348647909107";
    // 房产id
    String HOUSE_ID = "5YyX5Lqs";
    // 足球
    String FOOTBALL_ID = "T1399700447917";
    // 娱乐
    String ENTERTAINMENT_ID = "T1348648517839";
    // 体育
    String SPORTS_ID = "T1348649079062";
    // 财经
    String FINANCE_ID = "T1348648756099";
    // 科技
    String TECH_ID = "T1348649580692";
    // 电影
    String MOVIE_ID = "T1348648650048";
    // 汽车
    String CAR_ID = "T1348654060988";
    // 笑话
    String JOKE_ID = "T1350383429665";
    // 游戏
    String GAME_ID = "T1348654151579";
    // 时尚
    String FASHION_ID = "T1348650593803";
    // 情感
    String EMOTION_ID = "T1348650839000";
    // 精选
    String CHOICE_ID = "T1370583240249";
    // 电台
    String RADIO_ID = "T1379038288239";
    // nba
    String NBA_ID = "T1348649145984";
    // 数码
    String DIGITAL_ID = "T1348649776727";
    // 移动
    String MOBILE_ID = "T1351233117091";
    // 彩票
    String LOTTERY_ID = "T1356600029035";
    // 教育
    String EDUCATION_ID = "T1348654225495";
    // 论坛
    String FORUM_ID = "T1349837670307";
    // 旅游
    String TOUR_ID = "T1348654204705";
    // 手机
    String PHONE_ID = "T1348649654285";
    // 博客
    String BLOG_ID = "T1349837698345";
    // 社会
    String SOCIETY_ID = "T1348648037603";
    // 家居
    String FURNISHING_ID = "T1348654105308";
    // 暴雪游戏
    String BLIZZARD_ID = "T1397016069906";
    // 亲子
    String PATERNITY_ID = "T1397116135282";
    // CBA
    String CBA_ID = "T1348649475931";
    // 消息
    String MSG_ID = "T1371543208049";
    // 军事
    String MILITARY_ID = "T1348648141035";



    //@Url，它允许我们直接传入一个请求的URL。这样以来我们可以将上一个请求的获得的url直接传入进来，baseUrl将被无视
    // baseUrl 需要符合标准，为空、""、或不合法将会报错


}
