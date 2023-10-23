package app;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Main {

    private static final String API_URL = "http://api.nstack.in/v1/todos?page=1&limit=10";

    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                System.err.println("Failed to fetch data: " + response.code());
                return;
            }
            String responseBody = response.body().string();
            System.out.println("API Response:");
            System.out.println(responseBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}