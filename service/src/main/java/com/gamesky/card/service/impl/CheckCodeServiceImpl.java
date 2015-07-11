package com.gamesky.card.service.impl;

import com.gamesky.card.core.Keyable;
import com.gamesky.card.core.Marshaller;
import com.gamesky.card.core.MessageSender;
import com.gamesky.card.core.SmsMessage;
import com.gamesky.card.core.exceptions.MarshalException;
import com.gamesky.card.core.exceptions.SmsSenderException;
import com.gamesky.card.service.CheckCodeService;
import com.gamesky.card.service.key.CheckCodeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;

/**
 * Created on 3/26/15.
 *
 * @Author lianghongbin
 */
public class CheckCodeServiceImpl implements CheckCodeService {

    private Marshaller<Keyable, Serializable> marshaller;
    private String placeholder;
    private List<MessageSender<SmsMessage>> messageSenders;
    private static final Logger logger = LoggerFactory.getLogger(CheckCodeServiceImpl.class);

    public void setMarshaller(Marshaller<Keyable, Serializable> marshaller) {
        this.marshaller = marshaller;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public void setMessageSenders(List<MessageSender<SmsMessage>> messageSenders) {
        this.messageSenders = messageSenders;
    }

    @Override
    public void send(final String phone, String code) throws MarshalException, SmsSenderException{
        String content = MessageFormat.format(placeholder, code);
        for (MessageSender<SmsMessage> sender : messageSenders) {
            if (!sender.send(new SmsMessage(phone, content))) {
                logger.error("消息发送出错");
                continue;
            }

            try {
                marshaller.marshal(new CheckCodeKey(phone, 60), code);
                return;
            } catch (MarshalException e) {
                logger.error("验证码存储出错");
                throw new MarshalException("验证码存储出错");
            }
        }

        throw new SmsSenderException("消息发送出错");
    }
}
