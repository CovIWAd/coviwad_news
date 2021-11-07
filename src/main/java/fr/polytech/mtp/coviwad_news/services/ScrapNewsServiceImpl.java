package fr.polytech.mtp.coviwad_news.services;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;
import fr.polytech.mtp.coviwad_news.models.News;
import org.springframework.stereotype.Service;
import org.w3c.dom.NamedNodeMap;

import java.io.IOException;
import java.util.*;

@Service
public class ScrapNewsServiceImpl implements ScrapNewsService {

    public ArrayList<News> scrapNews (){
        WebClient webClient = new WebClient(BrowserVersion.FIREFOX);

        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setPrintContentOnFailingStatusCode(false);

        ArrayList<News> news = new ArrayList<>();

        try {
            HtmlPage page = webClient.getPage("https://www.santepubliquefrance.fr/dossiers/coronavirus-covid-19");

            List<?> anchors = page.getByXPath("//a[@class='card__inner']");

            List<?> divs = page.getByXPath("//div[@class='card__content edito']");

            for (Object div : divs) {
                HtmlDivision ch = (HtmlDivision) div;
                News n = new News("","","","");
                news.add(n);
                for (DomNode i : ch.getChildNodes()){
                    NamedNodeMap node = i.getAttributes();
                    int size = i.getChildNodes().getLength();
                    if(node.item(0) != null){
                        if(Objects.equals(node.item(0).getNodeValue(), "subtitle") && size > 0){
                            n.setSubtitle(i.getChildNodes().item(0).getNodeValue().trim().replace("\n",""));
                        } else if (Objects.equals(node.item(0).getNodeValue(), "h4") && size > 0){
                            n.setTitle(i.getChildNodes().item(0).getNodeValue().trim().replace("\n",""));
                        } else if (Objects.equals(node.item(0).getNodeValue(),"eztext-field") && size > 0){
                            n.setArticle(i.getChildNodes().item(0).getNodeValue().trim().replace("\n",""));
                        }
                    }
                }
            }

            if(news.size() == anchors.size()){
                for (int index = 0; index < anchors.size(); index++) {
                    HtmlAnchor link = (HtmlAnchor) anchors.get(index);

                    String newsLink = "https://www.santepubliquefrance.fr"+link.getHrefAttribute();
                    news.get(index).setLink(newsLink);
                }
            } else {
                System.out.println("C'est la merde");
            }



            webClient.getCurrentWindow().getJobManager().removeAllJobs();
            webClient.close();

        } catch (IOException e) {
            System.out.println("An error occurred: " + e);
        }

        return news;
    }
}
