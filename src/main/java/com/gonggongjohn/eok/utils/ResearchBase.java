package com.gonggongjohn.eok.utils;

public class ResearchBase {
    public int id;
    public int category;
    public String name;
    public int[] fatherResearch;
    public double dotX,dotY;

    public ResearchBase(int id, int category, String name) {
        this.id = id;
        this.category = category;
        this.name = name;
    }

    public ResearchBase setFatherResearch(int[] fatherResearch){
        this.fatherResearch = fatherResearch;
        return this;
    }

    public ResearchBase setCoordinate(double dotX, double dotY){
        this.dotX = dotX;
        this.dotY = dotY;
        return this;
    }
}