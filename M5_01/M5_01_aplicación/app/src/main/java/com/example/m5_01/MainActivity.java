package com.example.m5_01;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "M5_01";

    // Posición máxima: a partir de F(93) el resultado ya no cabe en un long
    private static final int MAX_POSICION = 92;

    // Memoization: aquí se guardan los números de Fibonacci ya calculados
    private final List<Long> memo = new ArrayList<>();

    // Posición actual en la secuencia (0 = todavía no se presiona ningún botón)
    private int posicion = 0;

    private TextView tvValor;

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

        // Valores iniciales de la secuencia: F(0) = 0 y F(1) = 1
        memo.add(0L);
        memo.add(1L);

        // Conectar el código con los elementos del diseño XML
        tvValor = findViewById(R.id.tvValor);
        Button btnAvanzar = findViewById(R.id.btnAvanzar);
        Button btnRetroceder = findViewById(R.id.btnRetroceder);

        // Qué hacer al dar clic en cada botón
        btnAvanzar.setOnClickListener(v -> avanzar());
        btnRetroceder.setOnClickListener(v -> retroceder());
    }

    private void avanzar() {
        if (posicion < MAX_POSICION) {
            posicion++;
            mostrar();
        }
    }

    private void retroceder() {
        if (posicion > 1) {
            posicion--;
            mostrar();
        } else {
            posicion = 0;
            tvValor.setText(R.string.mensaje_inicial);
        }
    }

    private void mostrar() {
        long valor = fibonacci(posicion);
        tvValor.setText(String.valueOf(valor));
    }

    private long fibonacci(int n) {
        // Si ya se calculó antes, se toma de la memoria
        if (n < memo.size()) {
            Log.d(TAG, "F(" + n + ") = " + memo.get(n) + " (tomado de la memoria)");
            return memo.get(n);
        }
        // Si no, se calcula con el ciclo for y se guarda
        for (int i = memo.size(); i <= n; i++) {
            long nuevo = memo.get(i - 1) + memo.get(i - 2);
            memo.add(nuevo);
            Log.d(TAG, "F(" + i + ") = " + nuevo + " (calculado con for)");
        }
        return memo.get(n);
    }
}