package com.example.m6_01;

import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Plano plano;      // la foto que se está mostrando
    TextView tvInfo;
    ImageView[][] celdas = new ImageView[Analizador.FILAS][Analizador.COLUMNAS];

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

        // Conectar con el diseño XML
        tvInfo = findViewById(R.id.tvInfo);
        GridLayout gridPlano = findViewById(R.id.gridPlano);
        Button btnAnterior = findViewById(R.id.btnAnterior);
        Button btnSiguiente = findViewById(R.id.btnSiguiente);

        crearGrid(gridPlano);

        // Foto del paso 0
        plano = Planificador.crearRutaInicial();
        dibujar();

        // Botones
        btnSiguiente.setOnClickListener(v -> {
            plano = plano.next();
            dibujar();
        });

        btnAnterior.setOnClickListener(v -> {
            plano = plano.prev();
            dibujar();
        });
    }

    // Crea las 64 casillas del grid (8 x 8)
    private void crearGrid(GridLayout gridPlano) {
        float d = getResources().getDisplayMetrics().density;
        int ancho = getResources().getDisplayMetrics().widthPixels;
        int tamano = (int) ((ancho - 48 * d) / Analizador.COLUMNAS) - 2;

        for (int y = 0; y < Analizador.FILAS; y++) {
            for (int x = 0; x < Analizador.COLUMNAS; x++) {
                ImageView celda = new ImageView(this);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = tamano;
                params.height = tamano;
                params.setMargins(1, 1, 1, 1);
                celda.setLayoutParams(params);
                celda.setBackgroundColor(0xFFFFFFFF);
                celda.setPadding(6, 6, 6, 6);
                gridPlano.addView(celda);
                celdas[y][x] = celda;
            }
        }
    }

    // Dibuja la foto actual en el grid
    private void dibujar() {
        // 1. Limpiar todas las casillas
        for (int y = 0; y < Analizador.FILAS; y++) {
            for (int x = 0; x < Analizador.COLUMNAS; x++) {
                celdas[y][x].setImageDrawable(null);
            }
        }

        // 2. Dibujar los aviones
        for (Avion avion : plano.aviones) {
            celdas[avion.y][avion.x].setImageResource(avion.getImage());
        }

        // 3. Dibujar las explosiones
        for (Colision colision : plano.colisiones) {
            celdas[colision.y][colision.x].setImageResource(colision.getImage());
        }

        // 4. Mostrar la información
        tvInfo.setText("Paso: " + plano.noPaso
                + "   Colisiones en este paso: " + plano.colisiones.size()
                + "\nTotal de colisiones: " + plano.totalColisiones
                + "   Aviones en vuelo: " + plano.aviones.size());
    }
}