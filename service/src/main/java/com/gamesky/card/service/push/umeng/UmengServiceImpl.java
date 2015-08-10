package com.gamesky.card.service.push.umeng;

import com.gamesky.card.core.PushPayload;
import com.gamesky.card.service.PushService;
import com.gamesky.card.service.push.umeng.android.AndroidUnicast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * LHB on 8/7/15.
 */
public class UmengServiceImpl implements PushService<PushPayload> {

    private final String appKey;
    private final String secret;
    private static final Logger logger = LoggerFactory.getLogger(UmengServiceImpl.class);

    public UmengServiceImpl(String appKey, String secret) {
        this.appKey = appKey;
        this.secret = secret;
    }

    @Override
    public void push(PushPayload payload) throws Exception {
        String timestamp = Integer.toString((int)(System.currentTimeMillis() / 1000));
        AndroidUnicast unicast = new AndroidUnicast();
        unicast.setAppMasterSecret(secret);
        unicast.setPredefinedKeyValue("appkey", appKey);
        unicast.setPredefinedKeyValue("timestamp", timestamp);
        // TODO Set your device token
        unicast.setPredefinedKeyValue("device_tokens", payload.getToken());
        unicast.setPredefinedKeyValue("ticker", "礼包大全推送");
        unicast.setPredefinedKeyValue("title",  payload.getTitle());
        unicast.setPredefinedKeyValue("text",   payload.getContent());
        unicast.setPredefinedKeyValue("after_open", "go_app");
        unicast.setPredefinedKeyValue("display_type", "notification");
        unicast.setPredefinedKeyValue("icon", "small_icon");
        unicast.setPredefinedKeyValue("largeIcon", "large_icon");

        // TODO Set 'production_mode' to 'false' if it's a test device.
        // For how to register a test device, please see the developer doc.
        unicast.setPredefinedKeyValue("production_mode", "true");
        // Set customized fields
        unicast.setExtraField("test", "helloworld");
        unicast.send();

        logger.info("umeng push one phone:{}", payload.getToken());
    }
}
