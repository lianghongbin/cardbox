package com.gamesky.card.core;

import java.io.Serializable;

/**
 * 消息发送接口（短信、邮件、站内）
 * Created on 6/5/15.
 *
 * @Author lianghongbin
 */
public interface MessageSender<T extends Serializable> {

    public boolean send(T... t);
}
