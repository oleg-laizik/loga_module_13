package com.loga.module13.Task1;

import java.net.HttpURLConnection;
import java.net.URL;

public class DeleteObject {


        private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

        public static void main(String[] args) {
            deleteUser(1);
        }

        public static void deleteUser(int userId) {
            try {
                URL url = new URL(BASE_URL + "/users/" + userId);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("DELETE");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Accept", "application/json");

                int status = conn.getResponseCode();
                if (status >= 200 && status < 300) {
                    System.out.println("User з id " + userId + " було успішно видалено");
                } else {
                    System.out.println("Помилка видалення користувача з id " + userId + ". Статус: " + status);
                }

                conn.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

