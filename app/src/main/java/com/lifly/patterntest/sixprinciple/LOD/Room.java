package com.lifly.patterntest.sixprinciple.LOD;

/**
 * 迪米特原则也叫最少知道法则，只与直接的朋友通信
 */
public class Room {
    public float area;
    public float price;

    public Room(float area, float price) {
        this.area = area;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Room{" +
                "area=" + area +
                ", price=" + price +
                '}';
    }
}
