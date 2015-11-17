package com.zackandshikkic.wikilinks;

import java.io.IOException;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

class Application {
    
    public static void main(String args[]) throws IOException {
        LinkFetcher linkFetcher = new LinkFetcher();
        // Initialize Scanner
        //Scanner input = new Scanner(System.in);
        /*
        * Take a user input for starting node
        */
        System.out.println("Enter start node URL");
        String startNodeUrl = "French_Fries";

        /*
         * Take a user input for ending node
         */
        System.out.println("Enter end node URL");
        String endNodeUrl = "Hot_Dog";

        /*
         * Create Start Node
         */
        PageNode startNode = new PageNode(startNodeUrl, 0);

        /*
         * Create End Node
         */
        PageNode endNode = new PageNode(endNodeUrl, 0);

        startNode.printNode();
        endNode.printNode();
        List<String> children = linkFetcher.getUrlsOnPage(startNode.getPageUrl());
        System.out.println(children);

        /*
         * Declare ConcurrentLinkedQueue
         * Then instantiate the class with our beginning list of children urls
         */
        Queue<String> pageNodeUrlQueue = new ConcurrentLinkedQueue<>(children);

        // check base case that startNode !== endNode

        /*
         * While the Queue is not empty, perform actions
         */
        int i  = 0;
        while (!pageNodeUrlQueue.isEmpty()) {
            // Check to see if url == endPageNodeUrl
            String url = pageNodeUrlQueue.poll();
            PageNode  urlNode = new PageNode(url, 0);
            if (endNode.getPageUrl().equals(urlNode.getPageUrl())) {
                // We've found the end node stop
                break;
            }
            List<String> childrenUrl = linkFetcher.getUrlsOnPage(urlNode.getPageUrl());
            i++;
            if (i >= 100) {
                System.out.println(childrenUrl);
                i = 0;
            }
            pageNodeUrlQueue.addAll(childrenUrl);

        }

    }

}
