package com.ch.spider.core;

import com.ch.spider.dao.ArticleDao;
import com.ch.spider.dao.MemberDao;
import com.ch.spider.entity.taskinfo;
import com.ch.spider.utils.http.HtmlXpath;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.ch.spider.core.SpiderDataManager_bitcoin_blogs.Down;
import static com.ch.spider.core.SpiderDataManager_bitcoin_blogs.DownLoadCoverPic;
import static com.ch.spider.core.SpiderDataManager_bitcoin_blogs.DownLoadPic;

public class SpiderDataManager_newsbtc {
    private String url;
    private ArticleDao articleDao;
    private MemberDao memberDao;

    private static final Logger log = LoggerFactory.getLogger(SpiderDataManager_newsbtc.class);
    private AtomicBoolean shutdownDone = new AtomicBoolean(false);

    public void setUrl(String url) {
        this.url = url;
    }

    public SpiderDataManager_newsbtc(ArticleDao articleDao, MemberDao memberDao) {
//		this.demoService =demoService;
        this.articleDao = articleDao;
        this.memberDao = memberDao;
        initdata_thread();
    }
    private Thread rThread;
    volatile boolean done;
    private void initdata_thread() {
        rThread = new Thread() {
            public void run() {
                goThread(this);
            }
        };
        rThread.setName("Thread Name: " + "BKB");
        rThread.setDaemon(true);
    }
    public void startupService() {
        rThread.start();
    }
    public void stopService() {
        done = true;
        synchronized (shutdownDone) {
            if (!shutdownDone.get()) {
                try {
                    shutdownDone.wait(100);
                } catch (InterruptedException e) {
                }
            }
        }
    }
    private void goThread(Thread thisThread) {
        try {
            // Write out packets from the queue.
            while (!done && (rThread == thisThread)) {
                spider_bitcoin_blogs();
                try {
                    Thread.sleep(6000 * 6);
                } catch (InterruptedException e) {
                    log.warn("Spider_newsbtc Sleep: ExceptionMsg:" + e.getMessage());
                    continue;
                }
            }
            shutdownDone.set(true);
            synchronized (shutdownDone) {
                shutdownDone.notify();
            }
        } catch (Exception ioe) {

            if (!(done)) {
                stopService();

            }
        }
    }

    private void spider_bitcoin_blogs() {
        taskinfo info1=new taskinfo();
        info1.setColumnUrl("https://www.newsbtc.com/category/crypto/bitcoin/");
        info1.setTypeID(2);//比特币
        taskinfo info2=new taskinfo();
        info2.setColumnUrl("https://www.newsbtc.com/category/crypto/");
        info2.setTypeID(3);//加密
        taskinfo info3=new taskinfo();
        info3.setColumnUrl("https://www.newsbtc.com/category/sponsored-stories/");
        info3.setTypeID(4);//赞助
        taskinfo info4=new taskinfo();
        info4.setColumnUrl("https://www.newsbtc.com/category/ico/");
        info4.setTypeID(5);//ico
        taskinfo info5=new taskinfo();
        info5.setColumnUrl("https://www.newsbtc.com/category/blockchain-projects/");
        info5.setTypeID(6);//区块链项目
        taskinfo info6=new taskinfo();
        info6.setColumnUrl("https://www.newsbtc.com/category/crypto-tech/");
        info6.setTypeID(7);//加密技术
        taskinfo info7=new taskinfo();
        info7.setColumnUrl("https://www.newsbtc.com/category/industry-news/");
        info7.setTypeID(8);//行业新闻
        taskinfo info8=new taskinfo();
        info8.setColumnUrl("https://www.newsbtc.com/category/analysis/");
        info8.setTypeID(9);//分析

        List<String> listSiteUrl = new ArrayList<>();
        listSiteUrl.add(info1.getColumnUrl());
        listSiteUrl.add(info2.getColumnUrl());
        listSiteUrl.add(info3.getColumnUrl());
        listSiteUrl.add(info4.getColumnUrl());
        listSiteUrl.add(info5.getColumnUrl());
        listSiteUrl.add(info6.getColumnUrl());
        listSiteUrl.add(info7.getColumnUrl());
        listSiteUrl.add(info8.getColumnUrl());

        while(true) {
            for(String url : listSiteUrl)
            {
                newsbtc(url);
                System.out.println("此网站爬取完成："+url);
            }
        }
    }

    public void newsbtc(String url) {
        System.out.println(url);
        try {
             Document doc = Jsoup.connect(url).get();////*[@id="latest-news"]/div/div[2]
             Elements abstractContent = doc.body().getElementsByClass("row posts");
             doc.body().getElementsByTag("a");
             //获取所有文章简介的信息
             Element abs = abstractContent.get(0);
             abs.removeClass("left-sidebar three columns");
             abs.removeClass("right-sidebar three columns fr");
             abs.removeClass("separator six columns");
             Elements abstractTagContent = abs.getElementsByClass("image-hover effect-slash");
             for (Element e : abstractTagContent)
             {
                 System.out.println(e.toString());

                 //获取文章链接
                 String hrefValue = HtmlXpath.GetAttribute(e.toString(), "//a[@class='featured-image']", "href");
                 System.out.println("正在爬取" + hrefValue + "...");
                 //获取封面图片(小图)
                 String abstractImg = HtmlXpath.GetAttribute(e.toString(), "//a[@class='featured-image']/figure/img", "src");
                 System.out.println(abstractImg);
                 String abstractImgPath = DownLoadCoverPic(abstractImg);
                 System.out.println("地址" + abstractImgPath);
                 //获取标题
                 String abstractTitle = HtmlXpath.GetContent(e.toString(), "//h3[@class='title small']");
                 //获取时间、作者
                 String abstractTime = HtmlXpath.GetAttribute(e.toString(), "//div[@class='entry-meta']/time", "datetime");
                 //获取文章内容
                 Document articleDoc = Jsoup.connect(hrefValue).get();
                 Elements articleContentList = articleDoc.body().getElementsByClass("entry-content");//获取所有文章简介的信息

                 Element articleContent = articleContentList.get(0);
                 //上方大图与封面相同的图片
                 String articleImg = HtmlXpath.GetAttribute(articleContent.toString(), "//div[@class='post-thumbnail']/a/img", "src");
                 String articleImgPath = DownLoadPic(articleImg);
                 //清除多余数据
                 articleContent.getElementsByClass("post-thumbnail").get(0).remove();
                 articleContent.getElementsByClass("content-ads").get(0).remove();
                 articleContent.getElementsByClass("vuukle-powerbar").get(0).remove();
                 articleContent.getElementById("vuukle-emote").remove();
                 articleContent.getElementsByTag("center").remove();
                 articleContent.getElementsByTag("script").remove();
                 articleContent.getElementsByTag("respond").remove();
                 String content = Down(articleContent.toString());
             }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


