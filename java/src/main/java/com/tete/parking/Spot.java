package com.tete.parking;

public class Spot {
    public enum Type { MOTORCYCLE, COMPACT, LARGE }

    public final int level, row, col;
    public final Type type;
    public Vehicle occupant; // null = livre

    public Spot(int level, int row, int col, Type type) {
        this.level = level; this.row = row; this.col = col; this.type = type;
        this.occupant = null;
    }

    public boolean isFree() { return occupant == null; }
    public String id() { return "L"+level+"-R"+row+"-C"+col; }
}
