package api;

import entity.Project;
import entity.Task;
import kotlin.Pair;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.add_project.AddProjectDataAccessInterface;
import use_case.add_task.AddTaskDataAccessInterface;
import use_case.close_task.CloseTaskDataAccessInterface;
import use_case.delete_project.DeleteProjectDataAccessInterface;
import use_case.get_all_projects.GetProjectDataAccessInterface;
import use_case.get_task.GetTaskDataAccessInterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class TodoistDB implements AddProjectDataAccessInterface, GetTaskDataAccessInterface, GetProjectDataAccessInterface, CloseTaskDataAccessInterface, AddTaskDataAccessInterface, DeleteProjectDataAccessInterface {
    private static final String API_TOKEN = System.getenv("API_TOKEN");

    public static String getApiToken() {
        return API_TOKEN;
    }
    private final Map<String,String> all_projects = new HashMap<>() {};
    private final Map<Integer, String> phrases = new HashMap<>();

    @Override
    public void createProject(String name) {
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
                return;
            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }


    public void deleteProject(String projectName) {
        this.getProject();
        String projectId = all_projects.get(projectName);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .delete()
                .url("https://api.todoist.com/rest/v2/projects/"+ projectId)
                .addHeader("Authorization", API_TOKEN)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.code() == 204) {
                all_projects.remove(projectName);
            }
            else {
                throw new RuntimeException("Error");
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }


    public Project[] getProject() {
        OkHttpClient client = new OkHttpClient();
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
                    String projectId = element.getString("id");
                    String projectName = element.getString("name");
                    int taskCount = getTasksCountForProject(projectId);
                    Project project = Project.builder()
                            .ProjectId(projectId)
                            .ProjectName(projectName)
                            .TaskCount(taskCount)
                            .build();
                    projects[i] = project;
                    all_projects.put(projectName, projectId);
                }
                return projects;
            } else {
                throw new RuntimeException("Error");
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }


    private int getTasksCountForProject(String projectId) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.todoist.com/rest/v2/tasks?project_id=" + projectId)
                .addHeader("Authorization", API_TOKEN)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            String responseBodyString = response.body().string();
            JSONArray tasksArray = new JSONArray(responseBodyString);
            return tasksArray.length(); // 返回任务数量
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void closeTask(String taskId) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.todoist.com/rest/v2/tasks/" + taskId + "/close"))
                .header("Authorization", API_TOKEN)
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());

            if (response.statusCode() != 204) { // Check for the expected 204 status code
                throw new IOException("Unexpected response status: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Precondition: the project name provided exists
    public Pair<String, ArrayList<Task>> getTasks(String name) {
        this.getProject();
        String id = all_projects.get(name);
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://api.todoist.com/rest/v2/tasks")
                .addHeader("Authorization", API_TOKEN)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONArray responseBody = new JSONArray(response.body().string());
            if (response.code() == 200) {
                ArrayList<Task> tasks = new ArrayList<>();
                for (int i = 0; i < responseBody.length(); i++) {
                    JSONObject element = responseBody.getJSONObject(i);
                    if (element.getString("project_id").equals(id)) {

                        String deadline = "";
                        if (!element.isNull("due")) { // Check if "due" exists.
                            JSONObject dueObject = element.getJSONObject("due");
                            deadline = dueObject.getString("string");
                        }

                        Task task = Task.builder()
                                .TaskId(element.getString("id"))
                                .TaskName(element.getString("content"))
                                .ProjectId(element.getString("project_id"))
                                .Deadline(deadline)
                                .build();
                        tasks.add(task);
                    }
                }
                Pair<String, ArrayList<Task>> result = new Pair<>(name, tasks);
                return result;
            } else {
                throw new RuntimeException("Error");
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public String addTask(String taskName, String projectName, String deadline) {
        HttpClient client = HttpClient.newHttpClient();
        this.getProject();
        String id = all_projects.get(projectName);
        String requestBody = "{\"content\": \"" + taskName +
                "\", \"project_id\": \"" + id +
                "\", \"due_date\": \"" + deadline + "\"}";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.todoist.com/rest/v2/tasks"))
                .header("Authorization", API_TOKEN)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                // Parse the response body to get the task ID
                JSONObject jsonResponse = new JSONObject(response.body());
                String taskId = jsonResponse.getString("id");
                return taskId; // Return the task ID
            } else {
                throw new IOException("Unexpected response status: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null; // Return null or handle the error as appropriate
        }
    }


    public boolean existsByName(String name) {
        this.getProject();
        if (all_projects.containsKey(name)) {
            return true;
        }
        else {return false;}
    }

    public String getMessage() {
        try (BufferedReader br = new BufferedReader(new FileReader("emotional_phrases"))) {
            String line;
            Integer i = 0;
            while ((line = br.readLine()) != null) {
                phrases.put(i, line);
                i += 1;
            }
        } catch (IOException e) {
        }
        List<Integer> keysAsArray = new ArrayList<Integer>(phrases.keySet());
        Random r = new Random();
        String phrase = phrases.get(keysAsArray.get(r.nextInt(keysAsArray.size())));
        return phrase;
    }
}