package com.mor.moona.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Joke implements Serializable {

    //default serialVersion id
    private static final long serialVersionUID = 1L;

    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("background")
    @Expose
    private String background;
    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("jokeTitle")
    @Expose
    private String jokeTitle;

    @SerializedName("jokeID")
    @Expose
    private String jokeID;

    @SerializedName("jokeText")
    @Expose
    private String jokeText;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Joke withLanguage(String language) {
        this.language = language;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Joke withName(String name) {
        this.name = name;
        return this;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public Joke withBackground(String background) {
        this.background = background;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Joke withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getJokeTitle() {
        return jokeTitle;
    }

    public void setJokeTitle(String jokeTitle) {
        this.jokeTitle = jokeTitle;
    }

    public Joke withJokeTitle(String jokeTitle) {
        this.jokeTitle = jokeTitle;
        return this;
    }

    public String getJokeID() {
        return jokeID;
    }

    public void setJokeID(String jokeID) {
        this.jokeID = jokeID;
    }

    public Joke withJokeID(String jokeID) {
        this.jokeID = jokeID;
        return this;
    }

    public String getJokeText() {
        return jokeText;
    }

    public void setJokeText(String jokeText) {
        this.jokeText = jokeText;
    }

    public Joke withJokeText(String jokeText) {
        this.jokeText = jokeText;
        return this;
    }

}