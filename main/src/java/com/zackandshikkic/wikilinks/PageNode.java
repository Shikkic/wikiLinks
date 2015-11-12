package com.zackandshikkic.wikilinks;

class PageNode {

    private String pageUrl;
    private int depth;
    private List<String> childenPageNodes; 
    
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

    public printNode() {
        System.out.println("page Url = " + pageUrl);
        System.out.println("depth = " + depth);
    }

}
