package com.gamesky.card.inner.controller;

import com.gamesky.card.core.model.Download;
import com.gamesky.card.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 2015/7/22.
 *
 * @Author lianghongbin
 */
@Component
public class DownloadOutput {

    @Autowired
    private DownloadService downloadService;

    public String out(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "download";
        //填充projects数据
        List<Download> projects = downloadService.findAll();
        List<Map<String, Object>> list = createExcelRecord(projects);
        String columnNames[] = {"平台", "下载次数"};//列名
        String keys[] = {"platform", "count"};//map中的key
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ExcelUtil.createWorkBook(list, keys, columnNames).write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName.concat(".xls")).getBytes()));
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            ServletOutputStream out = response.getOutputStream();
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (final IOException ignored) {

        } finally {
            try {
                if (bis != null)
                    bis.close();
                if (bos != null)
                    bos.close();
            }catch (IOException ignored){}
        }

        return null;
    }

    private List<Map<String, Object>> createExcelRecord(List<Download> downloads) {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("sheetName", "sheet1");
        list.add(map);
        for (Download download : downloads) {
            Map<String, Object> mapValue = new HashMap<>();
            mapValue.put("platform", download.getPlatform());
            mapValue.put("count", download.getCount());
            list.add(mapValue);
        }

        return list;
    }
}
