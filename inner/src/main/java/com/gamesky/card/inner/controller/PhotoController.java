package com.gamesky.card.inner.controller;

import com.gamesky.card.core.Constants;
import com.gamesky.card.core.Keyable;
import com.gamesky.card.core.Marshaller;
import com.gamesky.card.core.Page;
import com.gamesky.card.core.model.Card;
import com.gamesky.card.core.model.CardWithBLOBs;
import com.gamesky.card.core.model.Game;
import com.gamesky.card.core.model.Photo;
import com.gamesky.card.service.CardService;
import com.gamesky.card.service.GameService;
import com.gamesky.card.service.PhotoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created on 6/25/15.
 *
 * @Author lianghongbin
 */
@Controller
@RequestMapping(value = "/photo", produces = "text/plain;charset=UTF-8")
public class PhotoController {

    @Autowired
    private PhotoService photoService;
    @Autowired
    private GameService gameService;
    @Autowired
    private CardService cardService;
    private static final Logger logger = LoggerFactory.getLogger(PhotoController.class);

    @Autowired
    @Qualifier("uploadMarshaller")
    private Marshaller<Keyable, Serializable> marshaller;

    @RequestMapping("/game")
    public ModelAndView game(int gameId, Page page) {
        if (page.getPagesize() == Integer.MAX_VALUE) {
            page.setPagesize(10);
        }

        Game game = gameService.find(gameId);
        List<Photo> photos = photoService.findByGame(gameId, page);
        PaginationData paginationData = new PaginationData(page, photos);
        ModelAndView modelAndView = new ModelAndView("photo/game");
        modelAndView.addObject("paginationData", paginationData);
        modelAndView.addObject("game", game);

        return modelAndView;
    }

    @RequestMapping("/card")
    public ModelAndView card(int cardId, Page page) {
        if (page.getPagesize() == Integer.MAX_VALUE) {
            page.setPagesize(10);
        }

        Card card = cardService.find(cardId);
        List<Photo> photos = photoService.findByCard(cardId, page);
        PaginationData paginationData = new PaginationData(page, photos);
        ModelAndView modelAndView = new ModelAndView("photo/card");
        modelAndView.addObject("paginationData", paginationData);
        modelAndView.addObject("card", card);

        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/remove")
    public String remove(int id) {
        int result = photoService.remove(id);
        return String.valueOf(result);
    }

    @RequestMapping("/add")
    public ModelAndView add(int id, String type) {
        Object item;
        if (type.equalsIgnoreCase("game")) {
            item = gameService.find(id);
        } else {
            item = cardService.find(id);
        }

        ModelAndView modelAndView = new ModelAndView("photo/add");
        modelAndView.addObject("item", item);
        modelAndView.addObject("type", type);

        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(HttpServletRequest request) {
        String responseStr = "";
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        File dir = new File(request.getSession().getServletContext().getRealPath(Constants.UPLOAD_DIR));
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                return "创建上传文件夹失败";
            }
        }

        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        String fileName;
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            // 上传文件
            MultipartFile mf = entity.getValue();
            fileName = mf.getOriginalFilename();
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            // 重命名文件
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
            File uploadFile = new File(dir, newFileName);

            try {
                marshaller.marshal(uploadFile::getAbsolutePath, mf.getBytes());
                responseStr = "上传成功";

                int id = Integer.parseInt(request.getParameter("id"));
                String type = request.getParameter("type");
                Photo photo = new Photo();
                photo.setItemId(id);
                photo.setUrl(Constants.PHOTO_URL_PREFIX + "/" + Constants.UPLOAD_DIR + "/" + newFileName);
                photo.setType(type);

                photoService.save(photo);
            } catch (Exception e) {
                responseStr = "上传失败";
                logger.error("文件{}上传失败", fileName);
                logger.error(e.getMessage());
            }
        }

        return responseStr;
    }

    @ResponseBody
    @RequestMapping(value = "/icon", method = RequestMethod.POST)
    public String icon(HttpServletRequest request) {
        String responseStr;
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        File dir = new File(request.getSession().getServletContext().getRealPath(Constants.UPLOAD_DIR));
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                return "创建上传文件夹失败";
            }
        }

        MultipartFile mf = multipartRequest.getFile("icon");
        String fileName = mf.getOriginalFilename();
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        // 重命名文件
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
        File uploadFile = new File(dir, newFileName);

        try {
            marshaller.marshal(uploadFile::getAbsolutePath, mf.getBytes());

            int id = Integer.parseInt(request.getParameter("id"));
            String type = request.getParameter("type");
            String url = Constants.PHOTO_URL_PREFIX + "/" + Constants.UPLOAD_DIR + "/" + newFileName;
            if (type.equalsIgnoreCase("game")) {
                Game game = new Game();
                game.setId(id);
                game.setIcon(url);
                gameService.update(game);
            } else {
                CardWithBLOBs card = new CardWithBLOBs();
                card.setId(id);
                card.setIcon(url);
                cardService.update(card);
            }

            responseStr = "上传成功";
        } catch (Exception e) {
            responseStr = "上传失败";
            logger.error("文件{}上传失败", fileName);
            logger.error(e.getMessage());
        }


        return responseStr;
    }
}
