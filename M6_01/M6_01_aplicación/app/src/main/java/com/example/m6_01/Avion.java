package com.example.m6_01;

public class Avion {
    Direccion direccion;
    int x;
    int y;

    public Avion(Direccion direccion, int x, int y) {
        this.direccion = direccion;
        this.x = x;
        this.y = y;
    }

    public void mover() {
        switch (direccion) {
            case NORTH:
                y = y - 1;
                break;
            case SOUTH:
                y = y + 1;
                break;
            case EAST:
                x = x + 1;
                break;
            case WEST:
                x = x - 1;
                break;
        }
    }
    public Avion copiar() {
        return new Avion(direccion, x, y);
    }

    public int getImage() {
        switch (direccion) {
            case NORTH:
                return R.mipmap.north;
            case SOUTH:
                return R.mipmap.south;
            case EAST:
                return R.mipmap.east;
            case WEST:
                return R.mipmap.west;
        }
        return R.mipmap.north;
    }
}