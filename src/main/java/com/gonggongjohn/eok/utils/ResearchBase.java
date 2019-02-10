package com.gonggongjohn.eok.utils;


public class ResearchBase {
    public int id;
    //public int category;
    //public String name;
    public int[] fatherResearch;
    public double dotX,dotY;

    public ResearchBase(int id) {
        this.id = id;
        //this.category = category;
        //this.name = name;
        getCoordinate(id);
    }

    public ResearchBase setFatherResearch(int[] fatherResearch){
        this.fatherResearch = fatherResearch;
        return this;
    }

    /*public ResearchBase setCoordinate(double dotX, double dotY){
        this.dotX = dotX;
        this.dotY = dotY;
        return this;
    }*/

    private void getCoordinate(int id){
        this.dotX = ResearchUtils.coordX[id];
        this.dotY = ResearchUtils.coordY[id];
    }
}