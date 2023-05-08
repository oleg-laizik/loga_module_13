package com.loga.module13.Task1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import UtilsUser.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;

public class UserName {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/users?username=";

    public static User getUserByUsername(String username) throws Exception {
        URL url = new URL(BASE_URL + username);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        List<User> users = new Gson().fromJson(response.toString(), new TypeToken<List<User>>(){}.getType());
        if (users != null && !users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }
}

