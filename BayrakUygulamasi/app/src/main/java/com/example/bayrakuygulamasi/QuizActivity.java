package com.example.bayrakuygulamasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashSet;

public class QuizActivity extends AppCompatActivity {
    private TextView textViewDogru, textViewYanlis, textViewSoruSayisi;
    private ImageView imageViewBayrak;
    private Button buttonA, buttonB,buttonC,buttonD;
    private ArrayList<Bayraklar> sorularListe;
    private ArrayList<Bayraklar> yanlisSeceneklerListe;
    private Bayraklar dogruSoru;
    private VeriTabani vt;
    private int soruSayac = 0;
    private int yanlisSayac = 0;
    private int dogruSayac = 0;
    private HashSet<Bayraklar> secenekleriKaristir = new HashSet<>();
    private ArrayList<Bayraklar> secenekler = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        textViewDogru = findViewById(R.id.textViewDogru);
        textViewYanlis = findViewById(R.id.textViewYanlis);
        textViewSoruSayisi = findViewById(R.id.textviewSoruSayisi);
        imageViewBayrak = findViewById(R.id.imageViewBayrak);
        buttonA = findViewById(R.id.buttonA);
        buttonB = findViewById(R.id.buttonB);
        buttonC = findViewById(R.id.buttonC);
        buttonD = findViewById(R.id.buttonD);
        vt = new VeriTabani(this);
        sorularListe = new BayraklarDao().rastgeleGetir(vt);
        soruYukle();
        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dogruKontrol(buttonA);
                sayacKontrol();
                Log.e("SAYAC",String.valueOf(soruSayac));
            }
        });
        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dogruKontrol(buttonB);
                sayacKontrol();

            }
        });
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               dogruKontrol(buttonC);
                sayacKontrol();
            }
        });
        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               dogruKontrol(buttonD);
                sayacKontrol();
            }
        });
    }
    public void soruYukle(){
        textViewSoruSayisi.setText((soruSayac+1)+". Soru");
        textViewDogru.setText("Doğru:"+(dogruSayac));
        textViewYanlis.setText("Yanlış:"+(yanlisSayac));
        dogruSoru = sorularListe.get(soruSayac);
        yanlisSeceneklerListe = new BayraklarDao().rastgele3YanlisSecenekGetir(vt,dogruSoru.getBayrak_id());
        imageViewBayrak.setImageResource(getResources().getIdentifier(dogruSoru.getBayrak_resim(),"drawable",getPackageName()));
        secenekleriKaristir.clear();
        secenekleriKaristir.add(dogruSoru);
        secenekleriKaristir.add(yanlisSeceneklerListe.get(0));
        secenekleriKaristir.add(yanlisSeceneklerListe.get(1));
        secenekleriKaristir.add(yanlisSeceneklerListe.get(2));
        secenekler.clear();
        for (Bayraklar b:secenekleriKaristir){
            secenekler.add(b);
        }
        buttonA.setText(secenekler.get(0).getBayrak_ad());
        buttonB.setText(secenekler.get(1).getBayrak_ad());
        buttonC.setText(secenekler.get(2).getBayrak_ad());
        buttonD.setText(secenekler.get(3).getBayrak_ad());
    }
    public void dogruKontrol(Button button){
        String buttonYazi = button.getText().toString();
        String dogruCevap = dogruSoru.getBayrak_ad();
        Log.e("Doğru",dogruCevap);
        Log.e("ButtonYazi",buttonYazi);
        if (buttonYazi.equals(dogruCevap)){
            dogruSayac++;
        }else{
            yanlisSayac++;
        }
        textViewDogru.setText("Doğru:"+dogruSayac);
        textViewYanlis.setText("Yanlış:"+yanlisSayac);
    }
    public void sayacKontrol(){
        soruSayac++;
        if (soruSayac!=5){
            soruYukle();
        }else{
            Intent intent = new Intent(QuizActivity.this,ResultActivity.class);
            intent.putExtra("dogruSayac",dogruSayac);
            startActivity(intent);
            finish();
        }
    }
}