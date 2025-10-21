package com.example.lek4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

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


    private ActivityResultLauncher<Intent> podpowiedzLauncher;

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
        iloscPoprawnychOdpowiedzi = findViewById(R.id.textView2);

        pytanie = Repozytorium1.ZwrocWszystkiePytania();

        podpowiedzLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                (ActivityResult result) -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null && data.getBooleanExtra("PODPOWIEDZ_WYKORZYSTANA", false)) {
                            int numer = data.getIntExtra("NUMERPYTANIA", -1);
                            if (numer >= 0 && numer < pytanie.size()) {
                                pytanie.get(numer).setCzyWykorzystanaPodpowiedz(true);
                            }
                        }
                    }
                });

        if (savedInstanceState == null) {
            wyswietlPytanie(0);
        } else {
            ilosc = savedInstanceState.getInt("NUMERPYTANIA", 0);
            iloscDobrze = savedInstanceState.getInt("ILOSC_DOBRZE", 0);
            wyswietlPytanie(ilosc);
        }

        iloscPoprawnychOdpowiedzi.setText(String.valueOf(iloscDobrze));

        buttonPodpowiedzi.setText("Podpowiedz");
        buttonTak.setText("Tak");
        buttonNie.setText("Nie");
        buttonNastepne.setText("Nastepne");

        buttonNastepne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonTak.setEnabled(true);
                buttonNie.setEnabled(true);
                if (ilosc < pytanie.size() - 1) {
                    ilosc++;
                    wyswietlPytanie(ilosc);
                } else {
                    buttonTak.setVisibility(View.GONE);
                    buttonNie.setVisibility(View.GONE);
                    buttonPodpowiedzi.setVisibility(View.GONE);
                    buttonNastepne.setVisibility(View.GONE);

                    imageViewObraz.setVisibility(View.GONE);
                    textViewtrescPytania.setText("Koniec quizu. Twój wynik: " + iloscDobrze);
                }
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
                        Intent intent = new Intent(MainActivity.this, PodpowiedzActivity.class);
                        intent.putExtra("NUMERPYTANIA", ilosc);
                        podpowiedzLauncher.launch(intent);
                    }
                }
        );
    }

    private void wyswietlPytanie(int ktorePytanie) {
        textViewtrescPytania.setText(pytanie.get(ktorePytanie).getTrescPytania());
        imageViewObraz.setImageResource(pytanie.get(ktorePytanie).getIdobrazek());
        imageViewObraz.setVisibility(View.VISIBLE);
    }

    private void sprawdzOdpowiedz(int ktorePytanie, boolean udzielonaOdpowiedz) {
        Pytanie p = pytanie.get(ktorePytanie);

        if (p.isOdpowiedzi() == udzielonaOdpowiedz) {
            if (!p.isCzyOdpOK()) {
                int punkt = p.isCzyWykorzystanaPodpowiedz() ? 5 : 10;
                iloscDobrze += punkt;
                iloscPoprawnychOdpowiedzi.setText(String.valueOf(iloscDobrze));
            }
            p.setCzyOdpOK(true);
            Toast.makeText(this, "dobrze", Toast.LENGTH_SHORT).show();
            buttonNie.setEnabled(false);
            buttonTak.setEnabled(false);
        } else {
            p.setCzyOdpOK(false);
            Toast.makeText(this, "zle", Toast.LENGTH_SHORT).show();
            buttonNie.setEnabled(false);
            buttonTak.setEnabled(false);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("NUMERPYTANIA", ilosc);
        outState.putInt("ILOSC_DOBRZE", iloscDobrze);
    }
}