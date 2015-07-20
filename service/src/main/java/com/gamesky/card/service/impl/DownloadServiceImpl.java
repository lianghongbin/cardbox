package com.gamesky.card.service.impl;

import com.gamesky.card.core.model.Download;
import com.gamesky.card.core.model.DownloadExample;
import com.gamesky.card.dao.mapper.DownloadMapper;
import com.gamesky.card.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 7/20/15.
 *
 * @Author lianghongbin
 */
@Service
public class DownloadServiceImpl implements DownloadService {

    @Autowired
    private DownloadMapper downloadMapper;

    @Override
    public Download find(String platform) {
        DownloadExample downloadExample = new DownloadExample();
        downloadExample.createCriteria().andIdGreaterThan(0).andPlatformEqualTo(platform);
        List<Download> downloads = downloadMapper.selectByExample(downloadExample);
        if (downloads == null || downloads.size() == 0) {
            Download download = new Download();
            download.setCount(0);
            download.setPlatform(platform);
            download.setDownloadTime(System.currentTimeMillis());
            downloadMapper.insert(download);

            return download;
        }

        return downloads.get(0);
    }

    @Override
    public int update(Download download) {
        return downloadMapper.updateByPrimaryKeySelective(download);
    }

    @Override
    public int count(String platform) {
        Download download = find(platform);
        if (download == null) {
            return 0;
        }

        DownloadExample downloadExample = new DownloadExample();
        downloadExample.createCriteria().andPlatformEqualTo(platform);
        download.setCount(download.getCount() + 1);
        download.setDownloadTime(System.currentTimeMillis());
        downloadMapper.updateByExample(download, downloadExample);

        return download.getCount() + 1;
    }
}
