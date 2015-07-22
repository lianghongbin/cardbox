package com.gamesky.card.inner.controller;

import com.gamesky.card.core.model.User;
import com.gamesky.card.core.model.UserExample;
import com.gamesky.card.service.UserService;
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
public class UserOutput {

    @Autowired
    private UserService userService;

    public String out(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "user";
        String start = request.getParameter("start");
        String end = request.getParameter("end");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if (StringUtils.isNotBlank(start)) {
            try {
                Date date = sdf.parse(start);
                criteria.andCreateTimeGreaterThanOrEqualTo(date.getTime());
            } catch (ParseException ignored) {
            }
        }

        if (StringUtils.isNotBlank(end)) {
            try {
                Date date = sdf.parse(end);
                criteria.andCreateTimeLessThan(date.getTime());
            } catch (ParseException ignored) {
            }
        }

        //填充projects数据
        List<User> projects = userService.findByCondition(userExample);
        List<Map<String, Object>> list = createExcelRecord(projects);
        String columnNames[] = {"手机", "积分", "注册时间", "最后登录时间"};//列名
        String keys[] = {"phone", "score", "createTime", "lastTime"};//map中的key
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

    private List<Map<String, Object>> createExcelRecord(List<User> users) {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("sheetName", "sheet1");
        list.add(map);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (User user : users) {
            Map<String, Object> mapValue = new HashMap<>();
            mapValue.put("phone", user.getPhone());
            mapValue.put("score", user.getScore());
            mapValue.put("createTime", sdf.format(new Date(user.getCreateTime())));
            mapValue.put("lastTime", sdf.format(new Date(user.getLastTime())));
            list.add(mapValue);
        }

        return list;
    }
}
