package com.gamesky.card.inner.controller;

import com.gamesky.card.core.model.H5Game;
import com.gamesky.card.service.H5GameService;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * lianghongbin on 15/8/5.
 */
public class HttpGameHandler implements ContentHandler<String> {

    @Autowired
    private H5GameService h5GameService;
    private static final Logger logger = LoggerFactory.getLogger(HttpGameHandler.class);

    @Override
    public void handle(String s) {
        Gson gson = new Gson();
        H5DataWrapper h5DataWrapper = gson.fromJson(s, H5DataWrapper.class);
        List<Map<String, String>> hotGames = h5DataWrapper.getData().getHotgamelist();
        List<Map<String, String>> wxGames = h5DataWrapper.getData().getWxgamelist();
        Map<String, String> recommendGame = h5DataWrapper.getData().getRecommendgame();
        List<Map<String, String>> newGames = h5DataWrapper.getData().getNewgamelist();

        List<Map<String, String>> recommendGames = new ArrayList<>();
        recommendGames.add(recommendGame);
        save(hotGames, "hot");
        save(wxGames, "wx");
        save(recommendGames, "recommend");
        save(newGames, "new");
    }

    private void save(List<Map<String, String>> games, String type) {
        if (games == null || games.size() == 0) {
            return;
        }

        List<H5Game> h5Games = new ArrayList<>(games.size());
        for (Map<String, String> map : games) {
            H5Game h5Game = new H5Game();
            h5Game.setType(type);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                if (key.contains("_")) {
                    String[] words = StringUtils.split(key, "_");
                    StringBuilder name = new StringBuilder();
                    for (String word : words) {
                        if (name.length() == 0) {
                            name.append(word);
                        } else {
                            name.append(StringUtils.capitalize(word));
                        }
                    }

                    mapping(name.toString(), entry.getValue(), h5Game);
                } else {
                    mapping(entry.getKey(), entry.getValue(), h5Game);
                }
            }

            h5Games.add(h5Game);
        }

        h5GameService.save(h5Games);
    }

    private void mapping(String name, String value, H5Game h5Game) {
        Class h5GameClass = H5Game.class;
        Field[] fields = h5GameClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals(name)) {
                field.setAccessible(true); //设置些属性是可以访问的
                String type = field.getType().toString();//得到此属性的类型

                try {
                    if (type.endsWith("Boolean")) {
                        field.set(h5Game, Boolean.valueOf(value));
                    }else if(type.endsWith("int") || type.endsWith("Integer")){
                        field.set(h5Game, Integer.parseInt(value));
                    }else if (type.endsWith("String")){
                        field.set(h5Game, value);
                    }
                    else {
                        logger.error("not found the type {} in H5Game", type);
                    }
                } catch (IllegalAccessException e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }
}
