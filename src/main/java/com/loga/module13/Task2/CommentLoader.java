package com.loga.module13.Task2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class CommentLoader {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    public static void main(String[] args) throws IOException {
        int userId = 1;
        int postId = getLatestPostIdForUser(userId);
        String fileName = "user-" + userId + "-post-" + postId + "-comments.json";
        JSONArray comments = getCommentsForPost(postId);
        saveCommentsToFile(fileName, comments);
    }

    private static int getLatestPostIdForUser(int userId) throws IOException {
        String endpoint = BASE_URL + "/users/" + userId + "/posts";
        JSONArray posts = getJsonArrayFromEndpoint(endpoint);
        JSONObject latestPost = posts.getJSONObject(posts.length() - 1);
        return latestPost.getInt("id");
    }

    private static JSONArray getCommentsForPost(int postId) throws IOException {
        String endpoint = BASE_URL + "/posts/" + postId + "/comments";
        return getJsonArrayFromEndpoint(endpoint);
    }

    private static JSONArray getJsonArrayFromEndpoint(String endpoint) throws IOException {
        URL url = new URL(endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        InputStream inputStream = connection.getInputStream();
        String responseBody = new Scanner(inputStream, "UTF-8").useDelimiter("\\A").next();

        return new JSONArray(responseBody);
    }

    private static void saveCommentsToFile(String fileName, JSONArray comments) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write(comments.toString());
        fileWriter.close();
    }

    private static void getCommentsAndSaveToFile(int userId, String fileName) throws IOException {
        int postId = getLatestPostIdForUser(userId);
        JSONArray comments = getCommentsForPost(postId);
        saveCommentsToFile(fileName, comments);
    }
}