package com.gamesky.card.service.impl;

import com.gamesky.card.core.*;
import com.gamesky.card.core.exceptions.MarshalException;
import com.gamesky.card.service.CheckCodeService;
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
    public boolean send(final String phone, String code) {
        String content = MessageFormat.format(placeholder, code);
        for (MessageSender<SmsMessage> sender : messageSenders) {
            if (!sender.send(new SmsMessage(phone, content))) {
                logger.error("消息发送出错");
                continue;
            }

            try {
                marshaller.marshal(new Keyable() {
                    @Override
                    public String k() {
                        return Constants.CHECK_CODE_KEY_PREFIX + ":" + phone;
                    }
                    public long expire() {return 60;}
                }, code);
            } catch (MarshalException e) {
                logger.error("验证码存储出错");
                return false;
            }

            return true;
        }

        return false;
    }
}
