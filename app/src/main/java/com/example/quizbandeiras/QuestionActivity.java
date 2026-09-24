package com.example.quizbandeiras;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity {

    private String nome;
    private int indice;
    private int acertos;

    private TextView tvProgresso;
    private ImageView ivBandeira;
    private RadioGroup rgOpcoes;
    private Button btnResponder;

    // Letras usadas para identificar as alternativas
    private static final String[] LETRAS = {"A", "B", "C", "D"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        // Recupera os dados enviados pela tela anterior
        Intent intent = getIntent();
        if (intent != null) {
            nome = intent.getStringExtra("EXTRA_NOME");
            indice = intent.getIntExtra("EXTRA_INDICE", 0);
            acertos = intent.getIntExtra("EXTRA_ACERTOS", 0);
        }

        // Associa as variáveis do Java aos IDs do XML
        tvProgresso = findViewById(R.id.tvProgresso);
        ivBandeira = findViewById(R.id.ivBandeira);
        rgOpcoes = findViewById(R.id.rgOpcoes);
        btnResponder = findViewById(R.id.btnResponder);

        // Limpa qualquer seleção anterior por segurança
        rgOpcoes.clearCheck();

        // Carrega a pergunta atual com base no índice
        final Question pergunta = QuizData.PERGUNTAS.get(indice);

        tvProgresso.setText("Pergunta " + (indice + 1) + " de " + QuizData.PERGUNTAS.size());
        ivBandeira.setImageResource(pergunta.getBandeiraResId());

        // Preenche o texto das opções dinamicamente
        int[] ids = {R.id.rbOpcao1, R.id.rbOpcao2, R.id.rbOpcao3, R.id.rbOpcao4};
        for (int i = 0; i < ids.length; i++) {
            RadioButton rb = findViewById(ids[i]);
            if (rb != null) {
                // Ex: "A) Honduras"
                rb.setText(LETRAS[i] + ") " + pergunta.getOpcoes()[i]);
            }
        }

        // 1. Garante que o botão começa desativado
        btnResponder.setEnabled(false);

        // 2. Habilita o botão apenas quando o utilizador escolhe uma opção
        rgOpcoes.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId != -1) {
                btnResponder.setEnabled(true);
            }
        });

        // 3. Ação do clique no botão "RESPONDER"
        btnResponder.setOnClickListener(v -> {
            int checkedId = rgOpcoes.getCheckedRadioButtonId();
            int respostaSelecionada = -1;

            // Descobre qual foi a opção escolhida (0 a 3)
            for (int i = 0; i < ids.length; i++) {
                if (ids[i] == checkedId) {
                    respostaSelecionada = i;
                    break;
                }
            }

            // FUNÇÃO 1: Contabiliza o acerto
            if (respostaSelecionada == pergunta.getIndiceCorreto()) {
                acertos++;
            }

            // FUNÇÃO 2: Decide se vai para a próxima pergunta ou para o Ranking
            if (indice + 1 < QuizData.PERGUNTAS.size()) {
                Intent proxIntent = new Intent(QuestionActivity.this, QuestionActivity.class);
                proxIntent.putExtra("EXTRA_NOME", nome);
                proxIntent.putExtra("EXTRA_INDICE", indice + 1);
                proxIntent.putExtra("EXTRA_ACERTOS", acertos);
                startActivity(proxIntent);
            } else {
                Intent rankingIntent = new Intent(QuestionActivity.this, RankingActivity.class);
                rankingIntent.putExtra("EXTRA_NOME", nome);
                rankingIntent.putExtra("EXTRA_ACERTOS", acertos);
                startActivity(rankingIntent);
            }

            // Fecha a tela atual para não acumular na memória
            finish();
        });
    }
}