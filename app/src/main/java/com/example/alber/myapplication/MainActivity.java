package com.example.alber.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Random cpuRandom = new Random();
    TextView titleLabel;
    TextView humanoScore;
    TextView cpuScore;
    Button jogarButton;
    Button novamenteButton;
    Button sairButton;
    Button pedraButton;
    Button papelButton;
    Button tesouraButton;
    int humano = 0;
    int cpu = 0;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initGame();
    }

    protected void initGame() {
        initVars();
        setButtons();
        hideElement(pedraButton);
        hideElement(papelButton);
        hideElement(tesouraButton);
        hideElement(novamenteButton);
        hideElement(sairButton);
    }

    protected void setButtons() {
        jogarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });
        novamenteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                novamente();
            }
        });
        sairButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sair();
            }
        });
        pedraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escolherMao(1);
            }
        });
        papelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escolherMao(2);
            }
        });
        tesouraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escolherMao(3);
            }
        });
    }

    protected void escolherMao(int escolha) {
        int cpuEscolha = cpuRandom.nextInt(2) + 1;
        if (escolha == 1) {
            if (cpuEscolha == 3) {
                jogadorGanhou();
            } else if (cpuEscolha == 2) {
                cpuGanhou();
            } else {
                endCurrentGame(true, 0);
            }
        } else if (escolha == 2) {
            if (cpuEscolha == 1) {
                jogadorGanhou();
            } else if (cpuEscolha == 3) {
                cpuGanhou();
            } else {
                endCurrentGame(true, 0);
            }
        } else if (escolha == 3) {
            if (cpuEscolha == 2) {
                jogadorGanhou();
            } else if (cpuEscolha == 1) {
                cpuGanhou();
            } else {
                endCurrentGame(true, 0);
            }
        }
    }

    protected void jogadorGanhou() {
        setHumanoScore(humano + 1);
        endCurrentGame(false, 1);
    }

    protected void cpuGanhou() {
        setCpuScore(cpu + 1);
        endCurrentGame(false, 2);
    }

    protected void endCurrentGame(boolean empate, int ganhador) {
        if (empate) {
            titleLabel.setText("Empate. Ninguem ganhou essa rodada.");
        } else {
            String textoGanhador = "Jogador";
            if (ganhador == 2)
                textoGanhador = "CPU";
            titleLabel.setText(textoGanhador + " ganhou essa rodada.");
        }
        hideElement(pedraButton);
        hideElement(papelButton);
        hideElement(tesouraButton);
        showElement(novamenteButton);
        showElement(sairButton);
    }

    protected void startGame() {
        titleLabel.setText("Escolha sua mao");
        setHumanoScore(0);
        setCpuScore(0);
        hideElement(jogarButton);
        showElement(pedraButton);
        showElement(papelButton);
        showElement(tesouraButton);
    }

    protected void novamente() {
        titleLabel.setText("Escolha sua mao");
        showElement(titleLabel);
        showElement(pedraButton);
        showElement(papelButton);
        showElement(tesouraButton);
        hideElement(novamenteButton);
        hideElement(sairButton);
    }

    protected void sair() {
        setHumanoScore(0);
        setCpuScore(0);
        titleLabel.setText("Bem vindo ao super Pedra, Papel e Tesoura");
        showElement(titleLabel);
        showElement(jogarButton);
        hideElement(pedraButton);
        hideElement(papelButton);
        hideElement(novamenteButton);
        hideElement(sairButton);
    }

    protected void initVars() {
        titleLabel = (TextView) findViewById(R.id.titleLabel);
        humanoScore = (TextView) findViewById(R.id.humanoScore);
        cpuScore = (TextView) findViewById(R.id.cpuScore);
        jogarButton = (Button) findViewById(R.id.jogarButton);
        novamenteButton = (Button) findViewById(R.id.novamenteButton);
        sairButton = (Button) findViewById(R.id.sairButton);
        pedraButton = (Button) findViewById(R.id.pedraButton);
        papelButton = (Button) findViewById(R.id.papelButton);
        tesouraButton = (Button) findViewById(R.id.tesouraButton);
    }

    protected void setHumanoScore(int score) {
        humano = score;
        humanoScore.setText("Humano: " + humano);
    }

    protected void setCpuScore(int score) {
        cpu = score;
        cpuScore.setText(cpu + " :CPU");
    }

    protected void hideElement(View element) {
        element.setVisibility(View.INVISIBLE);
    }

    protected void showElement(View element) {
        element.setVisibility(View.VISIBLE);
    }
}