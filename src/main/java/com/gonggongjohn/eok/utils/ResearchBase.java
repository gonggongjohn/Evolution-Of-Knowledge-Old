package com.gonggongjohn.eok.utils;

//研究模板类，每个研究个体的各属性在此定义
public class ResearchBase {
    //每个研究的ID，之后所有按钮，窗口间传递研究任务都会使用该ID
    public int id;
    //public int category;
    //public String name;
    //该研究对应的前置研究的ID（由于可能不止有一个前置因此为数组型）
    public int[] fatherResearch;
    //该研究对应的抽象空间中的点的坐标（目前为二维将来可能拓展至三维）
    public double dotX,dotY;

    //构造函数
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

    //通过ID从data中获取事先设定好的坐标信息（坐标数组存放在工具类中方便别的类调用）
    private void getCoordinate(int id){
        this.dotX = ResearchUtils.coordX[id];
        this.dotY = ResearchUtils.coordY[id];
    }
}