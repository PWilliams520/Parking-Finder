package com.example.noahjarvis.ParkingFinder;

import java.io.Serializable;

public class ParkingLot implements Serializable {
    enum Type{
        FACULTY,GENERAL,VISITOR
    }

    private String  name;       //Name of the lot
    private String  description;//Long description of Lot
    private int     capacity;   //Total Capacity
    private int     current;    //Current Capacity
    private Type    type;       //type of lot
    private double  latitude;   //latitude of lot
    private double  longitude;  //longitude of lot


    public ParkingLot(int name){
        this.name   = "Lot " + name;
        description = "Sample Text the will describe lot location";
        capacity    = 100 + (int)(Math.random()*100);
        current     = (int)(Math.random()*100);
        latitude    =  41.403133;
        longitude   =  2.1737241;
        int temp = (int)(Math.random()*3);
        if(temp == 0){
            this.type = Type.FACULTY;
        }
        else if(temp == 1){
            this.type = Type.GENERAL;
        }
        else{
            this.type = Type.VISITOR;
        }
    }

    public ParkingLot(String name, String description, int capacity, Type type, double latitude, double longitude){
        this.name = name;
        this.description = description;
        this.capacity = capacity;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
        updateLot();
    }

    //updates information for lot
    //set value to -1 if lot cannot be updated
    public void updateLot(){
        //TODO update information from firebase
        current = (int)(Math.random()*capacity) -5;

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

    public double getLatitude() { return latitude;}

    public double getLongitude() { return  longitude;}

    @Override
    public String toString() {
        return current + "/" + capacity;
    }
}