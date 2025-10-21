package com.example.lek4;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

// Naprawiony licznik bez komentarzy w buttonTak i buttonNie
public class MainActivity extends AppCompatActivity {
    ArrayList<Pytanie> pytanie = new ArrayList<Pytanie>();
    private TextView textViewtrescPytania;
    private TextView iloscPoprawnychOdpowiedzi;
    private Button buttonNie;
    private Button buttonTak;
    private Button buttonPodpowiedzi;
    private Button buttonNastepne;
    private ImageView imageViewObraz;
    private int ilosc = 0;
    private int iloscDobrze = 0;
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

        textViewtrescPytania = findViewById(R.id.tekst);
        imageViewObraz = findViewById(R.id.imageView3);
        buttonNie = findViewById(R.id.button6);
        buttonTak = findViewById(R.id.button5);
        buttonNastepne = findViewById(R.id.button2);
        buttonPodpowiedzi = findViewById(R.id.button);
        iloscPoprawnychOdpowiedzi=findViewById(R.id.textView2);
        pytanie.add(new Pytanie(R.drawable.kot1, "czy jest to slodkie?", true, "tak jest to slodkie"));
        pytanie.add(new Pytanie(R.drawable.kotek2, "czy jest to mega slodkie?", false, "no oczywiscie"));
        pytanie.add(new Pytanie(R.drawable.kot3, "czy jest to rudy?", true, "jeszcze sie pytasz?"));

        wyswietlPytanie(ilosc);
        buttonPodpowiedzi.setText("Podpowiedz");
        iloscPoprawnychOdpowiedzi.setText(String.valueOf(iloscDobrze));
        buttonTak.setText("Tak");
        buttonNie.setText("Nie");
        buttonNastepne.setText("Nastepne");

        buttonNastepne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ilosc++;
                if (ilosc >= pytanie.size()) {
                    ilosc = 0;
                }
                wyswietlPytanie(ilosc);
            }
        });

        buttonTak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sprawdzOdpowiedz(ilosc, true);
            }
        });

        buttonNie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sprawdzOdpowiedz(ilosc, false);
            }
        });
        buttonPodpowiedzi.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this,PodpowiedzActivity.class);
                        intent.putExtra("NUMERPYTANIA",ilosc);
                        startActivity(intent);
                    }
                }
        );
    }

    private void wyswietlPytanie(int ktorePytanie) {
        textViewtrescPytania.setText(pytanie.get(ktorePytanie).getTrescPytania());
        imageViewObraz.setImageResource(pytanie.get(ktorePytanie).getIdobrazek());

    }

    private void sprawdzOdpowiedz(int ktorePytanie, boolean udzielonaOdpowiedz) {
        if (pytanie.get(ktorePytanie).isOdpowiedzi() == udzielonaOdpowiedz) {
            pytanie.get(ktorePytanie).setCzyOdpOK(true);
            Toast.makeText(this, "dobrze", Toast.LENGTH_SHORT).show();
            iloscDobrze++;
            iloscPoprawnychOdpowiedzi.setText(String.valueOf(iloscDobrze));

        } else {
            pytanie.get(ktorePytanie).setCzyOdpOK(false);
            Toast.makeText(this, "zle", Toast.LENGTH_SHORT).show();
        }
    }

};
