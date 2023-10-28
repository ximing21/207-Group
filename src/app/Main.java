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
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType,
                "{\n\t\"Title\": \"" + "task_name " + "\",\n\t\"Description\": \"" + "description" + "\"," +
                        "\n\t\"DueDate\": \"" + "date" + "\" \n}");
        Request request = new Request.Builder()
                .url("http://localhost:5000/create")
                .method("POST", body)
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            JSONObject responseBody = new JSONObject(response.body().string());
            System.out.println(responseBody);

            if (responseBody.equals("HTTP 201")) {
                //Task is created successfully

            } else if (responseBody.equals("HTTP 409 ")) {
                // Title not unique
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }


    }
}