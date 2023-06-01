package com.flower.shop.integration.utils;

import com.flower.shop.data.models.integration.User;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;

public class JsonUtils {

    public String mapUserToStringWithRole(User user) {
        Gson gson = new Gson();
        JsonElement jsonElement = gson.toJsonTree(user);
        jsonElement.getAsJsonObject().addProperty("role", "client");
        return gson.toJson(jsonElement);
    }

    public String getJwtToken(ResponseEntity<String> response) {
        JSONObject body = getJsonBody(response.getBody());
        String jwtToken = getStringFromJson("jwttoken", body);
        return jwtToken;
    }

    private JSONObject getJsonBody(String responseBody) {
        try {
            return new JSONObject(responseBody);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private String getStringFromJson(String fieldName, JSONObject jsonObject) {
        try {
            return jsonObject.getString(fieldName);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

}
