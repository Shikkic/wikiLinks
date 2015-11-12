package com.zackandshikkic.wikilinks;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LinkFetcher {

    public List<String> getUrlsOnPage(String url) throws IOException {
        Document webPage = Jsoup.connect(url).get();
        Elements allLinks = webPage.select("a[href]");

        return extractInternalValidUrls(allLinks);
    }

    private List<String> extractInternalValidUrls(Elements allLinks) {
        List<String> childrenList = new ArrayList<String>();
        for (Element link: allLinks) {
            String child = parseAndValidate(link);
            if (child != null) {
                childrenList.add(child);
            }
        }
        return childrenList;
    }

    private String parseAndValidate(Element element) {
        String url = element.attr("href");
        boolean validUrl = url.startsWith("/wiki");

        // Can choose to exclude File: prefix later if we want
        if (validUrl) {
            return url;
        }

        return null;
    }

}
