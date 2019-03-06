package com.example.noahjarvis.ParkingFinder;

public class ParkingLot {
    String name;
    int cap;
    int cur;

    public ParkingLot(int name){
        this.name = "Lot " + name;
        cap = 100;
        cur = 13;
    }

    @Override
    public String toString() {
        return name + "\n" + cur + "/" + cap;
    }
}