package com.gamesky.card.core.lock;

/**
 * Created on 6/8/15.
 *
 * @Author lianghongbin
 */
public interface Lockable {

    /**
     * 锁定的KEY
     * @return 锁定的Key
     */
    public String key();

    /**
     * 在使用redis时，设置缓存时长
     * @return 有效时长
     */
    public long expire();    //毫秒
}
