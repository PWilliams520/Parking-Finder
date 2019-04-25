package com.example.noahjarvis.ParkingFinder;

import android.os.Parcel;
import android.os.Parcelable;

public class ParkingCapacity /*implements Parcelable*/ {
    private Long Capacity;
    private Long Current;
    private String Name;

    public Long getCapacity() {
        return Capacity;
    }

    public void setCapacity(Long capacity) {
        Capacity = capacity;
    }

    public Long getCurrent() {
        return Current;
    }

    public void setCurrent(Long current) {
        Current = current;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

//    protected ParkingCapacity(Parcel in){
//        Capacity = in.readLong();
//        Current = in.readLong();
//        Name = in.readString();
//    }
//
//    public void writeToParcel(Parcel parcel, int i) {
//        parcel.writeLong(Capacity);
//        parcel.writeLong(Current);
//        parcel.writeString(Name);
//
//    }
//
//    public static final Creator<ParkingCapacity> CREATOR = new Creator<ParkingCapacity>() {
//        @Override
//        public ParkingCapacity createFromParcel(Parcel in) {
//            return new ParkingCapacity(in);
//        }
//
//        @Override
//        public ParkingCapacity[] newArray(int size) {
//            return new ParkingCapacity[size];
//        }
//    };
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//

}
