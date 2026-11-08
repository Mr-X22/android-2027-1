package com.example.m3_01;

public class Display implements IDisplay {

    private static final String PREFIJO_RESULTADO = "Resultado: ";
    private static final String PREFIJO_ERROR = "Error: ";

    @Override
    public String muestraResultado(float res) {
        return PREFIJO_RESULTADO + res;
    }

    @Override
    public String muestraError(CalculadoraError error) {
        String mensaje;
        switch (error) {
            case DIV_ZERO:
                mensaje = "No se puede dividir entre cero (resultado NaN o infinito)";
                break;
            case MAX_NUMBER:
                mensaje = "El resultado excede el número máximo permitido";
                break;
            case IMAGINARIO:
                mensaje = "El resultado es un número imaginario";
                break;
            default:
                mensaje = "Error desconocido";
        }
        return PREFIJO_ERROR + mensaje;
    }
}