package com.gamesky.card.inner.controller;

import com.gamesky.card.core.Keyable;
import com.gamesky.card.core.Marshaller;
import com.gamesky.card.core.exceptions.MarshalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * 图片上传控制器
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
    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    public String single(@RequestParam("uploadfile") CommonsMultipartFile file, HttpServletRequest request) {
        if (file.isEmpty()) {
            return "上传文件不能为空";
        }

        String type = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));    // 取文件格式后缀名
        final String fileName = System.currentTimeMillis() + type;  // 取当前时间戳作为文件名

        try {
            marshaller.marshal(new Keyable() {
                @Override
                public String k() {
                    return fileName;
                }
                public long expire() {return 0;}
            }, file.getBytes());
        } catch (MarshalException e) {
            logger.error("上传文件出错：{}", e);
            return "上传文件出错!";
        }

        return "";
    }

    //多文件上传
    @ResponseBody
    @RequestMapping(value = "/multipart")
    public String multipart(@RequestParam("uploadfile") CommonsMultipartFile[] files, HttpServletRequest request) {
        if (files == null) {
            return "上传文件不能为空";
        }

        for (CommonsMultipartFile file : files) {
            String type = file.getOriginalFilename().substring(
                    file.getOriginalFilename().indexOf("."));// 取文件格式后缀名
            final String fileName = System.currentTimeMillis() + type;// 取当前时间戳作为文件名
            try {
                marshaller.marshal(new Keyable() {
                    @Override
                    public String k() {
                        return fileName;
                    }
                    public long expire() {return 0;}
                }, file.getBytes());
            } catch (MarshalException e) {
                logger.error("上传文件出错：{}", e);
            }
        }

        return "";
    }
}