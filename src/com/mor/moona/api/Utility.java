package com.mor.moona.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Utility {

    public static void printResponse(JsonObject response) {
        if (response != null) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            System.out.println(gson.toJson(response));
        }
    }

    public Boolean writeJokeToFile(String categorySelected) {
        Joke jokeOfDay = new JokesStore().getJokeOfTheDay(categorySelected);
        try {
            File jokeFile = new File("C:\\temp\\joke.txt");
            FileOutputStream fileOut = new FileOutputStream(jokeFile);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(jokeOfDay);
            objectOut.close();
            System.out.println("The Object  was successfully written to a file");
            return true;
        } catch (Exception ex) {
            System.out.println("The write process has failed");
            ex.getMessage();
            return false;
        }
    }

}
