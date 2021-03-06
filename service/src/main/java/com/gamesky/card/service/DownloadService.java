package com.gamesky.card.service;

import com.gamesky.card.core.model.Download;

import java.util.List;

/**
 * Created on 7/20/15.
 *
 * @Author lianghongbin
 */
public interface DownloadService {

    Download find(String platform);

    int update(Download download);

    int count(String platform);

    List<Download> findAll();
}
