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

public class SpiderDataManager_ccn {
    private String url;
    private ArticleDao articleDao;
    private MemberDao memberDao;

    private static final Logger log = LoggerFactory.getLogger(SpiderDataManager_ccn.class);
    private AtomicBoolean shutdownDone = new AtomicBoolean(false);

    public void setUrl(String url) {
        this.url = url;
    }

    public SpiderDataManager_ccn(ArticleDao articleDao, MemberDao memberDao) {
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
                    log.warn("Spider_ccn Sleep: ExceptionMsg:" + e.getMessage());
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
        info1.setColumnUrl("https://www.newsbtc.com/category/crypto/bitcoin");
        info1.setTypeID(2);//技术
        taskinfo info2=new taskinfo();
        info2.setColumnUrl("https://www.newsbtc.com/category/crypto/bitcoin");
        info2.setTypeID(3);//市场
        taskinfo info3=new taskinfo();
        info3.setColumnUrl("https://www.newsbtc.com/category/crypto/bitcoin");
        info3.setTypeID(4);//商业
        List<String> listSiteUrl = new ArrayList<>();
        listSiteUrl.add(info1.getColumnUrl());
        listSiteUrl.add(info2.getColumnUrl());
        listSiteUrl.add(info3.getColumnUrl());
        while(true) {
            for(String url : listSiteUrl)
            {
                ccn(url);
                System.out.println("此网站爬取完成："+url);
            }
        }
    }

    public void ccn(String url) {
        System.out.println(url);
        try {
            Document doc = Jsoup.connect("https://www.ccn.com/news/").get();
            Elements abstractContent = doc.body().getElementsByClass("posts-row");//获取所有文章简介的信息
            for (Element abs : abstractContent) {
                //获取文章链接
                String hrefValue = HtmlXpath.GetAttribute(abs.toString(), "//div[@class='post-thumbnail mb-2']/a", "href");
                System.out.println("正在爬取" + hrefValue + "...");
                //获取封面图片(小图)
                String abstractImg = HtmlXpath.GetAttribute(abs.toString(), "//div[@class='post-thumbnail mb-2']/a/img", "src");
                System.out.println(abstractImg);
                String abstractImgPath = DownLoadCoverPic(abstractImg);
                System.out.println("地址" + abstractImgPath);
                //获取标题
                String abstractTitle = HtmlXpath.GetContent(abs.toString(), "//h4[@class='entry-title font-weight-bold']/a");
                //获取时间、作者
                String abstractTime = HtmlXpath.GetAttribute(abs.toString(), "//div[@class='entry-meta']/time", "datetime");
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


