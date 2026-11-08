package com.example.m1_03;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;
import java.util.Random;

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
        int[] numeros = new int[1000000];
        Random random = new Random();

        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = random.nextInt(1000000);
        }

        Log.d("M1_03", "Total de elementos: " + numeros.length);
        Log.d("M1_03", "Antes de ordenar: " + Arrays.toString(Arrays.copyOf(numeros, 10)));

        long inicio = System.currentTimeMillis();
        Arrays.sort(numeros);
        long fin = System.currentTimeMillis();
        long tiempo = fin - inicio;
        Log.d("M1_03", "Después de ordenar: " + Arrays.toString(Arrays.copyOf(numeros, 10)));
        Log.d("M1_03", "Tiempo de ordenamiento: " + tiempo + " ms");
    }
}