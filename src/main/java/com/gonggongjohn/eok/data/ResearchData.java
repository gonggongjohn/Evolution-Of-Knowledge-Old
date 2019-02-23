package com.gonggongjohn.eok.data;

import com.gonggongjohn.eok.utils.ResearchUtils;

public class ResearchData {

    private static void setCoordinate(int id, double x, double y){
        ResearchUtils.coordX[id] = x;
        ResearchUtils.coordY[id] = y;
        ResearchUtils.researchCount ++;
    }

    private static void setFather(int id, int fatherID){
        ResearchUtils.father[id] = fatherID;
    }

    public static void setup(){
        ResearchUtils.utilResearchesID.add(1);
        ResearchUtils.utilResearchesID.add(3);
        ResearchUtils.utilResearchesID.add(4);
        ResearchUtils.utilResearchesID.add(5);
        ResearchUtils.unlockedResearchID.add(0);
        ResearchUtils.unlockedResearchID.add(1);
        ResearchUtils.unlockedResearchID.add(3);
        setCoordinate(0,0,0);
        setCoordinate(1,1,0);
        setCoordinate(2,2,0);
        setCoordinate(3,0.5,1);
        setCoordinate(4,0,1);
        setCoordinate(5,1,0.5);
        setCoordinate(6,1,1);
        setCoordinate(7,2,1);
        setFather(0,0);
        setFather(1,0);
        setFather(2,0);
        setFather(3,1);
        setFather(4,3);
        setFather(5,3);
        setFather(6,5);
        setFather(7,6);
    }
}
