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
package com.ptu.tinynews.app;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.ptu.tinynews.R;
import com.ptu.tinynews.model.http.WangyiApis;

import java.io.File;

/**
 * @author 咖枯
 * @version 1.0 2016/5/31
 */
public class Constant
{
    //默认的JSON格式的主题数据
    public final static String DefaultThemesJson = "{\"limit\":1000,\"subscribed\":[],\"others\":[{\"color\":15007,\"thumbnail\":\"http:\\/\\/pic3.zhimg.com\\/0e71e90fd6be47630399d63c58beebfc.jpg\",\"description\":\"了解自己和别人，了解彼此的" +
            "欲望和局限。\",\"id\":13,\"name\":\"日常心理学\"},{\"color\":8307764,\"thumbnail\":\"http:\\/\\/pic4.zhimg.com\\/2c38a96e84b5cc8331a901920a87ea71.jpg\",\"description\":\"内容由知乎用户推荐，海纳主题百万，趣味上天入地\",\"id\":12,\"name\":\"用" +
            "户推荐日报\"},{\"color\":14483535,\"thumbnail\":\"http:\\/\\/pic3.zhimg.com\\/00eba01080138a5ac861d581a64ff9bd.jpg\",\"description\":\"除了经典和新片，我们还关注技术和产业\",\"id\":3,\"name\":\"电影日报\"},{\"color\":8307764,\"thumbnail\":\"ht" +
            "tp:\\/\\/pic4.zhimg.com\\/4aa8400ba46d3d46e34a9836744ea232.jpg\",\"description\":\"为你发现最有趣的新鲜事，建议在 WiFi 下查看\",\"id\":11,\"name\":\"不许无聊\"},{\"color\":62140,\"thumbnail\":\"http:\\/\\/p1.zhimg.com\\/d3\\/7b\\/d37b38d5c82b4345c" +
            "cd7e50c4ae997da.jpg\",\"description\":\"好设计需要打磨和研习，我们分享灵感和路径\",\"id\":4,\"name\":\"设计日报\"},{\"color\":1615359,\"thumbnail\":\"http:\\/\\/pic4.zhimg.com\\/aa94e197491fb9c44d384c4747773810.jpg\",\"description\":\"商业世界变化越来越快，就是这" +
            "些家伙干的\",\"id\":5,\"name\":\"大公司日报\"},{\"color\":16031744,\"thumbnail\":\"http:\\/\\/pic2.zhimg.com\\/f2e97ff073e5cf9e79c7ed498727ebd6.jpg\",\"description\":\"从业者推荐的财经金融资讯\",\"id\":6,\"name\":\"财经日报\"},{\"color\":9699556,\"thumbnail\":\"http:\\/\\/pic2." +
            "zhimg.com\\/98d7b4f8169c596efb6ee8487a30c8ee.jpg\",\"description\":\"把黑客知识科普到你的面前\",\"id\":10,\"name\":\"互联网安全\"},{\"color\":59647,\"thumbnail\":\"http:\\/\\/pic3.zhimg.com\\/2f214a4ca51855670668530f7d333fd8.jpg\",\"description\":\"如果你喜欢游戏，就从这里开始\",\"id\":2,\"na" +
            "me\":\"开始游戏\"},{\"color\":1564695,\"thumbnail\":\"http:\\/\\/pic4.zhimg.com\\/eac535117ed895983bd2721f35d6e8dc.jpg\",\"description\":\"有音乐就很好\",\"id\":7,\"name\":\"音乐日报\"},{\"color\":6123007,\"thumbnail\":\"http:\\/\\/pic1.zhimg.com\\/a0f97c523c64e749c700b2ddc96cfd7c.jpg\",\"" +
            "description\":\"用技术的眼睛仔细看懂每一部动画和漫画\",\"id\":9,\"name\":\"动漫日报\"},{\"color\":16046124,\"thumbnail\":\"http:\\/\\/pic1.zhimg.com\\/bcf7d594f126e5ceb22691be32b2650a.jpg\",\"description\":\"关注体育，不吵架。\",\"id\":8,\"name\":\"体育日报\"}]}";

    public final static String ArticleContentUrl = "http://news-at.zhihu.com/api/4/news/";

    public final static String LatestArticleUrl = "http://news-at.zhihu.com/api/4/news/latest";

    public final static String ArticleThemesUrl = "http://news-at.zhihu.com/api/4/themes";

    public final static String BeforeArticleUrl = "http://news.at.zhihu.com/api/4/news/before/";

    public final static String ThemeContentUrl = "http://news-at.zhihu.com/api/4/theme/";
    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";
    public static ImageLoader getImageLoader() {
        return ImageLoader.getInstance();
    }

    public static DisplayImageOptions getDisplayImageOptions() {
        return new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.header) //设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.header)//设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.header)  //设置图片加载/解码过程中错误时候显示的图片
                .displayer(new FadeInBitmapDisplayer(0))//是否图片加载好后渐入的动画时间
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
    }

    public static final int TYPE_WANGYI = 101;

    public static final int TYPE_ZHIHU = 102;

    public static final int TYPE_IOS = 103;

    public static final int TYPE_WEB = 104;

    public static final int TYPE_GIRL = 105;

    public static final int TYPE_WECHAT = 106;

    public static final int TYPE_GANK = 107;

    public static final int TYPE_SETTING = 108;

    public static final int TYPE_LIKE = 109;

    public static final int TYPE_ABOUT = 110;
    public static final String DB_NAME = "NewsChannelTable";
    public static final String SHARES_COLOURFUL_NEWS = "shares_colourful_news";
    public static final String NIGHT_THEME_MODE = "night_theme_mode";
    public static final String INIT_DB = "init_db";

    public static final String NEWS_ID = "news_id";
    public static final String NEWS_TYPE = "news_type";
    public static final String CHANNEL_POSITION = "channel_position";

    public static final String NEWS_POST_ID = "news_post_id";
    public static final String NEWS_IMG_RES = "news_img_res";

    public static final String TRANSITION_ANIMATION_NEWS_PHOTOS = "transition_animation_news_photos";
    public static final String SHOW_NEWS_PHOTO = "show_news_photo";

    public static final int NEWS_CHANNEL_MINE = 0;
    public static final int NEWS_CHANNEL_MORE = 1;

    public static final String PHOTO_DETAIL = "photo_detail";
    public static final String PHOTO_DETAIL_IMGSRC = "photo_detail_imgsrc";

    public static final String NEWS_LINK = "news_link";
    public static final String NEWS_TITLE = "news_title";

    public static String getType(String id) {

        switch (id)
        {
            case WangyiApis.HOUSE_TYPE:
                return WangyiApis.HOUSE_ID;
            default:
                return WangyiApis.OTHER_TYPE;

        }
    }
}
