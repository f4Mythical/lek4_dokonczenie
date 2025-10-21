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



public class PodpowiedzActivity extends AppCompatActivity {
private Button buttonCofnac;
 TextView textViewTrescPodpowiedziDo;
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
        int numerPytania =getIntent().getIntExtra("NUMERPYTANIA",0);

        buttonCofnac=findViewById(R.id.button3);
        textViewTrescPodpowiedziDo = findViewById(R.id.tekstDoPodpowiedzi);
        textViewTrescPodpowiedziDo.setText("Podpowiedz do pytania "+(numerPytania+1));
        buttonCofnac.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(PodpowiedzActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }
}