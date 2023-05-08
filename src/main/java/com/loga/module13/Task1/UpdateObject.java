package com.loga.module13.Task1;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import UtilsUser.User;

public class UpdateObject {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/users/";

    public User updateUser(int userId, User updatedUser) throws Exception {
        URL url = new URL(BASE_URL + userId);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
        Gson gson = new Gson();
        String requestBody = gson.toJson(updatedUser);
        writer.write(requestBody);
        writer.flush();

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Error : "
                    + conn.getResponseCode());
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));
        String responseBody = reader.readLine();
        reader.close();

        conn.disconnect();

        User user = gson.fromJson(responseBody, User.class);
        return user;
    }
}
