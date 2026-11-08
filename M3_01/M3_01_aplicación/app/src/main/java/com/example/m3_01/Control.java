package com.example.m3_01;

public class Control implements IControl {

    private int valorX;
    private int valorY;
    private Operacion operacion;
    private final IProcesamiento procesamiento;

    public Control(IProcesamiento procesamiento) {
        this.procesamiento = procesamiento;
    }

    @Override
    public void introduceValorX(int x) {
        valorX = x;
    }

    @Override
    public void introduceValorY(int y) {
        valorY = y;
    }

    @Override
    public void introduceOperacion(Operacion op) {
        operacion = op;
    }

    @Override
    public float igual() {
        if (operacion == null) {
            return Float.NaN;
        }
        float x = valorX;
        float y = valorY;
        switch (operacion) {
            case SUM:
                return procesamiento.suma(x, y);
            case RES:
                return procesamiento.resta(x, y);
            case MUL:
                return procesamiento.multi(x, y);
            case DIV:
                return procesamiento.div(x, y);
            default:
                return Float.NaN;
        }
    }
}