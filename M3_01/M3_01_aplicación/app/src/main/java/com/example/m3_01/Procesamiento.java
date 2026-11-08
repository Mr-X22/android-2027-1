package com.example.m3_01;

public class Procesamiento implements IProcesamiento {

    @Override
    public float suma(float x, float y) {
        return x + y;
    }

    @Override
    public float resta(float x, float y) {
        return x - y;
    }

    @Override
    public float multi(float x, float y) {
        return x * y;
    }

    @Override
    public float div(float x, float y) {
        return x / y;
    }
}