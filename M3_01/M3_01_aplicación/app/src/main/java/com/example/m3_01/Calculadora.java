package com.example.m3_01;

public class Calculadora implements ICalculadora {

    private IControl control;
    private IDisplay display;
    private IProcesamiento procesamiento;

    @Override
    public void setControl(IControl control) {
        this.control = control;
    }

    @Override
    public void setDisplay(IDisplay display) {
        this.display = display;
    }

    @Override
    public void setProcesamiento(IProcesamiento procesa) {
        this.procesamiento = procesa;
    }

    public String calcular(int x, Operacion op, int y) {
        control.introduceValorX(x);
        control.introduceValorY(y);
        control.introduceOperacion(op);

        float resultado = control.igual();

        if (Float.isNaN(resultado) || Float.isInfinite(resultado)) {
            if (op == Operacion.DIV) {
                return display.muestraError(CalculadoraError.DIV_ZERO);
            }
            return display.muestraError(CalculadoraError.MAX_NUMBER);
        }
        return display.muestraResultado(resultado);
    }
}