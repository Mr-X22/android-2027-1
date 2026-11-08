package com.example.m3_01;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // 1. Crear las piezas
        Procesamiento procesamiento = new Procesamiento();
        Control control = new Control(procesamiento);
        Display display = new Display();

        // 2. Armar la calculadora
        Calculadora calculadora = new Calculadora();
        calculadora.setProcesamiento(procesamiento);
        calculadora.setControl(control);
        calculadora.setDisplay(display);

        // 3. Pruebas
        Log.d("M3_01", "8 + 4 -> " + calculadora.calcular(8, Operacion.SUM, 4));
        Log.d("M3_01", "8 - 4 -> " + calculadora.calcular(8, Operacion.RES, 4));
        Log.d("M3_01", "8 * 4 -> " + calculadora.calcular(8, Operacion.MUL, 4));
        Log.d("M3_01", "8 / 4 -> " + calculadora.calcular(8, Operacion.DIV, 4));
        Log.d("M3_01", "7 / 2 -> " + calculadora.calcular(7, Operacion.DIV, 2));
        Log.d("M3_01", "5 / 0 -> " + calculadora.calcular(5, Operacion.DIV, 0));
        Log.d("M3_01", "0 / 0 -> " + calculadora.calcular(0, Operacion.DIV, 0));
    }
}