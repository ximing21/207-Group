package api;

import entity.Project;
import entity.Task;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.add_project.AddProjectDataAccessInterface;
import use_case.get_task.GetTaskDataAccessInterface;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TodoistDB implements AddProjectDataAccessInterface, GetTaskDataAccessInterface {
    private static final String API_TOKEN = System.getenv("API_TOKEN");

    public static String getApiToken() {
        return API_TOKEN;
    }
    private final Map<String,String> all_projects = new HashMap<>() {};


    @Override
    public void createProject(String name) throws JSONException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", name);
        RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
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
                String id = responseBody.getString("id");
                String project_name = responseBody.getString("name");
                all_projects.put(project_name, id);
            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }


    public Project updateProject() {
        return null;
    }


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
            JSONArray responseBody = new JSONArray(response.body().string());
            if (response.code() == 200) {
                Project[] projects = new Project[responseBody.length()];
                for (int i = 0; i < responseBody.length(); i++) {
                    JSONObject element = responseBody.getJSONObject(i);
                    Project project = Project.builder()
                            .name(element.getString("name"))
                            .is_favorite(element.getBoolean("is_favorite"))
                            .id(element.getString("id"))
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


    public void createTask() {

    }


    //Precondition: the project name provided exists
    public Task[] getTasks(String name) {
        String id = all_projects.get(name);
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://api.todoist.com/rest/v2/projects")
                .addHeader("Authorization", API_TOKEN)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONArray responseBody = new JSONArray(response.body().string());
            if (response.code() == 200) {
                Task[] tasks = new Task[responseBody.length()];
                for (int i = 0; i < responseBody.length(); i++) {
                    JSONObject element = responseBody.getJSONObject(i);
                    if (element.getString("project_id") == id) {
                        Task task = Task.builder()
                                .taskName(element.getString("content"))
                                .isCompleted(element.getBoolean("is_completed"))
                                .projectId(element.getString("project_id"))
                                .build();
                        tasks[i] = task;
                    }
                } return tasks;
            } else {
                throw new RuntimeException("Error");
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }


    public void deleteTask() {

    }

    public boolean existsByName(String name) {
        if (all_projects.containsKey(name)) {
            return true;
        }
        else {return false;}
    }
}