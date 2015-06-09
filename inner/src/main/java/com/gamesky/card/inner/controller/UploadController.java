package com.gamesky.card.inner.controller;

import com.gamesky.card.core.Keyable;
import com.gamesky.card.core.Marshaller;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created on 6/9/15.
 *
 * @Author lianghongbin
 */
@Controller
@RequestMapping(value = "/upload", produces = "text/plain;charset=UTF-8")
public class UploadController {

    @Autowired
    @Qualifier("uploadMarshaller")
    private Marshaller<Keyable, Serializable> marshaller;

    @ResponseBody
    @RequestMapping(value = "/sign")
    public String single(@RequestParam("uploadfile") CommonsMultipartFile file, HttpServletRequest request) {
        if (file.isEmpty()) {
            return "上传文件不能为空";
        }

        String type = file.getOriginalFilename().substring(
                file.getOriginalFilename().indexOf("."));// 取文件格式后缀名
        String filename = System.currentTimeMillis() + type;// 取当前时间戳作为文件名
        String path = request.getSession().getServletContext()
                .getRealPath("/upload/" + filename);// 存放位置
        File destFile = new File(path);
        try {
            // FileUtils.copyInputStreamToFile()这个方法里对IO进行了自动操作，不需要额外的再去关闭IO流
            FileUtils
                    .copyInputStreamToFile(file.getInputStream(), destFile);// 复制临时文件到指定目录下
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:upload_ok.jsp";
    }

    //多文件上传
    @RequestMapping(value = "/uploads.do")
    public String multipart(
            @RequestParam("uploadfile") CommonsMultipartFile[] files,
            HttpServletRequest request) {
        if (files != null) {
            for (CommonsMultipartFile file : files) {
                String type = file.getOriginalFilename().substring(
                        file.getOriginalFilename().indexOf("."));// 取文件格式后缀名
                String filename = System.currentTimeMillis() + type;// 取当前时间戳作为文件名
                String path = request.getSession().getServletContext()
                        .getRealPath("/upload/" + filename);// 存放位置
                File destFile = new File(path);
                try {
                    FileUtils.copyInputStreamToFile(file.getInputStream(),
                            destFile);// 复制临时文件到指定目录下
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return "redirect:upload_ok.jsp";
        } else {
            return "redirect:upload_error.jsp";
        }

    }
