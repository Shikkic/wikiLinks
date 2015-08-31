import java.util.Scanner;
import java.net.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class LinkedList {

    Node root;

    public LinkedList() {
        root = null;
    }

    // Inserting node into the linked list
    public void insert(Node newNode) {
        Node pointer = root;
        if (root == null) {
            System.out.println("Root was null");
            root = newNode; 
        }
        else if (pointer.next == null) {
            System.out.println("Root has null next");
            pointer.next = newNode;
        } else {
            while (pointer.next != null) {
                pointer = pointer.next;
            }
            pointer.next = newNode;
        }
    }

    public void print() {
        Node pointer = root;
        if (root == null) {
            System.out.println("No nodes");
        } else {
            while ( pointer.next != null) {
                System.out.println(pointer.url);
                pointer = pointer.next;
            }
        }
    }

} 

class Node {
    
    // Root URL
    String url;
    // Child(ren) ( Maybe a linked List)
    LinkedList children;
    Node next;

    // Public Constructor
    public Node(String newUrl) {
        url = newUrl;
        next = null;
        children = new LinkedList();
    }

    // Standard Get Request
    public String getRequest() throws Exception {
        String inputLine;
        URL link = new URL(url);
        URLConnection yc = link.openConnection();
        BufferedReader in = new BufferedReader(
            new InputStreamReader(
            yc.getInputStream()));

        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) 
            response.append(inputLine);
        in.close();
        
        return response.toString();
    }

    // Strips all href links and appends to LinkedList of Children
    // Figure out how to access chars in String
    public void strip(String text) {
        int i = 0;
        int z = 0;
        String html = text;
        while( i < html.length() - 1) {
            if (html.charAt(i) == 'h' && (html.length() -i > 12)) {
                //System.out.println("h found at "+ i);
                // If I find an H is the next 11 characters = to href="/wiki
                System.out.println(html.length() - i); 
                String word = html.substring(i, i+11);
                System.out.println("WORD="+word);
                // TODO CREATE BETTER TEST
                if (word.equals("href=\"/wiki")) {
                    //System.out.println("WORD HAS BEEN FOUND = "+word);
                    String result = "/wiki";
                    StringBuffer link = new StringBuffer();
                    z = i + 7;
                    while( html.charAt(z) != '"') {
                        link.append(html.charAt(z));
                        z++;
                    }
                    // Create Node from result
                    Node linkNode = new Node(link.toString());
                    // Add to queue
                    children.insert(linkNode);
                }
                // If true add the next n chars till " to a string and return
            } 
            i++;
        } 
    }

    public static void main(String [] args) throws Exception {
        Scanner keyboard = new Scanner(System.in);
        //String input = keyboard.nextLine();
        Node wiki = new Node("https://en.wikipedia.org/wiki/French_fries");
        LinkedList list = new LinkedList();
        String results = wiki.getRequest();
        wiki.strip(results);
        //wiki.children.print();
        Node pointer = wiki.children.root;
        //enqueue children
        // TODO Fix infinite loop of traversing and enqueing children
        while (pointer != null) {
            Node linkNode = new Node(pointer.url);
            list.insert(linkNode);
            pointer = pointer.next;
        }
        list.print();
        pointer = list.root;
        while (pointer != null) {
            
        }
        
        System.out.println("After printing");
        //System.out.println(results);
        //System.out.println(wiki.url);
    }

}
