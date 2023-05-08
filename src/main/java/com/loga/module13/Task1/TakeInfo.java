package com.loga.module13.Task1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import UtilsUser.User;
import com.google.gson.Gson;

public class TakeInfo {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/users/";

    public static User getUserById(int id) {
        User user = null;
        try {
            URL url = new URL(BASE_URL + id);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                Gson gson = new Gson();
                user = gson.fromJson(response.toString(), User.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

}
