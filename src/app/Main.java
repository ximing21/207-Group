
package app;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;

public class Main {

        private static final String API_TOKEN = System.getenv("API_TOKEN");

        public static String getApiToken() {
            return API_TOKEN;
        }

    public static void main(String[] args) {

        String name = "207";

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
//        MediaType mediaType = MediaType.parse("application/json");
//        JSONObject requestBody = new JSONObject();
//        requestBody.put("name", name);
//        RequestBody body = RequestBody.create(mediaType, requestBody.toString());
        Request request = new Request.Builder()
                .url("https://api.todoist.com/rest/v2/projects")
//                .method("POST", body)
                .addHeader("Authorization", API_TOKEN )
                .addHeader("Content-Type", "application/json")
//                .addHeader("Content-Type", "qwerty")
                .build();
        try { Response response = client.newCall(request).execute();
             System.out.println(response.code());

            JSONArray responseBody = new JSONArray(response.body().string());
//            JSONObject responseBody = new JSONObject(response.body().string());
//            if (responseBody.code == 200) {
//
//            } else {
//                throw new RuntimeException(responseBody.getString("message"));
//            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }

    }


}
//    public static void main(String[] args) {
//        String app_id = "3e1540e3";
//        String app_key = "11c5a456d1c9fd43e0e1166d3fef7d53";
//        String ingr = "sugar";
//
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                    .build();
//        Request request = new Request.Builder()
//                    .url(String.format("https://api.edamam.com/api/nutrition-data?app_id=%s&app_key=%s&ingr=%s",
//                            app_id, app_key, ingr))
//                    .build();
//            try {
//                Response response = client.newCall(request).execute();
//                System.out.println(response);
//                JSONObject responseCode = new JSONObject(response.code());
//
//                if (responseCode.toString() == "200") {
//                    //Task is created successfully
//
//                } else if (responseCode.toString() == "404") {
//                    // Title not unique
//                }
//            } catch (IOException | JSONException e) {
//                throw new RuntimeException(e);
//            }
//        }

