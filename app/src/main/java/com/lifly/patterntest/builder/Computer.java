package com.lifly.patterntest.builder;

/**
 * 计算机抽象类
 */
public abstract class Computer {
    protected String mBoard;
    protected String mDisplay;
    protected String mOS;
    
    protected Computer(){}
    
    //设置CPU核心数
    public void setBoard(String board){
        mBoard=board;
    }
    
    public void setDisplay(String display){
        mDisplay=display;
    }
    
    public abstract void setOS();

    @Override
    public String toString() {
        return "Computer{" +
                "mBoard='" + mBoard + '\'' +
                ", mDisplay='" + mDisplay + '\'' +
                ", mOS='" + mOS + '\'' +
                '}';
    }
}
