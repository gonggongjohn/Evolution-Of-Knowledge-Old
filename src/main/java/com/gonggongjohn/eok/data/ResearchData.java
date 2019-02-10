package com.gonggongjohn.eok.data;

import com.gonggongjohn.eok.utils.ResearchUtils;

public class ResearchData {

    private static void setCoordinate(int id, float x, float y){
        ResearchUtils.coordX[id] = x;
        ResearchUtils.coordY[id] = y;
    }

    public static void setup(){
        setCoordinate(0,1,0);
        setCoordinate(1,2,0);
        setCoordinate(2,2,2);
        setCoordinate(3,3,3);
    }
}
