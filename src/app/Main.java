package app;

import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Main {


    //private static final String API_URL = "http://api.nstack.in/v1/todos?page=1&limit=10";

    public static void main(String[] args) {

//        OkHttpClient client = new OkHttpClient();
//
//        Request request = new Request.Builder()
//                .url(API_URL)
//                .build();
//
//        try (Response response = client.newCall(request).execute()) {
//            if (!response.isSuccessful()) {
//                System.err.println("Failed to fetch data: " + response.code());
//                return;
//            }
//            String responseBody = response.body().string();
//            System.out.println("API Response:");
//            System.out.println(responseBody);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n\t\"description\": \"reading book\"\n}");
        Request request = new Request.Builder()
                .url("https://api-nodejs-todolist.herokuapp.com/task")
                .method("POST", body)
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ZGRjY2JlYzZiNTVkYTAwMTc1OTcyMmMiLCJpYXQiOjE1NzQ3NTE2ODh9.GPbsl9FLX4VrsGVErodiXypjuz1us4tfD0jwg2_UrzY")
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            JSONObject responseBody = new JSONObject(response.body());
            System.out.println(responseBody);

            if (responseBody.equals("HTTP 201")) {
                //Task is created successfully

            } else if (responseBody.equals("HTTP 404 ")) {
                // Title not unique
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }


    }
}