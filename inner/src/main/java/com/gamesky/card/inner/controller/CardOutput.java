package com.gamesky.card.inner.controller;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.model.Card;
import com.gamesky.card.core.model.CardWithBLOBs;
import com.gamesky.card.core.model.CodeExample;
import com.gamesky.card.service.CardService;
import com.gamesky.card.service.CodeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created on 2015/7/22.
 *
 * @Author lianghongbin
 */
@Component
public class CardOutput {

    @Autowired
    private CardService cardService;
    @Autowired
    private CodeService codeService;

    public String out(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "card";

        String start = request.getParameter("start");
        String end = request.getParameter("end");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CodeExample codeExample = new CodeExample();
        CodeExample.Criteria criteria = codeExample.createCriteria();
        if (StringUtils.isNotBlank(start)) {
            try {
                Date date = sdf.parse(start);
                criteria.andAssignTimeGreaterThanOrEqualTo(date.getTime());
            } catch (ParseException ignored) {
            }
        }

        if (StringUtils.isNotBlank(end)) {
            try {
                Date date = sdf.parse(end);
                criteria.andAssignTimeLessThan(date.getTime());
            } catch (ParseException ignored) {
            }
        }

        //填充projects数据
        List<CardWithBLOBs> cards = cardService.findEnabledAll(new Page());
        for (Card card : cards) {
            criteria.andCardIdEqualTo(card.getId()).andAssignedEqualTo(true);
            int count = codeService.findCountByCondition(codeExample);
            card.setAssignTotal(count);
        }

        List<Map<String, Object>> list = createExcelRecord(cards);
        String columnNames[] = {"ID", "礼包名称", "游戏名称", "激活码数", "剩余数量", "领取数量", "是否淘号"};//列名
        String keys[] = {"id", "name", "gameName", "total", "surplus", "assignTotal", "tao"};//map中的key
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
        response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xls").getBytes()));
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

    private List<Map<String, Object>> createExcelRecord(List<CardWithBLOBs> cards) {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("sheetName", "sheet1");
        list.add(map);
        for (Card card : cards) {
            Map<String, Object> mapValue = new HashMap<>();
            mapValue.put("id", card.getId());
            mapValue.put("name", card.getName());
            mapValue.put("gameName", card.getGameName());
            mapValue.put("total", card.getTotal());
            mapValue.put("surplus", card.getTotal() - card.getAssignTotal());
            mapValue.put("assignTotal", card.getAssignTotal());
            mapValue.put("tao", card.getTao()==1 ? "淘号" : "未淘号");
            list.add(mapValue);
        }

        return list;
    }
}
