package fr.polytech.mtp.coviwad_news.services;

import fr.polytech.mtp.coviwad_news.models.News;

import java.util.ArrayList;

public interface ScrapNewsService {
    public abstract ArrayList<News> scrapNews();
}
