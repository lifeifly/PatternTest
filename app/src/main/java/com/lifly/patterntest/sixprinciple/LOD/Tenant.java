package com.lifly.patterntest.sixprinciple.LOD;

import java.util.List;

public class Tenant {
    public float roomArea;
    public float roomPrice;
    public static final float diffPrice = 100.0001f;
    public static final float diffArea = 0.00001f;
    //不仅与中介打交道，还频繁的和房间打交道,直接的朋友是中介
    public void rentRoom(Meditor meditor) {
        List<Room> rooms = meditor.getAllRooms();
        for (Room room : rooms) {
            if (isSuitable(room)) {
                System.out.println("租到房间啦" + room);
                break;
            }
        }
    }
    private boolean isSuitable(Room room){
        return Math.abs(room.price-roomPrice)<diffPrice&&Math.abs(room.area-roomArea)<diffArea;
    }
}
