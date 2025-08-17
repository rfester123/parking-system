package com.tete.parking;

import java.time.Instant;

public abstract class Vehicle {
    protected final String plate;
    protected final Instant entryTime;

    protected Vehicle(String plate) {
        this.plate = plate;
        this.entryTime = Instant.now();
    }

    public String getPlate() { return plate; }
    public Instant getEntryTime() { return entryTime; }

    // Nested subclasses
    public static class Motorcycle extends Vehicle { public Motorcycle(String plate){ super(plate);} }
    public static class Car        extends Vehicle { public Car(String plate){ super(plate);} }
    public static class Bus        extends Vehicle { public Bus(String plate){ super(plate);} }
}
