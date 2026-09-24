package com.example.quizbandeiras;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RankingActivity extends AppCompatActivity {

    private TextView tvNomeUsuario, tvPontuacao;
    private Button btnResponderNovamente, btnTelaPrincipal;
    private String nomeUsuario = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        tvNomeUsuario = findViewById(R.id.tvNomeUsuario);
        tvPontuacao = findViewById(R.id.tvPontuacao);
        btnResponderNovamente = findViewById(R.id.btnResponderNovamente);
        btnTelaPrincipal = findViewById(R.id.btnTelaPrincipal);

        Intent intent = getIntent();
        if (intent != null) {
            // Captura o nome
            if (intent.hasExtra("EXTRA_NOME")) {
                nomeUsuario = intent.getStringExtra("EXTRA_NOME");
            } else if (intent.hasExtra("NOME_USUARIO")) {
                nomeUsuario = intent.getStringExtra("NOME_USUARIO");
            }

            // Captura a pontuação
            int acertos = 0;
            if (intent.hasExtra("EXTRA_ACERTOS")) {
                acertos = intent.getIntExtra("EXTRA_ACERTOS", 0);
            } else if (intent.hasExtra("PONTUACAO")) {
                acertos = intent.getIntExtra("PONTUACAO", 0);
            }

            if (nomeUsuario != null && !nomeUsuario.isEmpty()) {
                tvNomeUsuario.setText(nomeUsuario);
            }
            tvPontuacao.setText(String.valueOf(acertos));
        }

        // Botão "RESPONDER NOVAMENTE" -> Reinicia o quiz na 1ª pergunta
        btnResponderNovamente.setOnClickListener(v -> {
            Intent intentQuiz = new Intent(RankingActivity.this, QuestionActivity.class);
            intentQuiz.putExtra("EXTRA_NOME", nomeUsuario);
            intentQuiz.putExtra("EXTRA_INDICE", 0);
            intentQuiz.putExtra("EXTRA_ACERTOS", 0);
            startActivity(intentQuiz);
            finish();
        });

        // Botão "TELA PRINCIPAL" -> Retorna para a MainActivity
        btnTelaPrincipal.setOnClickListener(v -> {
            Intent intentInicio = new Intent(RankingActivity.this, MainActivity.class);
            intentInicio.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intentInicio);
            finish();
        });
    }
}