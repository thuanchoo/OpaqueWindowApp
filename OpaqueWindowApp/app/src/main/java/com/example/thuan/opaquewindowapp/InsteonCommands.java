package com.example.thuan.opaquewindowapp;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

public class InsteonCommands {

    // This function will turn the window pane transparent given the window pane ID.
    // It will implement a fast_on command, which will turn the pane 100 percent transparent
    public void turnPaneOn (int id) {

        Client client = ClientBuilder.newClient();

        Entity payload = Entity.json("{\n    \"command\": \"fast_on\",\n   \"device_id\": " + id + "\n}");
        Response response = client.target("https://private-anon-5206453b9-insteon.apiary-mock.com")
                .path("/api/v2/commands")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authentication", "APIKey 90cfea88-063c-4813-ae20-ea1e241c2b0e1422607574.8731273")
                .header("Authorization", "Bearer 90cfea88-063c-4813-ae20-ea1e241c2b0e1422607574.8731273")
                .post(payload);

        System.out.println("status: " + response.getStatus());
        System.out.println("headers: " + response.getHeaders());
        System.out.println("body:" + response.readEntity(String.class));

    }

    // This function will turn the window pane opaque given the window pane ID.
    // It will implement a fast_off command, which will turn the pane 100 percent opaque
    public void turnPaneOff (int id) {

        Client client = ClientBuilder.newClient();

        Entity payload = Entity.json("{\n    \"command\": \"fast_off\",\n    \"device_id\": " + id + "\n}  ");
        Response response = client.target("https://private-anon-5206453b9-insteon.apiary-mock.com")
                .path("/api/v2/commands")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authentication", "APIKey 90cfea88-063c-4813-ae20-ea1e241c2b0e1422607574.8731273")
                .header("Authorization", "Bearer 90cfea88-063c-4813-ae20-ea1e241c2b0e1422607574.8731273")
                .post(payload);

        System.out.println("status: " + response.getStatus());
        System.out.println("headers: " + response.getHeaders());
        System.out.println("body:" + response.readEntity(String.class));
    }

    // This function will turn all window panes on, specified by the scene_id
    // The scene_id is the identification number that the Insteon Hub will recognize
    // It will implement a fast_off command, which will turn the pane 100 percent opaque
    public void turnWindowOn (int scene) {

        Client client = ClientBuilder.newClient();

        Entity payload = Entity.json("{\n    \"command\": \"fast_on\",\n   \"scene_id\": " + scene + "\n}");
        Response response = client.target("https://private-anon-5206453b9-insteon.apiary-mock.com")
                .path("/api/v2/commands")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authentication", "APIKey 90cfea88-063c-4813-ae20-ea1e241c2b0e1422607574.8731273")
                .header("Authorization", "Bearer 90cfea88-063c-4813-ae20-ea1e241c2b0e1422607574.8731273")
                .post(payload);

        System.out.println("status: " + response.getStatus());
        System.out.println("headers: " + response.getHeaders());
        System.out.println("body:" + response.readEntity(String.class));

    }

    // This function will turn all window panes off, specified by the scene_id
    // It will implement a fast_off command, which will turn the pane 100 percent transparent
    public void turnWindowOff (int scene) {

        Client client = ClientBuilder.newClient();

        Entity payload = Entity.json("{\n    \"command\": \"fast_off\",\n    \"scene_id\": " + scene + "\n}  ");
        Response response = client.target("https://private-anon-5206453b9-insteon.apiary-mock.com")
                .path("/api/v2/commands")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authentication", "APIKey 90cfea88-063c-4813-ae20-ea1e241c2b0e1422607574.8731273")
                .header("Authorization", "Bearer 90cfea88-063c-4813-ae20-ea1e241c2b0e1422607574.8731273")
                .post(payload);

        System.out.println("status: " + response.getStatus());
        System.out.println("headers: " + response.getHeaders());
        System.out.println("body:" + response.readEntity(String.class));
    }

}
