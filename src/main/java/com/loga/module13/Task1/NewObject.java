package com.loga.module13.Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import UtilsUser.User;


public class NewObject {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    public static void main(String[] args) throws IOException {
        User newUser = createUser("Oleg One", "olegone@example.com");
        System.out.println("New user created: " + newUser);
    }

    public static User createUser(String name, String email) throws IOException {
        URL url = new URL(BASE_URL + "/users");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        String jsonInputString = "{"
                + "\"name\": \"" + name + "\","
                + "\"email\": \"" + email + "\""
                + "}";

        conn.getOutputStream().write(jsonInputString.getBytes());

        int responseCode = conn.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_CREATED) {
            throw new RuntimeException("Error : " + responseCode);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

        StringBuilder responseBuilder = new StringBuilder();
        String output;
        while ((output = br.readLine()) != null) {
            responseBuilder.append(output);
        }

        conn.disconnect();

        Gson gson = new Gson();
        return gson.fromJson(responseBuilder.toString(), User.class);
    }
}
