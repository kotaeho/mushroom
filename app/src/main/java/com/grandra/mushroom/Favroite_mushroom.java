package com.grandra.mushroom;

public class Favroite_mushroom {
    private String name;
    private String num;
    private String imageUrl;

    public Favroite_mushroom(String name,String num, String imageUrl){
        this.name = name;
        this.num = num;
        this.imageUrl = imageUrl;
    }

    public String getName(){
        return name;
    }

    public String getNum(){
        return num;
    }
    public String getImageUrl(){
        return imageUrl;
    }
}
