package fr.polytech.mtp.coviwad_news.models;

public class News {
    private String title;
    private String subtitle;
    private String article;
    private String link;

    public News(String title, String subtitle, String article, String link) {
        this.title = title;
        this.subtitle = subtitle;
        this.article = article;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
