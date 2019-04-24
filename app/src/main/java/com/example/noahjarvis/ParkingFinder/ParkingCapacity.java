package com.example.noahjarvis.ParkingFinder;

public class ParkingCapacity {
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
}
