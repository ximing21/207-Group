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

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType,
                "{\n\t\"Title\": \"" + task_name + "\",\n\t\"Description\": \""+ description+"\"," +
                        "\n\t\"DueDate\": \""+ date +"\" \n}");
        Request request = new Request.Builder()
                .url("localhost:5000/create")
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

//        {
//	"Title": "Create API Documentation",
//	"Description": "The document will be used as a reference",
//	"DueDate": "2020-05-15"
//}
    }
}





