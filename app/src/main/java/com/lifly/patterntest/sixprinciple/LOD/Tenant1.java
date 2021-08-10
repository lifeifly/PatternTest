package com.lifly.patterntest.sixprinciple.LOD;

public class Tenant1 {
    public float roomArea;
    public float roomPrice;
    public static final float diffPrice = 100.0001f;
    public static final float diffArea = 0.00001f;

    //不仅与中介打交道，还频繁的和房间打交道,直接的朋友是中介
    public void rentRoom(Meditor1 meditor) {
        System.out.println("租到房子啦" + meditor.rentOut(roomArea, roomPrice));
    }
}
