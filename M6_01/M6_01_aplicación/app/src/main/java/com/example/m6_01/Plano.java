package com.example.m6_01;
import java.util.ArrayList;

public class Plano {
    ArrayList<Avion> aviones;        // aviones que siguen volando
    ArrayList<Colision> colisiones;  // explosiones de este paso
    int noPaso;                      // número de paso
    int totalColisiones;             // colisiones acumuladas desde el paso 0

    public Plano(int noPaso, ArrayList<Avion> aviones, ArrayList<Colision> colisiones, int totalColisiones) {
        this.noPaso = noPaso;
        this.aviones = aviones;
        this.colisiones = colisiones;
        this.totalColisiones = totalColisiones;
    }

    public Plano next() {
        return Analizador.next(noPaso + 1, this);
    }

    public Plano prev() {
        if (noPaso == 0) {
            return this;
        }
        return Analizador.prev(noPaso - 1);
    }
}