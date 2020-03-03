package com.undefinedlabs.app.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class Restaurant {

    private final String id;
    private final String name;
    private final String description;
    private final Double rating;
    private final String latitude;
    private final String longitude;
    private final List<Image> images;

    @JsonCreator
    public Restaurant(@JsonProperty("id") final String id,
                      @JsonProperty("name") final String name,
                      @JsonProperty("description") final String description,
                      @JsonProperty("rating") final Double rating,
                      @JsonProperty("latitude") final String latitude,
                      @JsonProperty("longitude") final String longitude,
                      @JsonProperty("images") final List<Image> images) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.latitude = latitude;
        this.longitude = longitude;
        this.images = images;
    }

    @JsonGetter
    public String getId() {
        return id;
    }

    @JsonGetter
    public String getName() {
        return name;
    }

    @JsonGetter
    public String getDescription() {
        return description;
    }

    @JsonGetter
    public Double getRating() {
        return rating;
    }

    @JsonGetter
    public String getLatitude() {
        return latitude;
    }

    @JsonGetter
    public String getLongitude() {
        return longitude;
    }

    @JsonGetter
    public List<Image> getImages() {
        return images;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(rating, that.rating) &&
                Objects.equals(latitude, that.latitude) &&
                Objects.equals(longitude, that.longitude) &&
                Objects.equals(images, that.images);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, rating, latitude, longitude, images);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Restaurant{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", rating=").append(rating);
        sb.append(", latitude='").append(latitude).append('\'');
        sb.append(", longitude='").append(longitude).append('\'');
        sb.append(", images=").append(images);
        sb.append('}');
        return sb.toString();
    }
}
