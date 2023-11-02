package use_case;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class TodolistInteractor implements TodolistInputBoundary {
    private static final String app_id = "3e1540e3";
    private static final String app_key = "11c5a456d1c9fd43e0e1166d3fef7d53";


    public void creatTodoList(TodolistInputData ingredientInputData) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        String ingr = ingredientInputData.getIngredient();
        Request request = new Request.Builder()
                .url("https://api.edamam.com/api/nutrition-data")
                .addHeader("app_id", app_id)
                .addHeader("app_key", app_key)
                .addHeader("ingr", ingr)
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt("status_code") == 200) {
                //Task is created successfully

            } else if (responseBody.getInt("status_code") == 404) {
                // Title not unique
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
}





