package com.undefinedlabs.app;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.undefinedlabs.app.model.Restaurant;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class JavaDemoClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(JavaDemoClient.class);
    private static final String ENDPOINT = "https://go-demo-app.undefinedlabs.dev/restaurants";

    private final ObjectMapper objMapper = new ObjectMapper();
    private final OkHttpClient httpClient = new OkHttpClient.Builder().build();

    public Restaurant createRestaurant(final Restaurant restaurant) {
        LOGGER.info("API createRestaurant: " + restaurant);

        try {
            final RequestBody reqBody = RequestBody.create(MediaType.get("application/json"), objMapper.writeValueAsBytes(restaurant));
            final Request.Builder reqBuilder = new Request.Builder();
            reqBuilder.url(ENDPOINT)
                    .cacheControl(CacheControl.FORCE_NETWORK)
                    .post(reqBody);

            final Response response = httpClient.newCall(reqBuilder.build()).execute();
            if(response.isSuccessful()) {
                final Restaurant createdRestaurant = objMapper.readValue(response.body().string(), Restaurant.class);
                LOGGER.debug("--- created restaurant: " + createdRestaurant);
                return createdRestaurant;
            } else {
                LOGGER.error("API Error. Restaurant could not be created. HTTP " + response.code() + (response.body() != null ? ", message: " + response.body().string() : ""));
                throw new RuntimeException("createRestaurant error: HTTP " + response.code());
            }

        } catch (Exception e) {
            LOGGER.error("API Error. Restaurant could not be created: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public Restaurant getRestaurantById(final String id) {
        LOGGER.info("API getRestaurantById: " + id);

        try {
            final Request.Builder reqBuilder = new Request.Builder();
            reqBuilder.url(ENDPOINT + "/" + id);

            final Response response = httpClient.newCall(reqBuilder.build()).execute();
            if(response.isSuccessful()) {
                final Restaurant foundRestaurant = objMapper.readValue(response.body().string(), Restaurant.class);
                LOGGER.debug("--- found restaurant: " + foundRestaurant);
                return foundRestaurant;
            } else {
                LOGGER.error("API Error. Restaurant could not be found. HTTP " + response.code() + (response.body() != null ? ", message: " + response.body().string() : ""));
                throw new RuntimeException("getRestaurantById error: HTTP " + response.code());
            }
        } catch (Exception e){
            LOGGER.error("API Error. Restaurant could not be found: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public List<Restaurant> getRestaurantsByName(final String name) {
        LOGGER.info("API - getRestaurantsByName: " + name);

        try {
            final Request.Builder reqBuilder = new Request.Builder();
            reqBuilder.url(ENDPOINT + "/?name="+name);
            final Response response = httpClient.newCall(reqBuilder.build()).execute();
            if(response.isSuccessful()) {
                final List<Restaurant> foundRestaurants = objMapper.readValue(response.body().string(), new TypeReference<List<Restaurant>>(){});
                LOGGER.debug("--- found restaurants: " + foundRestaurants);
                return foundRestaurants;
            } else {
                LOGGER.error("API Error. Restaurant could not be found. HTTP " + response.code() + (response.body() != null ? ", message: " + response.body().string() : ""));
                throw new RuntimeException("getRestaurantsByName error: HTTP " + response.code());
            }

        } catch (Exception e){
            LOGGER.error("API Error. Restaurant could not be created: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public Restaurant updateRestaurant(final String id, final Restaurant restaurant) {
        LOGGER.info("API - updateRestaurant: id: " + id + ", restaurant: " + restaurant);

        try {
            final RequestBody reqBody = RequestBody.create(MediaType.get("application/json"), objMapper.writeValueAsBytes(restaurant));
            final Request.Builder reqBuilder = new Request.Builder();
            reqBuilder.url(ENDPOINT+ "/" + id).patch(reqBody);

            final Response response = httpClient.newCall(reqBuilder.build()).execute();
            if(response.isSuccessful()) {
                final Restaurant updatedRestaurant = objMapper.readValue(response.body().string(), Restaurant.class);
                LOGGER.debug("--- updated restaurant: " + updatedRestaurant);
                return updatedRestaurant;
            } else {
                LOGGER.error("API Error. Restaurant could not be updated. HTTP " + response.code() + (response.body() != null ? ", message: " + response.body().string() : ""));
                throw new RuntimeException("updateRestaurant error: HTTP " + response.code());
            }
        } catch (Exception e){
            LOGGER.error("API Error. Restaurant could not be updated: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void deleteRestaurant(final String id) {
        LOGGER.info("API - deleteRestaurant: id: " + id);

        try {

            final Request.Builder reqBuilder = new Request.Builder();
            reqBuilder.url(ENDPOINT + "/" + id).delete();

            final Response response = httpClient.newCall(reqBuilder.build()).execute();
            if(response.isSuccessful()) {
                LOGGER.debug("--- deleted restaurant: id: " + id);
            } else {
                LOGGER.error("API Error. Restaurant could not be deleted. HTTP " + response.code() + (response.body() != null ? ", message: " + response.body().string() : ""));
                throw new RuntimeException("deleteRestaurant error: HTTP " + response.code());
            }
        } catch (Exception e) {
            LOGGER.error("API Error. Restaurant could not be deleted: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }


}
