package com.zackandshikkic.wikilinks;

import java.io.IOException;
import java.util.Scanner;

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
        String endNodeUrl = "Ketchup";

        PageNode startNode = new PageNode(startNodeUrl, 0);
        PageNode endNode = new PageNode(endNodeUrl, 0);

        startNode.printNode();
        endNode.printNode();
        linkFetcher.getUrlsOnPage(startNode.getPageUrl());
    }

}
