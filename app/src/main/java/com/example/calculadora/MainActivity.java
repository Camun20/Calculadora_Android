package com.example.calculadora;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnSuma;
    private Button btnResta;
    private Button btnMultiplicar;
    private Button btnDividir;
    private Button btnNueve;
    private Button btnOcho;
    private Button btnSiete;
    private Button btnSeis;
    private Button btnCinco;
    private Button btnCuatro;
    private Button btnTres;
    private Button btnDos;
    private Button btnUno;
    private Button btnCero;
    private Button btnPunto;
    private Button btnIgual;
    private Button btnVaciar;

    private TextView result;
    private double n1= 0.0;
    private double n2= 0.0;
    String opera;
    private boolean nuevoNumero = false;

    @SuppressLint("MissingInflatedId")
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
        result = findViewById(R.id.result);
        btnVaciar = findViewById(R.id.btnVaciar);
        btnVaciar.setOnClickListener(v -> {
            result.setText("");
            n1 = 0.0;
            n2 = 0.0;
            opera = "";
        });

        btnPunto = findViewById(R.id.btnPunto);
        btnPunto.setOnClickListener(v -> {
            result.setText(result.getText()+".");
        });

        inicializarBotonesNumericos();
        inicializarBotonesOperaciones();
    }

    private void inicializarBotonesNumericos() {
        int[] botonesNumeros = {R.id.btnCero, R.id.btnUno, R.id.btnDos, R.id.btnTres, R.id.btnCuatro, R.id.btnCinco, R.id.btnSeis, R.id.btnSiete, R.id.btnOcho, R.id.btnNueve};
        for (int i = 0; i < botonesNumeros.length; i++) {
            final int num = i;
            Button btn = findViewById(botonesNumeros[i]);
            btn.setOnClickListener(v -> {
                if (nuevoNumero) {
                    result.setText("");
                    nuevoNumero = false;
                }
                result.append(String.valueOf(num));
            });
        }
    }

    private void inicializarBotonesOperaciones() {
        btnSuma = findViewById(R.id.btnSuma);
        btnResta = findViewById(R.id.btnResta);
        btnMultiplicar = findViewById(R.id.btnMultiplicar);
        btnDividir = findViewById(R.id.btnDividir);
        btnIgual = findViewById(R.id.btnIgual);

        btnSuma.setOnClickListener(v -> establecerOperacion("+"));
        btnResta.setOnClickListener(v -> establecerOperacion("-"));
        btnMultiplicar.setOnClickListener(v -> establecerOperacion("*"));
        btnDividir.setOnClickListener(v -> establecerOperacion("/"));

        btnIgual.setOnClickListener(v -> calcularResultado());
    }

    private void establecerOperacion(String operador) {
        if (!result.getText().toString().isEmpty()) {
            n1 = Double.parseDouble(result.getText().toString());
            opera = operador;
            nuevoNumero = true;
        }
    }

    private void calcularResultado() {
        if (!result.getText().toString().isEmpty()) {
            n2 = Double.parseDouble(result.getText().toString());
            double resultado = 0.0;

            switch (opera) {
                case "+":
                    resultado = n1 + n2;
                    break;
                case "-":
                    resultado = n1 - n2;
                    break;
                case "*":
                    resultado = n1 * n2;
                    break;
                case "/":
                    if (n2 != 0) {
                        resultado = n1 / n2;
                    } else {
                        result.setText("Error");
                        return;
                    }
                    break;
            }

            result.setText(String.valueOf(resultado));
            n1 = resultado; // Guarda el resultado para seguir operando
            nuevoNumero = true;
        }
    }
}