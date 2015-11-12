package com.zackandshikkic.wikilinks;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class LinkFetcher {

    public List<String> getUrlsOnPage(String url) throws IOException {
        Document webPage = Jsoup.connect(url).get();

        Elements allLinks = webPage.select("a[href]");

        System.out.println(allLinks);

        return null;
    }
}
