package com.hangman.service;

import com.hangman.elements.GamesSummary;
import java.util.List;

public class MonitorService {

    GamesSummary gamesSummary = GamesSummary.getInstance();

    public String createGamesReport() {

        List<String> summaries = gamesSummary.getAllSummries();

        String newGameResponseData = "<games>";
        for (String s : summaries) {
            newGameResponseData += "<game>" + s + "</game>";
        }
        newGameResponseData += "</games>";

        return newGameResponseData;
    }
}
