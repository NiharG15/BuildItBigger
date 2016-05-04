package com.niharg.joketeller.backend;

/**
 * Created by niharg on 5/4/16 at 2:51 PM.
 */
public class Joke {

    private String joke;

    public Joke() {
    }

    public Joke(String joke) {
        this.joke = joke;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }
}
