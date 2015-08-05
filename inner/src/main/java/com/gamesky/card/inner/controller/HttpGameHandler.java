package com.gamesky.card.inner.controller;

import com.gamesky.card.core.model.H5Game;
import com.gamesky.card.service.H5GameService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * lianghongbin on 15/8/5.
 */
public class HttpGameHandler implements ContentHandler<String> {

    @Autowired
    private H5GameService h5GameService;

    @Override
    public void handle(String s) {
        Gson gson = new Gson();
        H5DataWrapper h5DataWrapper = gson.fromJson(s, H5DataWrapper.class);
        List<H5Game> games = h5DataWrapper.getData().getHotgamelist();

        h5GameService.save(games);
    }
}
