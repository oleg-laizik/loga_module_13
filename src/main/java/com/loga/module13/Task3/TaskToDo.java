package com.loga.module13.Task3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TaskToDo {

    public static void main(String[] args) throws IOException {
        int userId = 1;
        String urlString = "https://jsonplaceholder.typicode.com/users/" + userId + "/todos";
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        int status = con.getResponseCode();
        if (status == 200) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();
            String json = content.toString();
            System.out.println("Відкриті задачі " + userId + ":");
            com.google.gson.JsonArray jsonArray = new com.google.gson.JsonParser().parse(json).getAsJsonArray();
            for (com.google.gson.JsonElement jsonElement : jsonArray) {
                com.google.gson.JsonObject jsonObject = jsonElement.getAsJsonObject();
                boolean completed = jsonObject.get("completed").getAsBoolean();
                if (!completed) {
                    int id = jsonObject.get("id").getAsInt();
                    String title = jsonObject.get("title").getAsString();
                    System.out.println("ID: " + id + ", Title: " + title);
                }
            }
        } else {
            System.out.println("Помилка" + status);
        }
    }

}