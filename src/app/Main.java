
package app;

import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.IngredientInputData;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String app_id = "3e1540e3";
        String app_key = "11c5a456d1c9fd43e0e1166d3fef7d53";
        String ingr = "sugar";

        OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
        Request request = new Request.Builder()
                    .url(String.format("https://api.edamam.com/api/nutrition-data?app_id=%s&app_key=%s&ingr=%s",
                            app_id, app_key, ingr))
                    .build();
            try {
                Response response = client.newCall(request).execute();
                System.out.println(response);
                JSONObject responseCode = new JSONObject(response.code());

                if (responseCode.toString() == "200") {
                    //Task is created successfully

                } else if (responseCode.toString() == "404") {
                    // Title not unique
                }
            } catch (IOException | JSONException e) {
                throw new RuntimeException(e);
            }
        }

}