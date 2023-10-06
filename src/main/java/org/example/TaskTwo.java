package org.example;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

public class TaskTwo {
    private long postId;
    private long userId;
    public void writeComment(int id) throws IOException, InterruptedException, URISyntaxException {
        userId = id;
        String url = "https://jsonplaceholder.typicode.com/users/"+ id +"/posts";
        HttpRequest httpRequest = HttpRequest.newBuilder(new URI(url))
                .GET()
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        Post[] posts = new Gson().fromJson(response.body(), Post[].class);
        postId = posts[posts.length-1].id;
        post();
    }
    private void post() throws URISyntaxException, IOException, InterruptedException {
        String url = "https://jsonplaceholder.typicode.com/posts/" + postId + "/comments";
        HttpRequest httpRequest = HttpRequest.newBuilder(new URI(url))
                .GET()
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        Comment[] comment = new Gson().fromJson(response.body(), Comment[].class);
        StringBuilder result = new StringBuilder();
        for (Comment comments:comment) {
            result.append(comments.body);
        }

        File file1 = new File("Task2/user-"+ userId +"-post-" + postId +"-comments.json");
        try {
            FileOutputStream fos = new FileOutputStream(file1);
            fos.write(result.toString().getBytes());
        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }
    private class Post{
        private String userId;
        private long id;
        private String title;
        private String body;
        public Post(String userId, long id, String title, String body) {
            this.userId = userId;
            this.id = id;
            this.title = title;
            this.body = body;
        }
        @Override
        public String toString() {
            return "post{" +
                    "userId='" + userId + '\'' +
                    ", id=" + id +
                    ", title='" + title + '\'' +
                    ", body='" + body + '\'' +
                    '}';
        }
    }
    private class Comment{
        private long postId;
        private long id;
        private String name;
        private String email;
        private String body;

        public Comment(long postId, long id, String name, String email, String body) {
            this.postId = postId;
            this.id = id;
            this.name = name;
            this.email = email;
            this.body = body;
        }
    }
}
