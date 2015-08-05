package com.gamesky.card.inner.controller;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * lianghongbin on 15/8/5.
 */
public class H5GameSchedule {

    private String url;
    private ContentHandler<String> contentHandler;
    private static final Logger logger = LoggerFactory.getLogger(H5GameSchedule.class);

    public void setUrl(String url) {
        this.url = url;
    }

    public void setContentHandler(ContentHandler<String> contentHandler) {
        this.contentHandler = contentHandler;
    }

    public void fetch() {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response;
        try {
            HttpPost httpPost = new HttpPost(url);
            response = httpclient.execute(httpPost);
        } catch (Exception e) {
            logger.error("请求H5小游戏出错:{}", e);
            return;
        }

        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            logger.error("请求H5小游戏出错,错误状态吗:{}", response.getStatusLine().getStatusCode());
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
        contentHandler.handle(data);
    }

    public static void main(String[] args) {
        H5GameSchedule h5GameSchedule = new H5GameSchedule();
        h5GameSchedule.fetch();
    }
}
