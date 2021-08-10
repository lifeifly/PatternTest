package com.lifly.patterntest.sixprinciple.LOD;

import java.util.ArrayList;
import java.util.List;

public class Meditor1 {
    List<Room> mRooms = new ArrayList<>();

    public Meditor1() {
        for (int i = 0; i < 5; i++) {
            mRooms.add(new Room(14 + i, (14 + i) * 150));
        }
    }

    public List<Room> getAllRooms() {
        return mRooms;
    }


    public Room rentOut(float area, float price) {
        for (Room room : mRooms) {
            if (isSuitable(room, area, price)) {
                return room;
            }
        }
        return null;
    }

    private boolean isSuitable(Room room, float roomArea, float roomPrice) {
        return Math.abs(room.price - roomPrice) < Tenant1.diffPrice && Math.abs(room.area - roomArea) < Tenant1.diffArea;
    }
}
