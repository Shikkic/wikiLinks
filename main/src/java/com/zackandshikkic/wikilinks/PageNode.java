package com.zackandshikkic.wikilinks;

import java.util.List;

class PageNode {

    private String pageUrl;
    private int depth;
    private List<String> childrenPageNodes;
    
    public PageNode(String url, int parentDepth) {
        this.pageUrl = url;
        this.depth = parentDepth + 1;
    }
        
    public void setChildrenPageNodes(List<String> childrenPageNodes) {
        this.childrenPageNodes = childrenPageNodes;
    }

    public List<String> getChildrenPageNodes() {
        return this.childrenPageNodes;
    }

    public void printNode() {
        System.out.println("page Url = " + pageUrl);
        System.out.println("depth = " + depth);
    }

}
