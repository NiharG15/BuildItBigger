package com.niharg.jokelib;

import java.util.Calendar;
import java.util.Random;

public class JokeLib {

    private static String[] jokes = {"Q: Why did the scarecrow win the Nobel Prize? \nA: He was out standing in his field.",
    "An elephant walks into a pub and says \"Large scotch please barman.\"\n" +
            "\"You again\" says the bar man \"Why do you drink so much?\"\n" +
            "The elephant replies \"I do it to forget.\"" };

    public static String getJoke() {
        Random random = new Random(Calendar.getInstance().getTimeInMillis());
        return jokes[random.nextInt(2)];
    }

}
