package com.undefinedlabs.scope.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Image {

    private final String mimeType;
    private final String data;

    @JsonCreator
    public Image(@JsonProperty("mimeType") final String mimeType, @JsonProperty("data") final String data) {
        this.mimeType = mimeType;
        this.data = data;
    }

    @JsonGetter
    public String getMimeType() {
        return mimeType;
    }

    @JsonGetter
    public String getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(mimeType, image.mimeType) &&
                Objects.equals(data, image.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mimeType, data);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Image{");
        sb.append("mimeType='").append(mimeType).append('\'');
        sb.append(", data='").append(data).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
