package com.gonggongjohn.eok.utils;

public class ResearchUtils {
    private static double abs(double x){
        return x >= 0.0? x: -x;
    }

    public static double[] getVector(ResearchBase startResearch, ResearchBase targetResearch){
        double x = targetResearch.dotX - startResearch.dotX;
        double y = targetResearch.dotY - startResearch.dotY;
        return new double[]{x, y};
    }

    public static double getDistance(ResearchBase startResearch, double[] utilVector, ResearchBase targetResearch){
        double numerator = abs((targetResearch.dotX - startResearch.dotX) * utilVector[0] + (targetResearch.dotY - startResearch.dotY) * utilVector[1]);
        double denominator = Math.sqrt(utilVector[0] * utilVector[0] + utilVector[1] * utilVector[1]);
        return numerator / denominator;
    }

    public static double[] calcMidPoint(ResearchBase startResearch, double[] utilVector, ResearchBase targetResearch){
        double xs = startResearch.dotX,ys = startResearch.dotY,xt = targetResearch.dotX,yt = targetResearch.dotY;
        double a = utilVector[0], b = utilVector[1];
        double x = (a*a*xs + b*b*xt + a*b*(ys-yt)) / (a*a+b*b);
        double y = (b*b*ys + a*a*yt + a*b*(xs-xt)) / (a*a+b*b);
        return new double[]{x, y};
    }


}
