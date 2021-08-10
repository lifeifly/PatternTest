package com.lifly.patterntest.sixprinciple.LSP;
//建立试图抽象，测量试图的宽高为公用代码，绘制实现交给具体的子类
public abstract class View {
    public abstract void draw();
    public void measure(int width,int height){

    }
}
