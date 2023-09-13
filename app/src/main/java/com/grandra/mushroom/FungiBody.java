package com.grandra.mushroom;

import com.tickaroo.tikxml.annotation.Element;
import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

@Xml(name = "body")
public class FungiBody {
    @Element(name = "item")
    private FungiItem item;

    public void setItem(FungiItem item) {
        this.item = item;
    }
    public FungiItem getItem() {
        return item;
    }
}