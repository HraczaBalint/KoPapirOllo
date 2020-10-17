package com.example.kopapirollo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView sajatValasztas, gepiValasztas;
    private TextView eredmenyKijelzo;
    private Button koGomb, papirGomb, olloGomb;
    private  String sajatValasztasVal, gepiValasztasVal;
    private int jatekosPontszam, gepPontszam;
    private int valasztas;
    private  AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        koGomb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sajatValasztas.setBackgroundResource(R.drawable.rock);
                sajatValasztasVal = "ko";
                gepValaszt();
                kiertekeles();
                jatekVege();
            }
        });

        papirGomb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sajatValasztas.setBackgroundResource(R.drawable.paper);
                sajatValasztasVal = "papir";
                gepValaszt();
                kiertekeles();
                jatekVege();
            }
        });

        olloGomb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sajatValasztas.setBackgroundResource(R.drawable.scissors);
                sajatValasztasVal = "ollo";
                gepValaszt();
                kiertekeles();
                jatekVege();
            }
        });

    }

    private void init() {
        sajatValasztas = findViewById(R.id.sajat_valasztas);
        gepiValasztas = findViewById(R.id.gepi_valasztas);
        eredmenyKijelzo = findViewById(R.id.eredmeny);
        koGomb = findViewById(R.id.ko);
        papirGomb = findViewById(R.id.papir);
        olloGomb = findViewById(R.id.ollo);
        sajatValasztasVal = "ko";
        gepiValasztasVal = "ko";
        jatekosPontszam = 0;
        gepPontszam = 0;
        builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(false).setMessage("Szeretnél újat játszani?")
                .setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ujJatek();
                    }
                })
                .setNegativeButton("Nem", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        kilépés();
                    }
                });


    }

    private void gepValaszt() {
        String[] opciok = {"ko", "papir", "ollo"};
        valasztas = (int)(Math.random()*3);
        gepiValasztasVal = opciok[valasztas];

        if (opciok[valasztas] == "ko")
        {
            gepiValasztas.setBackgroundResource(R.drawable.rock);
        }
        else if (opciok[valasztas] == "papir")
        {
            gepiValasztas.setBackgroundResource(R.drawable.paper);
        }
        else if (opciok[valasztas] == "ollo")
        {
            gepiValasztas.setBackgroundResource(R.drawable.scissors);
        }
    }

    private void kiertekeles() {
        if (sajatValasztasVal == "ko" && gepiValasztasVal == "ollo") {
            jatekosPontszam++;
            Toast.makeText(MainActivity.this, "A játékos nyerte a kört!", Toast.LENGTH_SHORT).show();
        }
        else if (sajatValasztasVal == "papir" && gepiValasztasVal == "ko")
        {
            jatekosPontszam++;
            Toast.makeText(MainActivity.this, "A játékos nyerte a kört!", Toast.LENGTH_SHORT).show();
        }
        else if (sajatValasztasVal == "ollo" && gepiValasztasVal == "papir")
        {
            jatekosPontszam++;
            Toast.makeText(MainActivity.this, "A játékos nyerte a kört!", Toast.LENGTH_SHORT).show();
        }
        else if (sajatValasztasVal == "ko" && gepiValasztasVal == "papir")
        {
            gepPontszam++;
            Toast.makeText(MainActivity.this, "A gép nyerte a kört!", Toast.LENGTH_SHORT).show();
        }
        else if (sajatValasztasVal == "papir" && gepiValasztasVal == "ollo")
        {
            gepPontszam++;
            Toast.makeText(MainActivity.this, "A gép nyerte a kört!", Toast.LENGTH_SHORT).show();
        }
        else if (sajatValasztasVal == "ollo" && gepiValasztasVal == "ko")
        {
            gepPontszam++;
            Toast.makeText(MainActivity.this, "A gép nyerte a kört!", Toast.LENGTH_SHORT).show();
        }
        eredmenyKijelzo.setText(String.valueOf("Ember: " + jatekosPontszam + " | Gép: " + gepPontszam));
    }

    private void jatekVege() {
        if (jatekosPontszam == 3)
        {
            builder.setTitle("Nyertél!").create().show();
        }
        if (gepPontszam == 3)
        {
            builder.setTitle("Vesztettél!").create().show();
        }
    }

    private void ujJatek() {
        jatekosPontszam = 0;
        gepPontszam = 0;
        eredmenyKijelzo.setText(String.valueOf("Ember: " + jatekosPontszam + " | Gép: " + gepPontszam));
    }

    private  void kilépés() {
        System.exit(0);
    }



}