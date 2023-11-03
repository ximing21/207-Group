package api;

import entity.Project;
import entity.Task;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.add_project.AddProjectDataAccessInterface;

import java.io.IOException;

public class TodoistDB implements TodolistDB, AddProjectDataAccessInterface {
    private static final String API_TOKEN = System.getenv("API_TOKEN");

    public static String getApiToken() {
        return API_TOKEN;
    }


    @Override
    public void createProject(String name) throws JSONException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", name);
        RequestBody body = RequestBody.create(mediaType, requestBody.toString());
        Request request = new Request.Builder()
                .url("https://api.todoist.com/rest/v2/projects")
                .method("POST", body)
                .addHeader("Authorization", API_TOKEN)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());
            if (response.code() == 200) {
            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Project updateProject() {
        return null;
    }

    @Override
    public Project[] getProject() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://api.todoist.com/rest/v2/projects")
                .addHeader("Authorization", API_TOKEN)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response.body());
            JSONArray responseBody = new JSONArray(response.body().string());
            if (response.code() == 200) {
                Project[] projects = new Project[responseBody.length()];
                for (int i = 0; i < responseBody.length(); i++) {
                    JSONObject element = responseBody.getJSONObject(i);
                    Project project = Project.builder()
                            .name(element.getString("name"))
                            .color(element.getString("color"))
                            .is_favourite(element.getBoolean("is_favourite"))
                            .build();
                    projects[i] = project;
                }
                return projects;
            } else {
                throw new RuntimeException("Error");
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createTask() {

    }

    @Override
    public Task getActiveTask() {
        return null;
    }

    @Override
    public void deleteTask() {

    }
    public boolean existsByName(String name) {
        Project[] projects = this.getProject();
        for (int i = 0; i < projects.length; i++) {
            if (projects[i].getName() == name) {
                return true;
            }
        }
        return false;
    }
}
