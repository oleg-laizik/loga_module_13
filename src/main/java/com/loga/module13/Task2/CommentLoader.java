package com.loga.module13.Task2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import com.google.gson.Gson;


public class CommentLoader {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final Gson gson = new Gson();

    public static void main(String[] args) throws IOException {
        int userId = 1;
        int postId = getLatestPostId(userId);
        String fileName = "user-" + userId + "-post-" + postId + "-comments.json";
        String commentsJson = getCommentsJsonForPost(postId);
        writeJsonToFile(commentsJson, fileName);
        System.out.println("Comments for user " + userId + " post " + postId + " were written to file " + fileName);
    }

    private static int getLatestPostId(int userId) throws IOException {
        String endpoint = BASE_URL + "/users/" + userId + "/posts";
        String responseJson = makeRequest(endpoint);
        Post[] posts = gson.fromJson(responseJson, Post[].class);
        Post latestPost = posts[0];
        for (Post post : posts) {
            if (post.getId() > latestPost.getId()) {
                latestPost = post;
            }
        }
        return latestPost.getId();
    }

    private static String getCommentsJsonForPost(int postId) throws IOException {
        String endpoint = BASE_URL + "/posts/" + postId + "/comments";
        return makeRequest(endpoint);
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
