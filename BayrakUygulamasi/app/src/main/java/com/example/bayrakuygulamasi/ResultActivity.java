package com.example.bayrakuygulamasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView textViewSonuc,textViewYuzde;
    private Button buttonTekrar;
    private int dogruSayac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        textViewSonuc = findViewById(R.id.textViewSonuc);
        textViewYuzde = findViewById(R.id.textViewYuzde);
        buttonTekrar = findViewById(R.id.buttonTekrar);
        dogruSayac = getIntent().getIntExtra("dogruSayac",0);
        textViewSonuc.setText(dogruSayac+" Doğru - "+(5-dogruSayac)+" Yanlış");
        textViewYuzde.setText("% "+(dogruSayac*100/5)+" Başarı");
        buttonTekrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResultActivity.this,QuizActivity.class));
                finish();
            }
        });
    }
}