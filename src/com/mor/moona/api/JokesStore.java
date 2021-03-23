package com.mor.moona.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JokesStore {
    public final String URI = "https://jokes.p.rapidapi.com/";

    public Boolean getMissingJoke(String id) {
        JsonObject response = getJsonObject(getRestActionById("jod", id).getBody().asString());
        Utility.printResponse(response);
        int code = Integer.parseInt(response.get("error").getAsJsonObject().get("code").getAsString());
        String msg = response.get("error").getAsJsonObject().get("message").getAsString();
        return code == 404 && msg.equalsIgnoreCase("Not Found");
    }

    public List<Joke> getJokesOptionList(String id) {
        Response response = getRestActionById("jod", id);
        if (response.getStatusCode() == 200) {
            JsonObject results = getJsonObject(response.getBody().asString());
            Utility.printResponse(results);
            JsonArray jokesOptions = results.get("contents").getAsJsonObject().get("categories").getAsJsonArray(); //Convert the result to JsonArray
            ObjectMapper mapper = new ObjectMapper();
            try {
                return Arrays.asList(mapper.readValue(jokesOptions.toString(), Joke[].class));  //get result as a List
            } catch (IOException e) {
                System.out.println("Cannot convert 'JsonArray' to 'ArrayList' according to I/O issue");
                e.getMessage();
                return null;
            }
        } else {
            System.out.println("Status code != 200");
            return null;
        }
    }

    public Joke getJokeOfTheDay(String categorySelected) {
        Response response = getRestActionWithParameters("jod", categorySelected);
        if (response.getStatusCode() == 200) {
            JsonObject results = getJsonObject(response.getBody().asString());
            Utility.printResponse(results);
            JsonObject jokes = results.get("contents").getAsJsonObject().get("jokes").getAsJsonArray().get(0).getAsJsonObject();
            return convertToJokeObj(jokes);
        } else {
            System.out.println("Status code != 200");
            return null;
        }
    }

    private Joke convertToJokeObj(JsonObject joke) {
        return new Joke()
                .withDescription(joke.get("description").getAsString())
                .withName(joke.get("category").getAsString())
                .withJokeTitle(joke.get("joke").getAsJsonObject().get("title").getAsString())
                .withJokeID(joke.get("joke").getAsJsonObject().getAsJsonObject().get("id").getAsString())
                .withJokeText(joke.get("joke").getAsJsonObject().getAsJsonObject().get("text").getAsString());
    }

    private JsonObject getJsonObject(String s) {
        return new Gson().fromJson(s, JsonObject.class);
    }

    private Response getRestActionById(String endpoint, String id) {
        initRestAssured(endpoint);
        return RestAssured
                .given()
                .basePath(endpoint + "/" + id)
                .contentType(ContentType.JSON)
                .header("accept", "application/json")
                .header("x-rapidapi-host", "jokes.p.rapidapi.com")
                .header("x-rapidapi-key", "56d7a4653emsh4c19b463b18e6b7p144eb7jsn030e478c59b2")
                .get();
    }

    private Response getRestActionWithParameters(String endpoint, String parameter) {
        initRestAssured(endpoint);
        return RestAssured
                .given()
                .basePath(endpoint)
                .contentType(ContentType.JSON)
                .header("accept", "application/json")
                .header("x-rapidapi-host", "jokes.p.rapidapi.com")
                .header("x-rapidapi-key", "56d7a4653emsh4c19b463b18e6b7p144eb7jsn030e478c59b2")
                .params("category",parameter)
                .get();
    }

    private void initRestAssured(String endpoint) {
        RestAssured.baseURI = URI;
        RestAssured.basePath = endpoint;
        RestAssured.config = RestAssured.config().sslConfig(SSLConfig.sslConfig().allowAllHostnames());
        RestAssured.useRelaxedHTTPSValidation();
    }
}
