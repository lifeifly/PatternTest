package com.lifly.patterntest.builder;

public abstract class Builder {
    public abstract void buildBoard(String board);
    public abstract void buildDisplay(String display);
    public abstract void buildOS();

    public abstract Computer create();
}
