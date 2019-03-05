package com.gonggongjohn.eok.data;

import java.util.ArrayList;

public class ResearchData {
    public static ArrayList<ArrayList<Integer>> researchFathers = new ArrayList<ArrayList<Integer>>();
    public static ArrayList<Integer> utilResearches = new ArrayList<>();

    public static void setFathers(int researchID, int fatherID){
        if(researchFathers.size() -1 <= researchID){
            ArrayList<Integer> father = new ArrayList<Integer>();
            father.add(fatherID);
            researchFathers.add(father);
        }
        else{
            ArrayList<Integer> father = researchFathers.get(researchID);
            father.add(fatherID);
            researchFathers.add(researchID, father);
        }
    }

    public static void setUtil(int id){
        utilResearches.add(id);
    }

    public static void initRsearch(){
        setFathers(0, 0);
        setFathers(1, 0);
        setFathers(2, 1);
        setUtil(1);
    }

}
