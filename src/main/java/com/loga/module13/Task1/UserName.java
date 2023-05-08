package com.loga.module13.Task1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class UserName {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    public static void main(String[] args) {
        getUserByUsername("Bret");
    }

    public static void getUserByUsername(String username) {
        try {
            String encodedUsername = URLEncoder.encode(username, "UTF-8");
            URL url = new URL(BASE_URL + "/users?username=" + encodedUsername);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

