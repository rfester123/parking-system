package com.tete.parking;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ParkingApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("Parking Manager - Setup Check");
            f.setSize(600, 400);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });SpotList list = new SpotList();
        for (int c = 0; c < 12; c++) {
            Spot.Type t = (c < 2) ? Spot.Type.MOTORCYCLE
                    : (c < 6) ? Spot.Type.COMPACT
                    : Spot.Type.LARGE;
            list.add(new Spot(1, 1, c, t));
        }
        list.printLine();

        var car = new Vehicle.Car("ABC-123");
        System.out.println("Alocou carro? " + list.allocateOne(car));
        list.printLine();

        var bus = new Vehicle.Bus("BUS-555");
        System.out.println("Alocou ônibus? " + list.allocateBus(bus));
        list.printLine();
        PlateBST idx = new PlateBST();
        String locCar = list.findLocationByPlate("ABC-123");
        idx.insert("ABC-123", locCar);
        System.out.println("BST find ABC-123 -> " + idx.find("ABC-123"));

        System.out.println("Java OK: GUI inicial aberta.");
    }
}
