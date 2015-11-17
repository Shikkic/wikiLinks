package com.zackandshikkic.wikilinks;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class LinkFetcher {

    public List<String> getUrlsOnPage(String url) throws IOException {
        Document webPage = Jsoup.connect(url).get();
        Elements allLinks = webPage.select("a[href]");

        return extractInternalValidUrls(allLinks);
    }

    private List<String> extractInternalValidUrls(Elements allLinks) {
        return allLinks.stream().map(element -> element.attr("href")).filter(this::isValid).map(url -> url.substring(6)).collect(Collectors.toList());
    }

    private boolean isValid(String url) {
        return url.startsWith("/wiki/") && !url.contains("File:");
    }
}
