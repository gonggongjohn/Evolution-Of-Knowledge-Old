package com.gonggongjohn.eok.handlers;

import com.gonggongjohn.eok.utils.ResearchBase;

public class ResearchHandler {
    public static ResearchBase testResearch;
    public static int[][] father = new int[5000][10];
    public static void setupResearch(){
        testResearch = new ResearchBase(1, 0, "test").setFatherResearch(father[1]);
    }
}
