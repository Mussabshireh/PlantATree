package com.treeshopapp.treeshop;

public class Tree {
    private String NameOfTree;
    private int price;
    private int ImgURL;

    public Tree(String n, int p, int a){
        this.NameOfTree=n;
        this.price=p;
        this.ImgURL=a;
    }

    public String getTheNameOfTree(){
        return this.NameOfTree;
    }

    public int getPrice(){
        return this.price;
    }

    public int getImgURL(){
        return this.ImgURL;
    }

}
