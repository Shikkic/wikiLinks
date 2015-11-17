package com.zackandshikkic.wikilinks;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.stream.Collectors;

public class LinkFetcher {

    public List<String> getUrlsOnPage(String url) {
        boolean success = false;
        int count = 0;
        Elements allLinks = null;

        while (count < 3) {
            try {
                Document webPage = Jsoup.connect(url).get();
                allLinks = webPage.select("a[href]");
                success = true;
                break;
            } catch (IOException e){
                e.printStackTrace();
            }
            count++;
        }

        if (!success) {
            // Test
            throw new NullPointerException();
        }

        return extractInternalValidUrls(allLinks);
    }

    private List<String> extractInternalValidUrls(Elements allLinks) {
        return allLinks.stream().map(element -> element.attr("href")).filter(this::isValid).map(url -> url.substring(6)).collect(Collectors.toList());
    }

    private boolean isValid(String url) {
        return url.startsWith("/wiki/") && !url.contains("File:");
    }
}
