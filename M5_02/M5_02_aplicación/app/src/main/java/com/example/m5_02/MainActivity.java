package com.example.m5_02;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "M5_02";
    private static final String ARCHIVO = "input.txt";

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

        resolverCalorias();
    }

    private void resolverCalorias() {
        int elfoActual = 1;        // número del elfo que se está sumando
        int caloriasActuales = 0;  // suma de calorías del elfo actual
        int maxCalorias = 0;       // la suma más grande encontrada
        int elfoConMas = 0;        // número del elfo que lleva más calorías

        try (BufferedReader lector = new BufferedReader(
                new InputStreamReader(getAssets().open(ARCHIVO)))) {

            String linea;
            // Leer el archivo línea por línea hasta que se acabe
            while ((linea = lector.readLine()) != null) {
                linea = linea.trim();

                if (linea.isEmpty()) {
                    // Línea en blanco: terminó el inventario de un elfo
                    if (caloriasActuales > maxCalorias) {
                        maxCalorias = caloriasActuales;
                        elfoConMas = elfoActual;
                    }
                    elfoActual++;
                    caloriasActuales = 0;
                } else {
                    // Línea con número: se suma al elfo actual
                    caloriasActuales += Integer.parseInt(linea);
                }
            }

            // El último elfo no tiene línea en blanco después, se revisa al final
            if (caloriasActuales > maxCalorias) {
                maxCalorias = caloriasActuales;
                elfoConMas = elfoActual;
            }

            Log.d(TAG, "Total de elfos: " + elfoActual);
            Log.d(TAG, "El elfo que lleva más calorías es el número " + elfoConMas);
            Log.d(TAG, "Calorías totales que lleva ese elfo: " + maxCalorias);

        } catch (IOException e) {
            Log.e(TAG, "No se pudo leer el archivo " + ARCHIVO, e);
        }
    }
}