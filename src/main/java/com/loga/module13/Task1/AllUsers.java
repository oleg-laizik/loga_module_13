package com.loga.module13.Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

import UtilsUser.User;
import com.google.gson.Gson;

public class AllUsers {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/users";

    public static void main(String[] args) {
        String usersJson = getDataFromApi(BASE_URL);
        User[] users = new Gson().fromJson(usersJson, User[].class);
        System.out.println(Arrays.toString(users));
    }

    private static String getDataFromApi(String urlString) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}

