package com.ptu.tinynews.module.yi.presenter;

import com.ptu.tinynews.base.RxPresenter;
import com.ptu.tinynews.model.bean.NewsSummary;
import com.ptu.tinynews.model.http.RetrofitHelper;
import com.ptu.tinynews.contract.YiNewsListContract;
import com.ptu.tinynews.util.RxUtil;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Administrator on 2016/10/23.
 */

public class NewsListPresenter extends RxPresenter<YiNewsListContract.View> implements YiNewsListContract.Presenter
{
   // private static String json = "[{\"postid\":\"C4F27FD60025815F\",\"hasCover\":false,\"hasHead\":1,\"replyCount\":16925,\"ltitle\":\"跌破6.78!人民币兑美元中间价下调122点\",\"hasImg\":1,\"digest\":\"网易财经10月28日讯人民币兑美元中间价报6.7858，上日中间价报6.7736；昨日在岸人民币夜盘收盘报6.7810，刷新逾六年新低。离岸人民币兑美元今日一度\",\"hasIcon\":true,\"docid\":\"C4F27FD60025815F\",\"title\":\"跌破6.78!人民币兑美元中间价下调122点\",\"order\":1,\"priority\":254,\"lmodify\":\"2016-10-28 10:02:45\",\"boardid\":\"money_bbs\",\"ads\":[{\"docid\":\"C4EU1VRO002580S6\",\"title\":\"日媒：中国正以远超日本的速度建造高铁\",\"tag\":\"doc\",\"imgsrc\":\"http://cms-bucket.nosdn.127.net/f7fa250c5bb844ad94aece8b6800990320161028150904.jpeg\",\"subtitle\":\"\",\"url\":\"C4EU1VRO002580S6\"}],\"url_3w\":\"http://money.163.com/16/1028/09/C4F27FD60025815F.html\",\"template\":\"recommend\",\"votecount\":15940,\"skipID\":\"S1477619419530\",\"alias\":\"Business\",\"skipType\":\"special\",\"cid\":\"C1348648727071\",\"url\":\"http://3g.163.com/money/16/1028/09/C4F27FD60025815F.html\",\"specialID\":\"S1477619419530\",\"hasAD\":1,\"source\":\"网易财经\",\"ename\":\"caijing\",\"tname\":\"财经\",\"imgsrc\":\"http://cms-bucket.nosdn.127.net/ab514440ff6d43a1914a70c37ffa791320161028092217.jpeg\",\"subtitle\":\"\",\"ptime\":\"2016-10-28 09:18:06\"},{\"url_3w\":\"\",\"postid\":\"C4ESPART05198SGG\",\"votecount\":28128,\"replyCount\":30362,\"ltitle\":\"东北小学生已锐减800万 人口危机将成死结？\",\"digest\":\"近日国家卫计委发布《中国流动人口发展报告2016》，东北地区生育率极低、人口增长趋于停滞的状况再次引起广泛关注。冰冻三尺非一日之寒，东北的人口问题由来已久。国家\",\"url\":\"\",\"docid\":\"C4ESPART05198SGG\",\"title\":\"东北小学生已锐减800万 人口危机将成死结？\",\"source\":\"面包财经\",\"priority\":200,\"lmodify\":\"2016-10-28 09:31:23\",\"imgsrc\":\"http://cms-bucket.nosdn.127.net/d40fdb43eeb341f798c0a6c1ff12b65320161028083849.png\",\"subtitle\":\"\",\"boardid\":\"dy_wemedia_bbs\",\"ptime\":\"2016-10-28 08:09:54\"},{\"url_3w\":\"http://money.163.com/16/1028/15/C4FLUH990025814S.html\",\"postid\":\"C4FLUH990025814S\",\"votecount\":1325,\"replyCount\":1481,\"ltitle\":\"金融股发力难救主 沪指冲高回落再度考验3100点\",\"digest\":\"网易财经10月28日讯周五早盘，股指呈现冲高回落走势，早盘金融股带领大盘上攻，而后稍有回落，但盘中一度企稳震荡，临近11点领涨龙头金融板块大幅砸盘，两市热点应声\",\"url\":\"http://3g.163.com/money/16/1028/15/C4FLUH990025814S.html\",\"docid\":\"C4FLUH990025814S\",\"title\":\"金融股发力难救主 沪指冲高回落再度考验3100点\",\"source\":\"网易财经\",\"priority\":149,\"lmodify\":\"2016-10-28 15:12:32\",\"imgsrc\":\"http://cms-bucket.nosdn.127.net/6205257a56174f99b63d465c4c3779d320161028150513.jpeg\",\"subtitle\":\"\",\"boardid\":\"money_bbs\",\"ptime\":\"2016-10-28 15:02:45\"},{\"url_3w\":\"http://money.163.com/16/1028/07/C4ERLOHM002580S6.html\",\"postid\":\"C4ERLOHM002580S6\",\"votecount\":4243,\"replyCount\":4490,\"ltitle\":\"浙江最大航运企业宣告破产:6年前大扩张运力翻倍\",\"digest\":\"10月27日，一份疑为杭州市中级人民法院下发的浙江远洋运输股份有限公司破产民事裁定书在网络流传。裁定书称，杭州中院依破产法裁定：宣告浙江远洋运输股份有限公司破产\",\"url\":\"http://3g.163.com/money/16/1028/07/C4ERLOHM002580S6.html\",\"docid\":\"C4ERLOHM002580S6\",\"title\":\"浙江最大航运企业宣告破产:6年前大扩张运力翻倍\",\"source\":\"澎湃新闻网\",\"priority\":149,\"lmodify\":\"2016-10-28 10:38:05\",\"imgsrc\":\"http://cms-bucket.nosdn.127.net/2c16c60cefa349b18d3650571b5bcb9320161028084318.jpeg\",\"subtitle\":\"\",\"boardid\":\"money_bbs\",\"ptime\":\"2016-10-28 07:23:34\"},{\"docid\":\"9IG74V5H00963VRO_C4FC6HHFzhanglinghuiupdateDoc\",\"title\":\"网易财富大讲堂|股市亏钱？来看这就对了\",\"TAGS\":\"直播回顾 视频\",\"skipID\":\"102089\",\"source\":\"网易原创\",\"priority\":101,\"live_info\":{\"mutilVideo\":true,\"pano\":false,\"end_time\":\"2016-10-28 16:22:00\",\"user_count\":29369,\"roomId\":102089,\"start_time\":\"2016-10-28 14:00:00\",\"type\":0,\"video\":false},\"lmodify\":\"2016-10-28 14:28:28\",\"imgsrc\":\"http://cms-bucket.nosdn.127.net/105349105fd748528327449d3dceb46120161028110702.jpeg\",\"digest\":\"\",\"skipType\":\"live\",\"ptime\":\"2016-10-28 12:12:21\",\"TAG\":\"直播回顾\"},{\"docid\":\"9IG74V5H00963VRO_C4F9T5ESbjyangqianupdateDoc\",\"title\":\"【股市掘金】深港通能救市场人气吗？\",\"TAGS\":\"正在直播 视频\",\"skipID\":\"102481\",\"source\":\"网易原创\",\"priority\":97,\"live_info\":{\"mutilVideo\":true,\"pano\":false,\"end_time\":\"2016-10-28 22:00:00\",\"user_count\":5520,\"roomId\":102481,\"start_time\":\"2016-10-28 11:30:00\",\"type\":0,\"video\":false},\"lmodify\":\"2016-10-28 16:30:26\",\"imgsrc\":\"http://cms-bucket.nosdn.127.net/11d83da924b9409ea61cd3f2246a932c20161028113213.jpeg\",\"digest\":\"\",\"skipType\":\"live\",\"ptime\":\"2016-10-28 11:32:17\",\"TAG\":\"正在直播\"},{\"url_3w\":\"http://money.163.com/16/1028/09/C4F1GONS002580S6.html\",\"postid\":\"C4F1GONS002580S6\",\"votecount\":2915,\"replyCount\":3024,\"ltitle\":\"铂涛如家等酒店营销太污：使用睡她等性暗示词语\",\"digest\":\"如家、铂涛等酒店集团，多次在其官方微信公众号文章标题上使用“出轨”“睡她”“开房”等带有性暗示的词语，营销越来越“污”，却无人监管。\",\"url\":\"http://3g.163.com/money/16/1028/09/C4F1GONS002580S6.html\",\"docid\":\"C4F1GONS002580S6\",\"title\":\"铂涛如家等酒店营销太污：使用睡她等性暗示词语\",\"source\":\"南方日报\",\"priority\":97,\"lmodify\":\"2016-10-28 15:55:28\",\"imgsrc\":\"http://cms-bucket.nosdn.127.net/27ecb3cd91794794839f0e383d6fc9bd20161028090757.jpeg\",\"subtitle\":\"\",\"boardid\":\"money_bbs\",\"ptime\":\"2016-10-28 09:05:42\"},{\"url_3w\":\"http://money.163.com/16/1028/16/C4FQMKV1002580S6.html\",\"postid\":\"C4FQMKV1002580S6\",\"votecount\":52,\"replyCount\":62,\"ltitle\":\"人民币汇率遇\\\"黑色10月\\\" 料创汇改以来最大跌幅\",\"digest\":\"自去年8月汇改引发人民币汇率一次性跳贬之后，人民币汇率在今年10月若无意外将创下14个月来的最大月度跌幅。本月迄今，上海现货市场美元兑人民币价格上升1.5%至6\",\"url\":\"http://3g.163.com/money/16/1028/16/C4FQMKV1002580S6.html\",\"docid\":\"C4FQMKV1002580S6\",\"title\":\"人民币汇率遇\\\"黑色10月\\\" 料创汇改以来最大跌幅\",\"source\":\"汇通网\",\"priority\":96,\"lmodify\":\"2016-10-28 16:31:20\",\"imgsrc\":\"http://cms-bucket.nosdn.127.net/c57fe209c5a3413fa2d77a6ef62e14ea20161028163111.jpeg\",\"subtitle\":\"\",\"boardid\":\"money_bbs\",\"ptime\":\"2016-10-28 16:21:00\"},{\"docid\":\"9IG74V5H00963VRO_C4D7S0IJlqfengupdateDoc\",\"editor\":[],\"title\":\"赴美生娃能\\\"烧\\\"百万?谁赚了!谁赔了!\",\"TAGS\":\"直播回顾 视频\",\"skipID\":\"101824\",\"source\":\"网易原创\",\"imgType\":1,\"priority\":96,\"live_info\":{\"mutilVideo\":true,\"pano\":false,\"end_time\":\"2016-10-28 13:54:00\",\"user_count\":464299,\"roomId\":101824,\"start_time\":\"2016-10-28 11:00:00\",\"type\":0,\"video\":false},\"lmodify\":\"2016-10-28 15:55:32\",\"imgsrc\":\"http://cms-bucket.nosdn.127.net/6603d9b8a9f34dceb2c13a2b4dc503b320161027162215.jpeg\",\"digest\":\"\",\"skipType\":\"live\",\"ptime\":\"2016-10-27 16:18:13\",\"TAG\":\"直播回顾\"},{\"url_3w\":\"http://money.163.com/16/1028/08/C4EU1VRO002580S6.html\",\"postid\":\"C4EU1VRO002580S6\",\"votecount\":2017,\"replyCount\":2295,\"ltitle\":\"日媒：中国正以远超日本的速度建造高铁\",\"digest\":\"日经商业在线网站10月27日文章称，如今，总里程(营运公里数)已经超过2万公里，而且速度还在进一步提升。随着高铁起始站的不断完善和乘车变得更加便捷，中国人的出游\",\"url\":\"http://3g.163.com/money/16/1028/08/C4EU1VRO002580S6.html\",\"docid\":\"C4EU1VRO002580S6\",\"title\":\"日媒：中国正以远超日本的速度建造高铁\",\"source\":\"环球时报-环球网\",\"priority\":95,\"lmodify\":\"2016-10-28 15:55:37\",\"imgsrc\":\"http://cms-bucket.nosdn.127.net/569cd351d8c34d818e75cbea61e85ab520160814153915.jpeg\",\"subtitle\":\"\",\"boardid\":\"money_bbs\",\"ptime\":\"2016-10-28 07:35:00\"}]";

    public RetrofitHelper mRetrofitHelper;
    //private RealmHelper mRealmHelper;
    private int page = 0;
    private String lastNews=null;
    private String moreNews;
    @Inject
    public NewsListPresenter(RetrofitHelper mRetrofitHelper)
    {
        this.mRetrofitHelper = mRetrofitHelper;
        // this.mRealmHelper = mRealmHelper;
    }

    @Override
    public void getLastData(final String newsChannelId)
    {
        page = 0;
        Subscription subscription = mRetrofitHelper.fetchYiNewsList(newsChannelId, page)
                .flatMap(new Func1<Map<String, List<NewsSummary>>, Observable<NewsSummary>>()
                {
                    @Override
                    public Observable<NewsSummary> call(Map<String, List<NewsSummary>> stringListMap)
                    {

                        return Observable.from(stringListMap.get(newsChannelId));
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
                        String title = newsSummaries.get(0).getTitle();
                        if (title.equals(lastNews))
                        {
                            mView.showMessage("已经是最新的新闻了");
                        }else
                        {
                            lastNews = title;
                            mView.showNewsListLast(newsSummaries);
                        }
                    }
                }, new Action1<Throwable>()
                {
                    @Override
                    public void call(Throwable throwable)
                    {
                        mView.showMessage("数据加载失败");
                    }
                });
        addSubscribe(subscription);
    }

    @Override
    public void getMoreData(final String newsChannelId)
    {
        page+=20;
        Subscription subscription = mRetrofitHelper.fetchYiNewsList(newsChannelId, page)
                .flatMap(new Func1<Map<String, List<NewsSummary>>, Observable<NewsSummary>>()
                {
                    @Override
                    public Observable<NewsSummary> call(Map<String, List<NewsSummary>> stringListMap)
                    {
                        return Observable.from(stringListMap.get(newsChannelId));
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
                        String title = newsSummaries.get(0).getTitle();
                        if (!title.equals(moreNews))
                        {
                            moreNews = title;
                            mView.showNewsListMore(newsSummaries);
                        }
                    }
                }, new Action1<Throwable>()
                {
                    @Override
                    public void call(Throwable throwable)
                    {
                        mView.showMessage("数据加载失败");
                    }
                });
        addSubscribe(subscription);
    }
}
