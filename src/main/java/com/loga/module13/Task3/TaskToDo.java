package com.loga.module13.Task3;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

import com.google.gson.Gson;


public class TaskToDo {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final Gson gson = new Gson();

    public static void main(String[] args) throws IOException {
        int userId = 1;
        String fileName = "user-" + userId + "-todos.json";
        ToDo[] openTodos = getOpenTodosForUser(userId);
        writeJsonToFile(gson.toJson(openTodos), fileName);
        System.out.println("Open " + userId + " were written to file " + fileName);
    }

    private static ToDo[] getOpenTodosForUser(int userId) throws IOException {
        String endpoint = BASE_URL + "/users/" + userId + "/todos";
        String responseJson = makeRequest(endpoint);
        ToDo[] todos = gson.fromJson(responseJson, ToDo[].class);
        ToDo[] openTodos = new ToDo[todos.length];
        int openTodosCount = 0;
        for (ToDo todo : todos) {
            if (!todo.isCompleted()) {
                openTodos[openTodosCount++] = todo;
            }
        }
        return Arrays.copyOf(openTodos, openTodosCount);
    }

    private static void writeJsonToFile(String json, String fileName) throws IOException {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(json);
        }
    }

    private static String makeRequest(String endpoint) throws IOException {
        URL url = new URL(endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.connect();
        InputStream inputStream = connection.getInputStream();
        Scanner scanner = new Scanner(inputStream, "UTF-8");
        String response = scanner.useDelimiter("\\A").next();
        scanner.close();
        return response;
    }
}





