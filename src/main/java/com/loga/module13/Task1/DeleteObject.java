package com.loga.module13.Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import UtilsUser.User;

public class DeleteObject {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final Gson gson = new GsonBuilder().create();

    public static User deleteUser(int userId) throws IOException {
        URL url = new URL(BASE_URL + "/users/" + userId);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("DELETE");
        conn.setRequestProperty("Accept", "application/json");

        int status = conn.getResponseCode();
        if (status >= 200 && status < 300) {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            User deletedUser = gson.fromJson(response.toString(), User.class);
            return deletedUser;
        } else {
            throw new RuntimeException("Error " + status);
        }
    }

}

