package com.example.quizbandeiras;

import java.util.Arrays;
import java.util.List;

public class QuizData {

    public static final List<Question> PERGUNTAS = Arrays.asList(
            new Question(R.drawable.argentina,
                    new String[]{"Honduras", "Nicarágua", "Argentina", "El Salvador"}, 2),
            new Question(R.drawable.brasil,
                    new String[]{"Iraque", "Brasil", "Granada", "Guiana"}, 1),
            new Question(R.drawable.chipre,
                    new String[]{"Malta", "Geórgia", "Israel", "Chipre"}, 3),
            new Question(R.drawable.espanha,
                    new String[]{"Espanha", "Portugal", "Bolívia", "Andorra"}, 0),
            new Question(R.drawable.inglaterra,
                    new String[]{"Dinamarca", "Suíça", "Inglaterra", "Geórgia"}, 2),
            new Question(R.drawable.gales,
                    new String[]{"País de Gales", "Butão", "Bulgária", "Irlanda"}, 0),
            new Question(R.drawable.croacia,
                    new String[]{"Rússia", "Croácia", "Sérvia", "Eslovênia"}, 1),
            new Question(R.drawable.italia,
                    new String[]{"Irlanda", "Hungria", "México", "Itália"}, 3),
            new Question(R.drawable.noruega,
                    new String[]{"Islândia", "Noruega", "Dinamarca", "Finlândia"}, 1),
            new Question(R.drawable.uruguai,
                    new String[]{"Uruguai", "Grécia", "Costa Rica", "Cuba"}, 0)
    );
}