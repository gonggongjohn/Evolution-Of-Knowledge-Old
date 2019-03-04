package com.gonggongjohn.eok.utils;

import java.util.List;

public class ResearchBase {
    public int id;
    public List<Integer> fatherResearch;
    public List<Integer> fatherUtils;

    public ResearchBase(int id) {
        this.id = id;
    }
}