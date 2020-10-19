package com.example.kopapirollo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView sajatValasztas, gepiValasztas, gepHp1, gepHp2, gepHp3, jatekosHp1, jatekosHp2, jatekosHp3;
    private TextView dontetlenKijelzo;
    private Button koGomb, papirGomb, olloGomb;
    private  String sajatValasztasVal, gepiValasztasVal;
    private int jatekosElet, gepElet, dontetlenPontszam;
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
                sajatValasztas.setImageResource(R.drawable.rock);
                sajatValasztasVal = "ko";
                gepValaszt();
                kiertekeles();
                jatekVege();
            }
        });

        papirGomb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sajatValasztas.setImageResource(R.drawable.paper);
                sajatValasztasVal = "papir";
                gepValaszt();
                kiertekeles();
                jatekVege();
            }
        });

        olloGomb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sajatValasztas.setImageResource(R.drawable.scissors);
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
        gepHp1 = findViewById(R.id.gep_hp1);
        gepHp2 = findViewById(R.id.gep_hp2);
        gepHp3 = findViewById(R.id.gep_hp3);
        jatekosHp1 = findViewById(R.id.jatekos_hp1);
        jatekosHp2 = findViewById(R.id.jatekos_hp2);
        jatekosHp3 = findViewById(R.id.jatekos_hp3);
        koGomb = findViewById(R.id.ko);
        papirGomb = findViewById(R.id.papir);
        olloGomb = findViewById(R.id.ollo);
        sajatValasztasVal = "ko";
        gepiValasztasVal = "ko";
        jatekosElet = 3;
        gepElet = 3;
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
        dontetlenKijelzo = findViewById(R.id.dontetlen);
        dontetlenPontszam = 0;

    }

    private void gepValaszt() {
        String[] opciok = {"ko", "papir", "ollo"};
        valasztas = (int)(Math.random()*3);
        gepiValasztasVal = opciok[valasztas];

        if (opciok[valasztas] == "ko")
        {
            gepiValasztas.setImageResource(R.drawable.rock);
        }
        else if (opciok[valasztas] == "papir")
        {
            gepiValasztas.setImageResource(R.drawable.paper);
        }
        else if (opciok[valasztas] == "ollo")
        {
            gepiValasztas.setImageResource(R.drawable.scissors);
        }
    }

    private void kiertekeles() {
        if (sajatValasztasVal == "ko" && gepiValasztasVal == "ollo") {
            gepEletLevon();
            Toast.makeText(MainActivity.this, "A játékos nyerte a kört!", Toast.LENGTH_SHORT).show();
        }
        else if (sajatValasztasVal == "papir" && gepiValasztasVal == "ko")
        {
            gepEletLevon();
            Toast.makeText(MainActivity.this, "A játékos nyerte a kört!", Toast.LENGTH_SHORT).show();
        }
        else if (sajatValasztasVal == "ollo" && gepiValasztasVal == "papir")
        {
            gepEletLevon();
            Toast.makeText(MainActivity.this, "A játékos nyerte a kört!", Toast.LENGTH_SHORT).show();
        }
        else if (sajatValasztasVal == "ko" && gepiValasztasVal == "papir")
        {
            jatekosEletLevon();
            Toast.makeText(MainActivity.this, "A gép nyerte a kört!", Toast.LENGTH_SHORT).show();
        }
        else if (sajatValasztasVal == "papir" && gepiValasztasVal == "ollo")
        {
            jatekosEletLevon();
            Toast.makeText(MainActivity.this, "A gép nyerte a kört!", Toast.LENGTH_SHORT).show();
        }
        else if (sajatValasztasVal == "ollo" && gepiValasztasVal == "ko")
        {
            jatekosEletLevon();
            Toast.makeText(MainActivity.this, "A gép nyerte a kört!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            dontetlenPontszam++;
        }
        dontetlenKijelzo.setText(String.valueOf("Döntetlen körök száma: " + dontetlenPontszam));


    }

    private void jatekosEletLevon() {

        switch (jatekosElet){
            case 3:
                jatekosHp1.setImageResource(R.drawable.heart1);
                break;
            case 2:
                jatekosHp2.setImageResource(R.drawable.heart1);
                break;
            case 1:
                jatekosHp3.setImageResource(R.drawable.heart1);
                break;
            default:
                break;
        }
        jatekosElet--;

    }

    private void gepEletLevon()
    {
        switch (gepElet){
            case 3:
                gepHp3.setImageResource(R.drawable.heart1);
                break;
            case 2:
                gepHp2.setImageResource(R.drawable.heart1);
                break;
            case 1:
                gepHp1.setImageResource(R.drawable.heart1);
                break;
            default:
                break;
        }
        gepElet--;
    }

    private void jatekVege() {
        if (gepElet == 0)
        {
            builder.setTitle("Nyertél!").create().show();
        }
        if (jatekosElet == 0)
        {
            builder.setTitle("Vesztettél!").create().show();
        }
    }

    private void ujJatek() {
        jatekosElet = 3;
        gepElet = 3;
        dontetlenPontszam = 0;
        dontetlenKijelzo.setText(String.valueOf("Döntetlen körök száma: " + dontetlenPontszam));
        jatekosHp1.setImageResource(R.drawable.heart2);
        jatekosHp2.setImageResource(R.drawable.heart2);
        jatekosHp3.setImageResource(R.drawable.heart2);
        gepHp1.setImageResource(R.drawable.heart2);
        gepHp2.setImageResource(R.drawable.heart2);
        gepHp3.setImageResource(R.drawable.heart2);

    }

    private  void kilépés() {
        System.exit(0);
    }



}