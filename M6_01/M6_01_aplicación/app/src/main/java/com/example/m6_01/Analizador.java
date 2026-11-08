package com.example.m6_01;
import java.util.ArrayList;
import java.util.HashMap;

public class Analizador {

    // Tamaño del grid (el radar del aeropuerto)
    static final int FILAS = 8;
    static final int COLUMNAS = 8;

    // Memoria: aquí se guarda la "foto" de cada paso
    static HashMap<Integer, Plano> memoria = new HashMap<>();

    public static void inicializa(Plano plano) {
        memoria.clear();
        memoria.put(0, plano);
    }

    public static Plano prev(int noPaso) {
        return memoria.get(noPaso);
    }

    public static Plano next(int noPaso, Plano plano) {
        // Punto 5: si la foto ya existe, se toma de la memoria
        if (memoria.containsKey(noPaso)) {
            return memoria.get(noPaso);
        }

        // Punto 1: mover una copia de cada avión
        ArrayList<Avion> movidos = new ArrayList<>();
        for (Avion avion : plano.aviones) {
            Avion copia = avion.copiar();
            copia.mover();
            movidos.add(copia);
        }

        // Puntos 2, 3 y 4: revisar el tablero como Sudoku
        ArrayList<Avion> nuevosAviones = new ArrayList<>();
        ArrayList<Colision> colisiones = new ArrayList<>();

        for (Avion avion : movidos) {
            // Contar cuántos aviones hay en la misma casilla que este
            int enLaCasilla = 0;
            for (Avion otro : movidos) {
                if (otro.x == avion.x && otro.y == avion.y) {
                    enLaCasilla = enLaCasilla + 1;
                }
            }

            if (enLaCasilla > 1) {
                // Punto 3: hubo choque, se pone una explosión (una sola por casilla)
                if (!hayColisionEn(colisiones, avion.x, avion.y)) {
                    colisiones.add(new Colision(avion.x, avion.y));
                }
            } else if (avion.x >= 0 && avion.x < COLUMNAS && avion.y >= 0 && avion.y < FILAS) {
                // Sigue dentro del radar, así que sigue volando
                nuevosAviones.add(avion);
            }
            // Punto 4: si salió del radar no se agrega (Triángulo de las Bermudas)
        }

        // Punto 5: guardar la foto en la memoria
        int total = plano.totalColisiones + colisiones.size();
        Plano planoNuevo = new Plano(noPaso, nuevosAviones, colisiones, total);
        memoria.put(noPaso, planoNuevo);
        return planoNuevo;
    }
    private static boolean hayColisionEn(ArrayList<Colision> colisiones, int x, int y) {
        for (Colision c : colisiones) {
            if (c.x == x && c.y == y) {
                return true;
            }
        }
        return false;
    }
}