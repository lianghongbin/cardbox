package com.gamesky.card.web.controller;

import com.gamesky.card.core.Keyable;
import com.gamesky.card.core.Marshaller;
import com.gamesky.card.core.model.Photo;
import com.gamesky.card.service.CardService;
import com.gamesky.card.service.GameService;
import com.gamesky.card.service.PhotoService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created on 6/25/15.
 *
 * @Author lianghongbin
 */
@RequestMapping(value = "/photo", produces = "text/plain;charset=UTF-8")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    private String uploadDir;
    private String host;
    private Marshaller<Keyable, Serializable> marshaller;
    private static final Logger logger = LoggerFactory.getLogger(PhotoController.class);

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setMarshaller(Marshaller<Keyable, Serializable> marshaller) {
        this.marshaller = marshaller;
    }

    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String dateDir = dateFormat.format(new Date());
        File dir = new File(uploadDir, dateDir);

        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                logger.error("创建上传文件夹失败:{}", dir.getAbsolutePath());
                return "";
            }
        }

        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        String fileName;
        List<String> photos = new ArrayList<>();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            // 上传文件
            MultipartFile mf = entity.getValue();
            fileName = mf.getOriginalFilename();
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            // 重命名文件
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String newFileName = new Random().nextInt(1000) + df.format(new Date()) + "." + fileExt;
            final File uploadFile = new File(dir, newFileName);

            try {
                marshaller.marshal(new Keyable() {
                    @Override
                    public String k() {
                        return uploadFile.getAbsolutePath();
                    }
                }, mf.getBytes());

                int id = Integer.parseInt(request.getParameter("id"));
                String type = request.getParameter("type");
                Photo photo = new Photo();
                photo.setItemId(id);
                String url = host + "/" + dateDir + "/" + newFileName;
                photo.setUrl(url);
                photo.setType(type);

                photoService.save(photo);

                photos.add(url);
            } catch (Exception e) {
                logger.error("文件{}上传失败", fileName);
                logger.error(e.getMessage());
            }
        }

        Gson gson = new Gson();
        return gson.toJson(photos);
    }

    //单图片上传
    @ResponseBody
    @RequestMapping(value = "/single", method = RequestMethod.POST)
    public String single(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String dateDir = dateFormat.format(new Date());
        File dir = new File(uploadDir, dateDir);

        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                logger.error("创建上传文件夹失败:{}", dir.getAbsolutePath());
                return "";
            }
        }

        MultipartFile mf = multipartRequest.getFile("file");
        String fileName = mf.getOriginalFilename();
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        // 重命名文件
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String newFileName = new Random().nextInt(1000) + df.format(new Date()) + "." + fileExt;
        final File uploadFile = new File(dir, newFileName);

        try {
            marshaller.marshal(new Keyable() {
                @Override
                public String k() {
                    return uploadFile.getAbsolutePath();
                }
            }, mf.getBytes());

            return host + "/" +dateDir + "/" + newFileName;
        } catch (Exception e) {
            logger.error("文件{}上传失败", fileName);
            logger.error(e.getMessage());
        }

        return "";
    }
}
