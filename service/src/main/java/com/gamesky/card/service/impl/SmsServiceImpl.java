package com.gamesky.card.service.impl;

import com.gamesky.card.core.Keyable;
import com.gamesky.card.core.Marshaller;
import com.gamesky.card.core.MessageSender;
import com.gamesky.card.core.SmsMessage;
import com.gamesky.card.service.CodeGenerator;
import com.gamesky.card.service.SmsService;
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
public class SmsServiceImpl implements SmsService {

    private Marshaller<Keyable, Serializable> marshaller;
    private String placeholder;
    private CodeGenerator codeGenerator;
    private List<MessageSender<SmsMessage>> messageSenders;
    private static final Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);

    public void setMarshaller(Marshaller<Keyable, Serializable> marshaller) {
        this.marshaller = marshaller;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public void setCodeGenerator(CodeGenerator codeGenerator) {
        this.codeGenerator = codeGenerator;
    }

    public void setMessageSenders(List<MessageSender<SmsMessage>> messageSenders) {
        this.messageSenders = messageSenders;
    }

    @Override
    public boolean send(final String phone) {
        String code = codeGenerator.generate();
        String content = MessageFormat.format(placeholder, code);
        for (MessageSender<SmsMessage> sender : messageSenders) {
            if (!sender.send(new SmsMessage(phone, content))) {
                logger.error("消息发送出错");
                continue;
            }

            marshaller.marshal(new Keyable() {
                @Override
                public String k() {
                    return phone;
                }
            }, code);
            return true;
        }

        return false;
    }
}
