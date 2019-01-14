package com.ch.spider.core;

import com.ch.spider.dao.ArticleDao;
import com.ch.spider.dao.MemberDao;
import com.ch.spider.entity.taskinfo;
import com.ch.spider.utils.encrypt.MD5Utils;
import com.ch.spider.utils.file.FileUtils;
import com.ch.spider.utils.http.HtmlReg;
import com.ch.spider.utils.http.HtmlXpath;
import com.ch.spider.utils.http.HttpUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class SpiderDataManager_bitcoin_blogs {
    private static String url;
    private ArticleDao articleDao;
    private MemberDao memberDao;
    public void setUrl(String url) {
        this.url = url;
    }
    private static final Logger log = LoggerFactory.getLogger(SpiderDataManager_bitcoin_blogs.class);
    private AtomicBoolean shutdownDone = new AtomicBoolean(false);

    public SpiderDataManager_bitcoin_blogs(ArticleDao articleDao, MemberDao memberDao) {
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
                    log.warn("Spider_bit_coin_blog Sleep: ExceptionMsg:" + e.getMessage());
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
        info1.setColumnUrl("https://www.coindesk.com/category/technology-news");
        info1.setTypeID(2);//技术
        taskinfo info2=new taskinfo();
        info2.setColumnUrl("https://www.coindesk.com/category/markets-news");
        info2.setTypeID(3);//市场
        taskinfo info3=new taskinfo();
        info3.setColumnUrl("https://www.coindesk.com/category/business-news");
        info3.setTypeID(4);//商业
        List<String> listSiteUrl = new ArrayList<>();
        listSiteUrl.add(info1.getColumnUrl());
        listSiteUrl.add(info2.getColumnUrl());
        listSiteUrl.add(info3.getColumnUrl());
        while(true) {
            for(String url : listSiteUrl)
            {
                coindesk(url);
                System.out.println("此网站爬取完成："+url);
            }
        }
    }

    public void coindesk(String url){
        System.out.println(url);
        String html = HttpUtils.doGet(url, null, "utf-8", "", 0);
        String abstractHtml = HtmlXpath.GetInnerHtml(html, "//*[@id=\"article-streams\"]/div[@class='article-stream']/div[@class='article-set']");
        //获取文章简介封面图等
        List<String> articleUrlList =new ArrayList<>();
        String[] articleUrlString = abstractHtml.split("</a>");
        //获取文章链接、标题、简介、封面图等
        for(String articleUrlStr :articleUrlString) {
            if(articleUrlStr==null||articleUrlStr.equals(""))
            {
                continue;
            }
            try{
            //获取文章链接
            String hrefValue = HtmlXpath.GetAttribute(articleUrlStr, "//a", "href");
            System.out.println("正在爬取"+hrefValue+"...");
            //获取封面图片(小图)
            String abstractImg = HtmlXpath.GetAttribute(articleUrlStr, "//a/div[@class='image']/img","src");
            System.out.println(abstractImg);
            String abstractImgPath = DownLoadCoverPic(abstractImg);
            //获取标题
            String abstractTitle = HtmlXpath.GetContent(articleUrlStr, "//a/div[@class='meta']/h3");
            //获取时间、作者
            String [] abstractTimeAuth= HtmlXpath.GetContent(articleUrlStr, "//a/div[@class='meta']/div[@class='time']").split("\\|");
            String abstractTime =abstractTimeAuth[0];
            String abstractAuth =abstractTimeAuth[1];
            //获取文章简介
            String abstractContent = HtmlXpath.GetContent(articleUrlStr, "//a/div[@class='meta']/p");
            System.out.println(abstractContent);
            //获取文章详细内容：
            System.out.println(hrefValue);
            String articleContentHtml = HttpUtils.doGet(hrefValue, null, "utf-8", "", 0);
            //获取作者头像

            String articleContent = HtmlXpath.GetInnerHtml(articleContentHtml, "//*[@id=\"articles-container\"]/article[1]/section/div");
            //获取内容图片（大图）
            String articleImg = HtmlXpath.GetAttribute(articleContentHtml, "//*[@id=\"articles-container\"]/article[1]/header/div[1]/div/picture/video/source","srcset");
            System.out.println(articleImg);
            String articleImgPath = DownLoadPic(articleImg);
            }
            catch (Exception e){
                System.out.println(e.toString());
                continue;
            }
        }
    }

    public static String DownLoadPic(String coverpicurl)
    {
        String strResult="";
        try
        {
            String imgpath=System.getProperty("user.dir")+FileUtils.FOLDER_SEPARATOR+"images";
            //获取coverpicurl，文件名
            String[] coverpicNames = coverpicurl.split("/");
            String coverpicName=coverpicNames[coverpicNames.length-1];
            imgpath=imgpath+FileUtils.FOLDER_SEPARATOR+coverpicName;
            int isuccess=HttpUtils.downpic(imgpath,coverpicurl,null,"utf-8");
            if (isuccess==1) {
                strResult=coverpicName;
            }
            else {
                strResult="";
            }
        }catch (Exception ex) {
            String strex=ex.toString();
        }
        return strResult;
    }

    public static  String DownLoadCoverPic(String coverpicurl)
    {
        String strResult="";
        try
        {
            String imgpath=System.getProperty("user.dir")+FileUtils.FOLDER_SEPARATOR+"images"+FileUtils.FOLDER_SEPARATOR+"coverpic";
            //获取coverpicurl，文件名
            String[] coverpicNames = coverpicurl.split("/");
            String coverpicName=coverpicNames[coverpicNames.length-1];
            imgpath=imgpath+FileUtils.FOLDER_SEPARATOR+coverpicName;
            int isuccess=HttpUtils.downpic(imgpath,coverpicurl,null,"utf-8");
            if (isuccess==1) {
                strResult=coverpicName;
            }
            else {
                strResult="";
            }
        }catch (Exception ex)
        {
            String strex=ex.toString();
        }
        return strResult;
    }

    public static String Down(String html)
    {

        String strResult="";
        try
        {
            List<String> listImg=HtmlReg.FindStrByRegList(html,"<img src=.*?>");
            for (int k=0;k<listImg.size();k++)
            {
                String imghtml=listImg.get(k);
                String imgSrc=HtmlXpath.GetAttribute(imghtml,"//img","src");
                String imgurl="";
                if (imgSrc.toLowerCase().contains("http"))
                {
                    imgurl=imgSrc;
                }
                else
                {
                    return "";
                }

                //下载文件
                String imgpath=System.getProperty("user.dir")+FileUtils.FOLDER_SEPARATOR+"images";
                String filename=MD5Utils.encrypt(imgurl,"MD5");
                imgpath=imgpath+FileUtils.FOLDER_SEPARATOR+filename+".jpg";
                int isuccess=HttpUtils.downpic(imgpath,imgurl,null,"utf-8");
                if (isuccess==1)
                {
//                    String newSrc=FileUtils.FOLDER_SEPARATOR+"images"+FileUtils.FOLDER_SEPARATOR+filename+".jpg";
                    String newSrc1= url+"images/"+filename+".jpg";

                    String newImgUrl=imghtml.replace(imgSrc,newSrc1);
                    html=html.replace(imghtml,newImgUrl);

                }
                else
                {
                    html=html.replace(imghtml,"");
                }

            }
            strResult=html;
        }catch (Exception ex)
        {
            String strex=ex.toString();
            log.info(strex);
        }
        return strResult;
    }
    }


