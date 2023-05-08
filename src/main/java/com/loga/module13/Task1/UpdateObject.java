package com.loga.module13.Task1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class UpdateObject {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    public static void main(String[] args) {
        updateUser(1);
    }

    public static void updateUser(int userId) {
        try {
            URL url = new URL(BASE_URL + "/users/" + userId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);

            String jsonInputString = "{\"id\": 1,\"name\": \"John Doe\",\"username\": \"johndoe\",\"email\": \"johndoe@example.com\"}";

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

