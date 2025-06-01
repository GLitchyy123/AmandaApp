package edu.uph.m23si2.amandaapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class amandakalkulator extends AppCompatActivity {

    EditText editTextNumber, editTextNumber2;
    Button buttonTambah, buttonKali, buttonBagi, buttonClear;
    TextView textViewHasil, textViewHasilAngka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_amandakalkulator);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextNumber = findViewById(R.id.editTextNumber);
        editTextNumber2 = findViewById(R.id.editTextNumber2);
        buttonTambah = findViewById(R.id.button2);
        buttonKali = findViewById(R.id.button3);
        buttonBagi = findViewById(R.id.button4);
        buttonClear = findViewById(R.id.buttonClear); // ← Tambahan
        textViewHasil = findViewById(R.id.textView4);
        textViewHasilAngka = findViewById(R.id.textViewHasilAngka);

        buttonTambah.setOnClickListener(v -> hitung("+"));
        buttonKali.setOnClickListener(v -> hitung("*"));
        buttonBagi.setOnClickListener(v -> hitung("/"));

        buttonClear.setOnClickListener(v -> {
            editTextNumber.setText("");
            editTextNumber2.setText("");
            textViewHasil.setText("");
            textViewHasilAngka.setText("");
        });
    }

    private void hitung(String operasi) {
        String input1 = editTextNumber.getText().toString();
        String input2 = editTextNumber2.getText().toString();

        if (input1.isEmpty() || input2.isEmpty()) {
            textViewHasil.setText("Mohon isi kedua nilai");
            textViewHasilAngka.setText("");
            return;
        }

        try {
            double angka1 = Double.parseDouble(input1);
            double angka2 = Double.parseDouble(input2);
            double hasil = 0;

            switch (operasi) {
                case "+":
                    hasil = angka1 + angka2;
                    break;
                case "*":
                    hasil = angka1 * angka2;
                    break;
                case "/":
                    if (angka2 == 0) {
                        textViewHasil.setText("Tidak bisa dibagi 0");
                        textViewHasilAngka.setText("");
                        return;
                    } else {
                        hasil = angka1 / angka2;
                    }
                    break;
            }

            textViewHasil.setText("Hasil:");
            if (hasil % 1 == 0) {
                textViewHasilAngka.setText(String.valueOf((int) hasil));
            } else {
                textViewHasilAngka.setText(String.valueOf(hasil));
            }
        } catch (NumberFormatException e) {
            textViewHasil.setText("Input tidak valid");
            textViewHasilAngka.setText("");
        }
    }
}
