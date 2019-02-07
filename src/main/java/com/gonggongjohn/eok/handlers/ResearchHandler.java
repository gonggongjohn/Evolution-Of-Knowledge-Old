package com.gonggongjohn.eok.handlers;

import com.gonggongjohn.eok.utils.ResearchBase;

public class ResearchHandler {
    public static ResearchBase testResearch;
    public static void setupResearch(){
        testResearch = new ResearchBase(1, 0, "test");
    }
}
