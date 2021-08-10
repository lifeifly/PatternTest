package com.lifly.patterntest.builder;

public class MacbookBuidler extends Builder {
    private Computer computer = new Macbook();

    @Override
    public void buildBoard(String board) {
        computer.setBoard(board);
    }

    @Override
    public void buildDisplay(String display) {
        computer.setDisplay(display);
    }

    @Override
    public void buildOS() {
        computer.setOS();
    }

    @Override
    public Computer create() {
        return computer;
    }
}
