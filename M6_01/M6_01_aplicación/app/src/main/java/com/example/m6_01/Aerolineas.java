package com.example.m6_01;

import java.util.ArrayList;

public class Aerolineas {

    // Caso del profe: dos aviones de frente
    public static ArrayList<Avion> AEROMEXICO() {
        ArrayList<Avion> aviones = new ArrayList<>();
        aviones.add(new Avion(Direccion.EAST, 0, 0));
        aviones.add(new Avion(Direccion.WEST, 2, 0));
        return aviones;
    }

    // Mi caso del ejercicio en papel
    public static ArrayList<Avion> MI_CASO() {
        ArrayList<Avion> aviones = new ArrayList<>();
        aviones.add(new Avion(Direccion.EAST, 0, 2));    // A
        aviones.add(new Avion(Direccion.WEST, 4, 2));    // B
        aviones.add(new Avion(Direccion.SOUTH, 2, 0));   // C
        aviones.add(new Avion(Direccion.NORTH, 6, 6));   // D
        return aviones;
    }
}
