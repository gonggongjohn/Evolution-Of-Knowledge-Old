package com.gonggongjohn.eok.data;

import com.gonggongjohn.eok.utils.ResearchUtils;

public class ResearchData {

    private static void setCoordinate(int id, float x, float y){
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
        ResearchUtils.unlockedResearchID.add(1);
        setCoordinate(0,0,0);
        setCoordinate(1,1,0);
        setCoordinate(2,2,0);
        setCoordinate(3,3,1);
        setCoordinate(4,3,3);
        setFather(0,0);
        setFather(1,0);
        setFather(2,0);
        setFather(3,0);
        setFather(4,2);
    }
}
