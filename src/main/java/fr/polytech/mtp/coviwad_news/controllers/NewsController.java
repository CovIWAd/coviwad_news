package fr.polytech.mtp.coviwad_news.controllers;

import fr.polytech.mtp.coviwad_news.models.News;
import fr.polytech.mtp.coviwad_news.services.ScrapNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    ScrapNewsService scrapService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/all")
    public ArrayList<News> getNews() {
        return scrapService.scrapNews();
    }
}
