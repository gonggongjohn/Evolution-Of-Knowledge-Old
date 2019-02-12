package com.gonggongjohn.eok.utils;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class ResearchUtils {
    //总研究个数（初始化为-1是为了适应GUI显示）
    public static int researchCount = -1;
    //每个研究对应的横坐标（抽象空间中）
    public static double coordX[] = new double[1000];
    //每个研究对应的纵坐标（抽象空间中）
    public static double coordY[] = new double[1000];
    public static List<Integer> utilResearchesID = new ArrayList<Integer>();
    public static int father[] = new int[1000];

    /*private static double abs(double x){
        return x >= 0.0? x: -x;
    }*/

    //获取起点研究到工具研究的向量值
    public static double[] getVector(ResearchBase startResearch, ResearchBase targetResearch){
        double x = targetResearch.dotX - startResearch.dotX;
        double y = targetResearch.dotY - startResearch.dotY;
        return new double[]{x, y};
    }

    //获取起点研究通过工具研究能够到达离目标研究的最近距离（距离为0即为可直接到达目标研究）
    public static double getDistance(ResearchBase startResearch, double[] utilVector, ResearchBase targetResearch){
        double a = - utilVector[1];
        double b = utilVector[0];
        double c = - a * startResearch.dotX - b * startResearch.dotY;
        double numerator = abs(a * targetResearch.dotX + b * targetResearch.dotY + c);
        double denominator = Math.sqrt(a * a + b * b);
        //System.out.println(targetResearch.dotX);
        //System.out.println(targetResearch.dotY);
        //System.out.println(c);
        return numerator / denominator;
    }

    //获取起点研究通过工具研究能够到达离目标研究的最近点
    public static double[] calcMidPoint(ResearchBase startResearch, double[] utilVector, ResearchBase targetResearch){
        double xs = startResearch.dotX,ys = startResearch.dotY,xt = targetResearch.dotX,yt = targetResearch.dotY;
        double a = utilVector[0], b = utilVector[1];
        double x = (a*a*xs + b*b*xt + a*b*(ys-yt)) / (a*a+b*b);
        double y = (b*b*ys + a*a*yt + a*b*(xs-xt)) / (a*a+b*b);
        return new double[]{x, y};
    }

    public static double getTime(ResearchBase startResearch, ResearchBase targetResearch){
        double xs = startResearch.dotX,ys = startResearch.dotY,xt = targetResearch.dotX,yt = targetResearch.dotY;
        double t = Math.sqrt((xs - xt) * (xs - xt) + (ys - yt) * (ys -yt));
        return t * 10;
    }

}
