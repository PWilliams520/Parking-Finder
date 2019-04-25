package com.example.noahjarvis.ParkingFinder;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class ParkingLot implements Serializable, Parcelable {
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

    public ParkingLot(Parcel in){
        this.name = in.readString();
        this.description = in.readString();
        this.capacity = in.readInt();
        this.current = in.readInt();
        this.type = Type.valueOf(in.readString());
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
    }

    //updates information for lot
    //set value to -1 if lot cannot be updated
    public void updateLot(Long current){
        //TODO update information from firebase
        //current = (int)(Math.random()*capacity) -5;
        this.current = current.intValue();

    }

    public void updateLot(){current = -1;}

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

    @Override
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        //    private String  name;       //Name of the lot
        //    private String  description;//Long description of Lot
        //    private int     capacity;   //Total Capacity
        //    private int     current;    //Current Capacity
        //    private Type    type;       //type of lot
        //    private double  latitude;   //latitude of lot
        //    private double  longitude;  //longitude of lot
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeInt(capacity);
        parcel.writeInt(current);
        parcel.writeString(type.name());
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
    }

    public static final Creator<ParkingLot> CREATOR = new Creator<ParkingLot>() {
        @Override
        public ParkingLot createFromParcel(Parcel in) {
            return new ParkingLot(in);
        }

        @Override
        public ParkingLot[] newArray(int size) {
            return new ParkingLot[size];
        }
    };
}