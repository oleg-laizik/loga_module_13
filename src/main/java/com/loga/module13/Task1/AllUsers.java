package com.loga.module13.Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import UtilsUser.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AllUsers {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/users";

    public static void main(String[] args) {
        List<User> users = getDataFromApi(BASE_URL);
        System.out.println(users);
    }

    private static List<User> getDataFromApi(String urlString) {
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
        return new Gson().fromJson(result.toString(), new TypeToken<List<User>>(){}.getType());
    }
}
