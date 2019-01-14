package com.ch.spider;

import com.ch.spider.core.SpiderDataManager_ccn;
import com.ch.spider.core.SpiderDataManager_newsbtc;
import com.ch.spider.dao.*;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@SpringBootApplication
public class SpiderApplication implements CommandLineRunner {


    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private DataQuickDao dataQuickDao;
    @Value("${btc.url}")
    private String url;

    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource() {
        return new org.apache.tomcat.jdbc.pool.DataSource();
    }
    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mapper/*.xml"));

        return sqlSessionFactoryBean.getObject();
    }


    public static void main(String[] args) {
        SpringApplication.run(SpiderApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        startThreadServer();

        int index = 0;
        while (true) {
            index++;
            if (index == 10000) {
                index = 0;
            }

            logger.info("Spider Number :" + String.valueOf(index));
            Thread.sleep(1000L * 1000);

        }

    }

    private void startThreadServer() {

        logger.info("EnSpider线程开始");
        //bitcoin_blogs
//        SpiderDataManager_bitcoin_blogs article = new SpiderDataManager_bitcoin_blogs(articleDao,memberDao);
//        article.setUrl(url);
//        article.startupService();
        //ccn
//        SpiderDataManager_ccn article1 = new SpiderDataManager_ccn(articleDao,memberDao);
//        article1.setUrl(url);
//        article1.startupService();
        //newsbtc
        SpiderDataManager_newsbtc article1 = new SpiderDataManager_newsbtc(articleDao,memberDao);
        article1.setUrl(url);
        article1.startupService();



        try {
            Thread.sleep(60*1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
