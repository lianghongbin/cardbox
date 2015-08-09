package com.gamesky.card.inner.controller;

import com.gamesky.card.core.utils.MD5Utils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * lianghongbin on 15/8/5.
 */
public class H5GameSchedule implements InitializingBean {

    private String httpURL;
    private int times;
    private int count;
    private ContentHandler<String> contentHandler;
    private static final Logger logger = LoggerFactory.getLogger(H5GameSchedule.class);

    public void setHttpURL(String httpURL) {
        String tmp = httpURL.toLowerCase();
        if (!tmp.startsWith("http://")) {
            this.httpURL = StringUtils.substring(httpURL, tmp.indexOf("http://"));
        } else {
            this.httpURL = httpURL;
        }
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setContentHandler(ContentHandler<String> contentHandler) {
        this.contentHandler = contentHandler;
    }

    public void fetch() {
        logger.info("fetch h5 game starting from online interface ......");

        Thread runnable = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int page = 1; page <= times; page++) {
                    logger.info("fetch page number {}", page);
                    doFetch(page);

                    try {
                        Thread.sleep(3000);
                        logger.info("sleep 3 seconds");
                    } catch (InterruptedException ignored) {
                    }
                }
            }
        });

        runnable.start();

        try {
            runnable.join();
        } catch (InterruptedException ignored) {
        }

        logger.info("fetch h5 game finished from online interface!");
    }

    private void doFetch(int pageNum) {
        String url = httpURL + "&pagenum=" + pageNum + "&pagesize=" + count + "&token="+getMd5Token(pageNum);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response;
        try {
            HttpGet httpPost = new HttpGet(url);response = httpclient.execute(httpPost);
        } catch (Exception e) {
            logger.info("请求H5小游戏出错:{}", e.getMessage());
            return;
        }

        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            logger.info("请求H5小游戏出错,错误状态吗:{}", response.getStatusLine().getStatusCode());
            return;
        }

        HttpEntity httpEntity = response.getEntity();
        StringBuilder stringBuilder = new StringBuilder();
        byte[] bytes = new byte[1024];
        int size;
        InputStream inputStream = null;
        try {
            inputStream = httpEntity.getContent();
            while ((size = inputStream.read(bytes)) > 0) {
                String str = new String(bytes, 0, size, "UTF-8");
                stringBuilder.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                response.close();
                httpclient.close();
            } catch (Exception ignored) {
            }
        }

        String data = StringEscapeUtils.unescapeJava(stringBuilder.toString());
        if (data.contains("token error.")) {
            logger.info("fetch h5 game interrupt because error:{}", data);
            return;
        }

        contentHandler.handle(data);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        fetch();
    }

    private String getMd5Token(int pagenum) {
        return MD5Utils.toString("HTML5GAME" + MD5Utils.toString(pagenum + "" + count));
    }
}
