package use_case;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class TodolistInteractor implements TodolistInputBoundary {

    public void creatTodoList(TodolistInputData todolistInputData) {
        String task_name = todolistInputData.getTask_name();
        String description = todolistInputData.getDescription();
        String date = todolistInputData.getDeadline();


        //RequestBody body = RequestBody.create(mediaType, "{\n\t\"description\": \"reading book\"\n}");
        // {\n\t\"Title\": \"" + task_name + "\",\n\t\"Description\": \""+ description+"\"," +
        //                        "\n\t\"DueDate\": \""+ date +"\" \n}");


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

//        {
//	"Title": "Create API Documentation",
//	"Description": "The document will be used as a reference",
//	"DueDate": "2020-05-15"
//}
    }
}





