package org.example;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TaskThree {
public void run(int id) throws IOException, InterruptedException, URISyntaxException {
    String url = "https://jsonplaceholder.typicode.com/users/" + id + "/todos";
    HttpRequest httpRequest = HttpRequest.newBuilder(new URI(url))
            .GET()
            .build();
    HttpClient httpClient = HttpClient.newHttpClient();
    HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    Todos[] tasks = new Gson().fromJson(response.body(), Todos[].class);
    for (Todos todos:tasks) {
        if(todos.completed == false){
            System.out.println(todos.title);
        }
    }
}
    private class Todos{
        public Todos(long userId, long id, String title, boolean completed) {
            this.userId = userId;
            this.id = id;
            this.title = title;
            this.completed = completed;
        }
        private long userId;
        private long id;
        private String title;
        private boolean completed;
    }
}
