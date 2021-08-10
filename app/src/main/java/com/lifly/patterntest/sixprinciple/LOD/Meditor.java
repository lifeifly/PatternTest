package com.lifly.patterntest.sixprinciple.LOD;

import java.util.ArrayList;
import java.util.List;

public class Meditor {
    List<Room> mRooms = new ArrayList<>();

    public Meditor() {
        for (int i = 0; i < 5; i++) {
            mRooms.add(new Room(14 + i, (14 + i) * 150));
        }
    }

    public List<Room> getAllRooms() {
        return mRooms;
    }
}
