package com.example.m6_01;

import java.util.ArrayList;

public class Planificador {

    public static Plano crearRutaInicial() {
        // Aquí se elige qué caso de prueba usar
        ArrayList<Avion> aviones = Aerolineas.MI_CASO();

        Plano planoInicial = new Plano(0, aviones, new ArrayList<>(), 0);
        Analizador.inicializa(planoInicial);
        return planoInicial;
    }
}