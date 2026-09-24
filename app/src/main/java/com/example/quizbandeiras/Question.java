package com.example.quizbandeiras;

public class Question {

    private final int bandeiraResId;
    private final String[] opcoes;
    private final int indiceCorreto;

    public Question(int bandeiraResId, String[] opcoes, int indiceCorreto) {
        this.bandeiraResId = bandeiraResId;
        this.opcoes = opcoes;
        this.indiceCorreto = indiceCorreto;
    }

    public int getBandeiraResId() {
        return bandeiraResId;
    }

    public String[] getOpcoes() {
        return opcoes;
    }

    public int getIndiceCorreto() {
        return indiceCorreto;
    }
}