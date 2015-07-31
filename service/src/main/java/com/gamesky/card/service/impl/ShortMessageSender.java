package com.gamesky.card.service.impl;

import com.gamesky.card.core.MessageSender;
import com.gamesky.card.core.SmsMessage;
import com.gamesky.card.core.utils.MD5Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 短信发送通道
 * Created on 6/5/15.
 *
 * @Author lianghongbin
 */
public class ShortMessageSender implements MessageSender<SmsMessage> {

    private static Logger logger = LoggerFactory.getLogger(ShortMessageSender.class);
    private String url;

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean send(SmsMessage... t) {
        String uid = "33867986";
        String op = "5";
        String from = "7kserver";
        String key = "FR-!B]6QDd4?s_c^tm'5C2H*w)t,x6.}";
        String content = "\""+ StringEscapeUtils.escapeJava(t[0].getMessage()).toLowerCase() +"\"";
        String token = MD5Utils.toString(uid + key + uid + t[0].getPhone() + content + op + from + "sendMobileMsg");

        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response;
        try {
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("uid", uid));
            params.add(new BasicNameValuePair("mobile", t[0].getPhone()));
            params.add(new BasicNameValuePair("content", content));
            params.add(new BasicNameValuePair("op", op));
            params.add(new BasicNameValuePair("from", from));
            params.add(new BasicNameValuePair("token", token));

            httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
            response = httpclient.execute(httpPost);
        } catch (Exception e) {
            return false;
        }

        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            return false;
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
            } catch (Exception ignored) {
            }
        }

        String data = StringEscapeUtils.unescapeJava(stringBuilder.toString());
        Gson gson = new Gson();
        Map<String, String> map = gson.fromJson(data, new TypeToken<Map<String, String>>() {}.getType());

        String status = map.get("status");
        if (!status.equals("1")) {
            logger.error("消息发送出错,手机号:{}，状态码：{}， 错误原因：{}",t[0].getPhone(), status, map.get("data"));
        }

        return map.get("status").equals("1");
    }
}
