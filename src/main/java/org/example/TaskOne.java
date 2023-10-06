package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

public class TaskOne {
    public void create() throws URISyntaxException, IOException, InterruptedException {
        Customer customer = new Customer(
                "Leanne Graham",
                "Bret",
                "Sincere@april.biz",
                new Address("Kulas Light",
                        "Apt. 556",
                        "Gwenborough",
                        "92998-3874",
                        new Geo("-37.3159",
                                "81.1496")),
                "1-770-736-8031 x56442",
                "hildegard.org",
                new Company("Romaguera-Crona",
                        "Multi-layered client-server neural-net",
                        "harness real-time e-markets")
        );
        Gson gson = new GsonBuilder().create();
        String result = gson.toJson(customer);

        String url = "https://jsonplaceholder.typicode.com/users";
        HttpRequest httpRequest = HttpRequest.newBuilder(new URI(url))
                .method("POST",HttpRequest.BodyPublishers.ofString(result))
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }
    public void update(int id) throws URISyntaxException, IOException, InterruptedException {
        Customer customer = new Customer(
                "Victor",
                "Bret",
                "Sincere@april.biz",
                new Address("Kulas Light",
                        "Apt. 556",
                        "Gwenborough",
                        "92998-3874",
                        new Geo("-37.3159",
                                "81.1496")),
                "1-770-736-8031 x56442",
                "hildegard.org",
                new Company("Romaguera-Crona",
                        "Multi-layered client-server neural-net",
                        "harness real-time e-markets")
        );
        Gson gson = new GsonBuilder().create();
        String result = gson.toJson(customer);

        String url = "https://jsonplaceholder.typicode.com/users/" + String.valueOf(id);
        HttpRequest httpRequest = HttpRequest.newBuilder(new URI(url))
                .method("PUT",HttpRequest.BodyPublishers.ofString(result))
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }
    public void delete(int id) throws URISyntaxException, IOException, InterruptedException {
        String url = "https://jsonplaceholder.typicode.com/users/" + String.valueOf(id);
        HttpRequest httpRequest = HttpRequest.newBuilder(new URI(url))
                .DELETE()
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }
    public void getAll() throws URISyntaxException, IOException, InterruptedException {
        String url = "https://jsonplaceholder.typicode.com/users";
        HttpRequest httpRequest = HttpRequest.newBuilder(new URI(url))
                .GET()
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        Customer[] person = new Gson().fromJson(response.body(), Customer[].class);
        System.out.println(Arrays.toString(person));
    }
    public void getId(int id) throws URISyntaxException, IOException, InterruptedException {
        String url = "https://jsonplaceholder.typicode.com/users/" + id;
        HttpRequest httpRequest = HttpRequest.newBuilder(new URI(url))
                .GET()
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        Customer person = new Gson().fromJson(response.body(), Customer.class);
        System.out.println(String.valueOf(person));
    }
    public void getName(String name) throws URISyntaxException, IOException, InterruptedException {
        String url = "https://jsonplaceholder.typicode.com/users?username=" + name;
        HttpRequest httpRequest = HttpRequest.newBuilder(new URI(url))
                .GET()
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        Customer person[] = new Gson().fromJson(response.body(), Customer[].class);
        System.out.println(Arrays.toString(person));
    }
    private class Customer{
        @Override
        public String toString() {
            return "Customer{" +
                    "name='" + name + '\'' +
                    ", username='" + username + '\'' +
                    ", email='" + email + '\'' +
                    ", address=" + address +
                    ", phone='" + phone + '\'' +
                    ", website='" + website + '\'' +
                    ", company=" + company +
                    '}';
        }

        private String name;
        private String username;
        private String email;
        private Address address;
        private String phone;
        private String website;
        private Company company;
        public Customer(String name, String username, String email, Address address, String phone, String website, Company company) {
            this.name = name;
            this.username = username;
            this.email = email;
            this.address = address;
            this.phone = phone;
            this.website = website;
            this.company = company;
        }
    }
    private class Address{
        private String street;
        private String suite;
        private String city;
        private String zipcode;
        private Geo geo;

        @Override
        public String toString() {
            return "Address{" +
                    "street='" + street + '\'' +
                    ", suite='" + suite + '\'' +
                    ", city='" + city + '\'' +
                    ", zipcode='" + zipcode + '\'' +
                    ", geo=" + geo +
                    '}';
        }

        public Address(String street, String suite, String city, String zipcode, Geo geo) {
            this.street = street;
            this.suite = suite;
            this.city = city;
            this.zipcode = zipcode;
            this.geo = geo;
        }
    }
    private class Geo{
        @Override
        public String toString() {
            return "Geo{" +
                    "lat='" + lat + '\'' +
                    ", lng='" + lng + '\'' +
                    '}';
        }
        private String lat;
        private String lng;

        public Geo(String lat, String lng) {
            this.lat = lat;
            this.lng = lng;
        }
    }
    private class Company{
        @Override
        public String toString() {
            return "Company{" +
                    "name='" + name + '\'' +
                    ", catchPhrase='" + catchPhrase + '\'' +
                    ", bs='" + bs + '\'' +
                    '}';
        }
        private String name;
        private String catchPhrase;
        private String bs;
        public Company(String name, String catchPhrase, String bs) {
            this.name = name;
            this.catchPhrase = catchPhrase;
            this.bs = bs;
        }
    }
}

