package com.ptu.tinynews;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.google.gson.Gson;
import com.ptu.tinynews.model.bean.DailyListBean;
import com.ptu.tinynews.model.bean.NewsDetail;
import com.ptu.tinynews.model.bean.NewsSummary;
import com.ptu.tinynews.model.bean.ZhihuDetailBean;
import com.ptu.tinynews.model.http.RetrofitHelper;
import com.ptu.tinynews.model.http.WangyiApis;
import com.ptu.tinynews.util.RxUtil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Administrator on 2016/10/27.
 */
@RunWith(AndroidJUnit4.class)
public class RetrofitTest
{
    private static String  json="[{\"postid\":\"C4D6HN5L002580S6\",\"hasCover\":false,\"hasHead\":1,\"replyCount\":431,\"ltitle\":\"Note7拖累 三星移动Q3获利创八年最低\",\"hasImg\":1,\"digest\":\"据外媒报道，韩国三星电子周四表示，将努力迅速走出GalaxyNote7造成的困境。受到Note7的拖累，三星电子第三季移动业务获利创下将近八年来最低。三星第三季\",\"hasIcon\":true,\"docid\":\"C4D6HN5L002580S6\",\"title\":\"Note7拖累 三星移动Q3获利创八年最低\",\"order\":1,\"priority\":254,\"lmodify\":\"2016-10-27 16:35:17\",\"boardid\":\"money_bbs\",\"ads\":[{\"docid\":\"C4BLFF33002580SJ\",\"title\":\"造谣水贝村每户拆迁款2亿 三人遭拘留\",\"tag\":\"doc\",\"imgsrc\":\"http://cms-bucket.nosdn.127.net/a53c0bb84d524f8f86cb281ac101e0f720161027073954.jpeg\",\"subtitle\":\"\",\"url\":\"C4BLFF33002580SJ\"}],\"url_3w\":\"http://money.163.com/16/1027/15/C4D6HN5L002580S6.html\",\"template\":\"recommend\",\"votecount\":357,\"alias\":\"Business\",\"cid\":\"C1348648727071\",\"url\":\"http://3g.163.com/money/16/1027/15/C4D6HN5L002580S6.html\",\"hasAD\":1,\"source\":\"上海证券报·中国证券网\",\"ename\":\"caijing\",\"subtitle\":\"\",\"imgsrc\":\"http://cms-bucket.nosdn.127.net/61739709fe424f33989216ef66b4fde320161027163507.jpeg\",\"tname\":\"财经\",\"ptime\":\"2016-10-27 15:47:10\"},{\"url_3w\":\"http://money.163.com/16/1027/17/C4DAOCN7002581PP.html\",\"postid\":\"C4DAOCN7002581PP\",\"votecount\":12005,\"replyCount\":12684,\"ltitle\":\"在岸人民币兑美元跌破6.78关口 再创逾六年新低\",\"digest\":\"周四欧市早盘，在岸人民币兑美元跌破6.78关口，刷新逾6年新低至6.7801。离岸人民币兑美元早盘刷新历史新低至6.7924，现跌15个基点报6.7870。离岸\",\"url\":\"http://3g.163.com/money/16/1027/17/C4DAOCN7002581PP.html\",\"docid\":\"C4DAOCN7002581PP\",\"title\":\"在岸人民币兑美元跌破6.78关口 再创逾六年新低\",\"source\":\"Wind资讯\",\"priority\":200,\"lmodify\":\"2016-10-27 18:33:13\",\"imgsrc\":\"http://cms-bucket.nosdn.127.net/1f91c7768d504a7984c6091064b307fa20161027171000.jpeg\",\"subtitle\":\"\",\"boardid\":\"money_bbs\",\"ptime\":\"2016-10-27 17:08:40\"},{\"url_3w\":\"http://money.163.com/16/1027/17/C4DAIJCR002580S6.html\",\"postid\":\"C4DAIJCR002580S6\",\"votecount\":8024,\"replyCount\":8361,\"ltitle\":\"国研中心专家谈人民币贬值：我们错过了太多机会\",\"digest\":\"“如果不抓住现在的机会，很可能现在的机会都还要损失掉。”国务院发展研究中心研究员吴庆在日前召开的中国宏观经济论坛上回答《华夏时报》记者提问时称。人民币在即将过去\",\"url\":\"http://3g.163.com/money/16/1027/17/C4DAIJCR002580S6.html\",\"docid\":\"C4DAIJCR002580S6\",\"title\":\"国研中心专家谈人民币贬值：我们错过了太多机会\",\"source\":\"华夏时报\",\"priority\":180,\"lmodify\":\"2016-10-27 18:33:28\",\"imgsrc\":\"http://cms-bucket.nosdn.127.net/62778f7e4ec34dd98092e33d4e60282920161027171830.jpeg\",\"subtitle\":\"\",\"boardid\":\"money_bbs\",\"ptime\":\"2016-10-27 17:05:30\"},{\"url_3w\":\"http://money.163.com/16/1027/13/C4CV5G1Q002581PP.html\",\"postid\":\"C4CV5G1Q002581PP\",\"votecount\":2231,\"replyCount\":2722,\"ltitle\":\"2016福布斯中国富豪榜:王健林以330亿美元登顶\",\"digest\":\"福布斯中国富豪榜身家达到十亿美元富豪的人数第一次达到了真正意义上的 400 人，这尚属首次。\",\"url\":\"http://3g.163.com/money/16/1027/13/C4CV5G1Q002581PP.html\",\"docid\":\"C4CV5G1Q002581PP\",\"title\":\"2016福布斯中国富豪榜:王健林以330亿美元登顶\",\"source\":\"第一财经日报\",\"priority\":180,\"lmodify\":\"2016-10-27 17:49:55\",\"imgsrc\":\"http://dingyue.nosdn.127.net/Bl49DiLGp4XiPXyru2repW22zqDrr2qtbGVEPd=DxhI7Y1469068258086.jpg\",\"subtitle\":\"\",\"boardid\":\"money_bbs\",\"ptime\":\"2016-10-27 13:46:07\"},{\"url_3w\":\"http://money.163.com/16/1027/07/C4CB1NB0002580S6.html\",\"postid\":\"C4CB1NB0002580S6\",\"votecount\":11601,\"replyCount\":12136,\"ltitle\":\"加多宝确认停止与代工厂合作 频受裁员传闻困扰\",\"digest\":\"工厂停产、员工被裁的传闻困扰着加多宝。日前，有消息称，加多宝内部裁员蔓延，与渠道商之间的合作也出现不愉快，且停掉了部分塑料瓶装加多宝的代工生产。对于系列传言，加\",\"url\":\"http://3g.163.com/money/16/1027/07/C4CB1NB0002580S6.html\",\"docid\":\"C4CB1NB0002580S6\",\"title\":\"加多宝确认停止与代工厂合作 频受裁员传闻困扰\",\"source\":\"北京商报\",\"priority\":180,\"lmodify\":\"2016-10-27 17:50:01\",\"imgsrc\":\"http://cms-bucket.nosdn.127.net/b1af643f6d4149f399db1dc4ccc4a77620161027111337.jpeg\",\"subtitle\":\"\",\"boardid\":\"money_bbs\",\"ptime\":\"2016-10-27 07:47:00\"},{\"url_3w\":\"http://money.163.com/16/1026/21/C4B8OL7P002580T4.html\",\"postid\":\"C4B8OL7P002580T4\",\"votecount\":41057,\"replyCount\":42589,\"ltitle\":\"200亿出售陆家嘴世纪汇！李嘉诚又卖地了\",\"digest\":\"李嘉诚终于把长实地产在陆家嘴最后的自留商用物业卖掉了。今日晚间，由李嘉诚掌控的长实地产发布公告称，将与李嘉诚海外基金会就出售二者共同持有的上海陆家嘴世纪汇广场的\",\"url\":\"http://3g.163.com/money/16/1026/21/C4B8OL7P002580T4.html\",\"docid\":\"C4B8OL7P002580T4\",\"title\":\"200亿出售陆家嘴世纪汇！李嘉诚又卖地了\",\"source\":\"华尔街见闻\",\"priority\":150,\"lmodify\":\"2016-10-27 11:28:34\",\"imgsrc\":\"http://cms-bucket.nosdn.127.net/b0bd14d66aab43abb4f6a1b8362d5f5920161026215811.jpeg\",\"subtitle\":\"\",\"boardid\":\"money_bbs\",\"ptime\":\"2016-10-26 21:55:23\"},{\"postid\":\"C4D4ER88002581PP\",\"url_3w\":\"http://money.163.com/16/1027/15/C4D4ER88002581PP.html\",\"votecount\":722,\"replyCount\":816,\"ltitle\":\"年入12万算高收入者是假的 但个税过高却是事实\",\"digest\":\"点击继续查看使用安卓和iPhone最新版本客户端可获得更流畅体验，下载地址：安卓用户点这里iPhone用户点这里\",\"url\":\"http://3g.163.com/money/16/1027/15/C4D4ER88002581PP.html\",\"docid\":\"C4D4ER88002581PP\",\"title\":\"年入12万算高收入者是假的 但个税过高却是事实\",\"TAGS\":\"独家\",\"source\":\"网易财经\",\"priority\":146,\"lmodify\":\"2016-10-27 16:55:00\",\"boardid\":\"money_bbs\",\"imgsrc\":\"http://cms-bucket.nosdn.127.net/a55b824f9f53411c94b1a167f5c3191820161027152042.jpeg\",\"subtitle\":\"\",\"ptime\":\"2016-10-27 15:18:36\",\"TAG\":\"独家\"},{\"docid\":\"9IG74V5H00963VRO_C4DCLUTLlqfengupdateDoc\",\"editor\":[],\"title\":\"87岁茅于轼在家秘密研究啥？美女小编上门揭秘\",\"TAGS\":\"直播预告\",\"skipID\":\"102206\",\"source\":\"网易原创\",\"imgType\":1,\"priority\":105,\"live_info\":{\"mutilVideo\":false,\"pano\":false,\"remindTag\":1,\"end_time\":\"2016-10-28 21:00:00\",\"user_count\":25918,\"roomId\":102206,\"start_time\":\"2016-10-28 15:30:00\",\"type\":0,\"video\":false},\"lmodify\":\"2016-10-27 17:53:28\",\"imgsrc\":\"http://cms-bucket.nosdn.127.net/2e592884f28b41febeef4c7acebe4ca920161027174523.jpeg\",\"digest\":\"\",\"skipType\":\"live\",\"ptime\":\"2016-10-27 17:42:18\",\"TAG\":\"直播预告\"},{\"docid\":\"9IG74V5H00963VRO_C4DCFPJTlqfengupdateDoc\",\"editor\":[],\"title\":\"慢就业＝啃老？清华、北影的鲜肉怎么看\",\"TAGS\":\"直播预告\",\"skipID\":\"102184\",\"source\":\"网易原创\",\"imgType\":1,\"priority\":105,\"live_info\":{\"mutilVideo\":false,\"pano\":false,\"remindTag\":1,\"end_time\":\"2016-10-28 18:00:00\",\"user_count\":25446,\"roomId\":102184,\"start_time\":\"2016-10-28 11:00:00\",\"type\":0,\"video\":false},\"lmodify\":\"2016-10-27 18:26:53\",\"imgsrc\":\"http://cms-bucket.nosdn.127.net/1ffd7c80b2194b6d8e15616e705094b520161027182650.jpeg\",\"digest\":\"\",\"skipType\":\"live\",\"ptime\":\"2016-10-27 17:38:55\",\"TAG\":\"直播预告\"},{\"docid\":\"9IG74V5H00963VRO_C4D92LBBlqfengupdateDoc\",\"editor\":[],\"title\":\"人民币贬值别怕！帅哥美女教你看紧钱袋子\",\"TAGS\":\"直播预告\",\"skipID\":\"102086\",\"source\":\"网易原创\",\"imgType\":1,\"priority\":105,\"live_info\":{\"mutilVideo\":false,\"pano\":false,\"remindTag\":1,\"end_time\":\"2016-10-28 15:00:00\",\"user_count\":39297,\"roomId\":102086,\"start_time\":\"2016-10-28 09:00:32\",\"type\":0,\"video\":false},\"lmodify\":\"2016-10-27 16:39:58\",\"imgsrc\":\"http://cms-bucket.nosdn.127.net/f0076ddae72048428445baeeaf49867120161026095511.jpg\",\"digest\":\"\",\"skipType\":\"live\",\"ptime\":\"2016-10-27 16:39:19\",\"TAG\":\"直播预告\"}]";
    private static final String BASIC_SAMPLE_PACKAGE
            = "com.example.android.testing.uiautomator.BasicSample";
private static  String json1="{\"body\":\"<p>　　<b>网易财经11月2日讯<\\/b> 周三早盘创业板指低开后窄幅盘整，临近午盘加速跳水跌破周二低点。午后开盘继续下探跌幅近1%。截至发稿创业板指报2155.62点，下跌21.11点，跌幅为0.97%。个股方面180家上涨，312家下跌。其中金利泰、中富通等涨幅居前；蒙草生态、飞力达等跌幅居前。<\\/p><p>　　今日创业板新股申购情况<\\/p><p>　　神宇股份此次发行总数为2000万股，网上发行2000万股，发行市盈率22.99倍，申购代码：300563，申购价格：8.84元，单一帐户申购上限20000股，申购数量500股整数倍。基本信息如下<\\/p><p>　　【基本信息】<\\/p><!--IMG#0--><p>　　【公司简介】<\\/p><p>　　神宇通信科技股份公司，是一家专业从事射频信号电缆的技术研发、生产和销售的公司。<\\/p><p>　　【筹集资金用途】<\\/p><!--IMG#1--><p><!--link0--><\\/p>\",\"users\":[],\"ydbaike\":[],\"replyCount\":0,\"link\":[{\"ref\":\"<!--link0-->\",\"title\":\"下载网易财经APP\",\"imgsrc\":\"http://img0.ph.126.net/rp7MH4T4RZzXvjzG2RveTw==/54606145499982072.png\",\"digest\":\"手中个股买还是卖 马上咨询牛人\",\"type\":\"linkCard\",\"href\":\"http://i.money.163.com/?xinwenapp\"}],\"img\":[{\"ref\":\"<!--IMG#0-->\",\"pixel\":\"665*243\",\"alt\":\"\",\"src\":\"http://cms-bucket.nosdn.127.net/e5aad65289304e1ab76ca1ef8ea1ed0120161101173833.png\"},{\"ref\":\"<!--IMG#1-->\",\"pixel\":\"467*199\",\"alt\":\"\",\"src\":\"http://cms-bucket.nosdn.127.net/051ebdb3da934b27a53a8abfc0a5993d20161101173931.png\"}],\"votes\":[],\"shareLink\":\"http://c.m.163.com/news/a/C4SC041Q0025814S.html?spss=newsapp&spsw=1\",\"digest\":\"\",\"topiclist_news\":[{\"hasCover\":false,\"subnum\":\"超过1000万\",\"alias\":\"Business\",\"tname\":\"财经\",\"ename\":\"caijing\",\"tid\":\"T1348648756099\",\"cid\":\"C1348648727071\"},{\"hasCover\":false,\"subnum\":\"6055\",\"alias\":\"stock\",\"tname\":\"股票\",\"ename\":\"stock\",\"tid\":\"T1473054348939\",\"cid\":\"C1348648727071\"}],\"dkeys\":\"创业板指,加速,下跌\",\"ec\":\"王宏贵_NF7326\",\"topiclist\":[{\"hasCover\":false,\"subnum\":\"230.3万\",\"alias\":\"最有影响力投资门户\",\"tname\":\"网易股票\",\"ename\":\"gupiao\",\"tid\":\"T1348648784889\",\"cid\":\"C1378977941637\"}],\"docid\":\"C4SC041Q0025814S\",\"sourceinfo\":{\"alias\":\"有态度的财经门户\",\"ename\":\"T1428648190649\",\"tname\":\"网易财经\",\"tid\":\"T1428648190649\"},\"picnews\":true,\"title\":\"快讯：创业板指加速跳水跌近1% 180只个股下跌\",\"tid\":\"\",\"template\":\"normal\",\"threadVote\":4,\"threadAgainst\":4,\"boboList\":[],\"replyBoard\":\"money_bbs\",\"source\":\"网易财经\",\"hasNext\":false,\"voicecomment\":\"off\",\"relative_sys\":[{\"id\":\"C4RN1SN800258138\",\"title\":\"新股提醒：上海银行等2股申购 海兴电力缴款\",\"source\":\"网易财经\",\"imgsrc\":\"http://cms-bucket.nosdn.127.net/8312857e24a64075a280a5d247bebd9d20161101173601.png\",\"docID\":\"C4RN1SN800258138\",\"from\":\"HZ\",\"type\":\"doc\",\"ptime\":\"2016-11-02 07:12:56\",\"href\":\"\"},{\"id\":\"C4AR6O2A002588TN\",\"title\":\"易金经牛人解盘:5日均线强支撑 回踩蓄势为冲关 \",\"source\":\"网易财经\",\"imgsrc\":\"http://img5.cache.netease.com/stock/2016/2/19/201602191102585b8ba.jpg\",\"docID\":\"C4AR6O2A002588TN\",\"from\":\"HZ\",\"type\":\"doc\",\"ptime\":\"2016-10-26 17:58:24\",\"href\":\"\"},{\"id\":\"C4PG28HU002580S6\",\"title\":\"创业板成绩单五人谈： 成绩值得肯定，尚存发展空间\",\"source\":\"21世纪经济报道\",\"imgsrc\":\"http://cms-bucket.nosdn.127.net/catchpic/e/ed/ed49a48d36e32a5b3ae808fe1856a396.jpg\",\"docID\":\"C4PG28HU002580S6\",\"from\":\"HZ\",\"type\":\"doc\",\"ptime\":\"2016-11-01 10:03:00\",\"href\":\"\"}],\"ptime\":\"2016-11-02 13:18:58\"}";


    private RetrofitHelper mRetrofitHelper;

    @Before
    public void start()
    {
        mRetrofitHelper = new RetrofitHelper();
    }

    @Test
    public void testNewListData()
    {
        Log.e("test", "testNewListData +++");
       final NewsDetail newsDetail=new  Gson().fromJson(json1, NewsDetail.class);
        Map<String, NewsDetail> map = new HashMap<>();
        map.put("C4SC041Q0025814S", newsDetail);

        mRetrofitHelper.fetchYiNewsDetail("C4SC041Q0025814S")
                .flatMap(new Func1<Map<String, NewsDetail>, Observable<NewsDetail>>()
                {
                    @Override
                    public Observable<NewsDetail> call(Map<String, NewsDetail> stringListMap)
                    {
                        Log.e("test", ""+new Gson().toJson(stringListMap));
                        return Observable.just(stringListMap.get("C4SC041Q0025814S"));
                    }
                })
                .compose(RxUtil.<NewsDetail>rxSchedulerHelper())
                .subscribe(new Action1<NewsDetail>()
                {
                    @Override
                    public void call(NewsDetail newsDetail)
                    {
                   //     Log.e("getContent", " " + new Gson().toJson(newsDetail));
                        Log.e("test", "连接成功" );
                    }
                }, new Action1<Throwable>()
                {
                    @Override
                    public void call(Throwable throwable)
                    {
                        StringWriter sw = new StringWriter();
                        PrintWriter pw = new PrintWriter(sw);
                        throwable.printStackTrace(pw);
                        pw.close();
                        Log.e("test", "网络连接错误 " +sw.toString());
                    }
                });


    }

    @Test
    public void testNewListDatas()
    {
        Log.e("test", "testNewListDatas +++");
        mRetrofitHelper.fetchYiNewsList(WangyiApis.MOVIE_ID, 0)
                .flatMap(new Func1<Map<String, List<NewsSummary>>, Observable<NewsSummary>>()
                {
                    @Override
                    public Observable<NewsSummary> call(Map<String, List<NewsSummary>> stringListMap)
                    {
                        return Observable.from(stringListMap.get(WangyiApis.MOVIE_ID));
                    }
                })
                .distinct()
                .toList()
                .compose(RxUtil.<List<NewsSummary>>defaultSchedulers())
                .subscribe(new Action1<List<NewsSummary>>()
                {
                    @Override
                    public void call(List<NewsSummary> newsSummaries)
                    {
                        String string = new Gson().toJson(newsSummaries);

                        Log.e("test", "newsSummaries +++" + string);
                    }
                }, new Action1<Throwable>()
                {
                    @Override
                    public void call(Throwable throwable)
                    {
                        Log.e("test", "网络连接错误");
                    }
                });


    }

    @Test
    public void testDailyListDatas()
    {
        Log.e("test", "testDailyListDatas  ");
        mRetrofitHelper.fetchDetailInfo(8943113)
                .compose(RxUtil.<ZhihuDetailBean>rxSchedulerHelper())
                .subscribe(new Action1<ZhihuDetailBean>() {
                    @Override
                    public void call(ZhihuDetailBean zhihuDetailBean) {
                        String string = new Gson().toJson(zhihuDetailBean);

                        Log.e("test", "newsSummaries +++" + string);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("test", "网络连接错误");
                    }
                });
    }

    @Test
    public void testDailyListData()
    {
        mRetrofitHelper.fetchDailyListInfo()
                .compose(RxUtil.<DailyListBean>rxSchedulerHelper())
                .subscribe(new Action1<DailyListBean>()
                {
                    @Override
                    public void call(DailyListBean dailyListBean)
                    {
                        Log.e("test", new Gson().toJson(dailyListBean));

                    }
                }, new Action1<Throwable>()
                {
                    @Override
                    public void call(Throwable throwable)
                    {
                        Log.e("test", "网络访问失败");
                    }
                });
    }
}
