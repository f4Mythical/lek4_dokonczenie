package com.example.lek4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class PodpowiedzActivity extends AppCompatActivity {
    private Button buttonCofnac;
    TextView textViewTrescPodpowiedziDo;
    ImageView imageViewPodpowiedz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_podpowiedz);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        final int numerPytania = getIntent().getIntExtra("NUMERPYTANIA", 0);
        ArrayList<Pytanie> pytanie = Repozytorium1.ZwrocWszystkiePytania();

        buttonCofnac = findViewById(R.id.button3);
        imageViewPodpowiedz = findViewById(R.id.imageView3Podpowiedz);
        textViewTrescPodpowiedziDo = findViewById(R.id.tekstDoPodpowiedzi);
        imageViewPodpowiedz.setImageResource(pytanie.get(numerPytania).getIdobrazek());
        textViewTrescPodpowiedziDo.setText("Podpowiedz do pytania " + (numerPytania + 1) + " " + pytanie.get(numerPytania).getPodpowiedzi());
        buttonCofnac.setText("Cofnij");
        buttonCofnac.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent data = new Intent();
                        data.putExtra("NUMERPYTANIA", numerPytania);
                        data.putExtra("PODPOWIEDZ_WYKORZYSTANA", true);
                        setResult(RESULT_OK, data);
                        finish();
                    }
                }
        );
    }
}