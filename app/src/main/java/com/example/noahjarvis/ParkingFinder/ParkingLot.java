package com.example.noahjarvis.ParkingFinder;
public class ParkingLot {
    enum Type{
        FACULTY,GENERAL,VISITOR
    }

    private String  name;       //Name of the lot
    private String  description;//Long description of Lot
    private int     capacity;   //Total Capacity
    private int     current;    //Current Capacity
    private Type    type;       //type of lot


    public ParkingLot(int name){
        this.name = "Lot " + name;
        capacity = 100 + (int)(Math.random()*100);
        current = (int)(Math.random()*100);
    }

    public ParkingLot(){
        updateLot();
    }

    //updates information for lot
    public void updateLot(){

    }

    //returns current capacity as a percentage
    public int getPercent(){
        return (int)(((double)current/capacity)*100);
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCurrent() {
        return current;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return current + "/" + capacity;
    }
}