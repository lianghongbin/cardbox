package com.gamesky.card.service.push;

import com.gamesky.card.core.PushPayload;
import com.gamesky.card.service.PushService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * LHB on 8/7/15.
 */
public class UmengServiceImpl implements PushService<PushPayload> {

    private static final Logger logger = LoggerFactory.getLogger(UmengServiceImpl.class);

    @Override
    public void push(PushPayload payload) {
        logger.info("umeng push one phone:{}", payload.getToken());
    }
}
