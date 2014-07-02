package io.github.mgt6.geoSearch.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;

@Document(collection = "pubs")
public class Pub {

    @Id
    private String id;

    private String name;

    @GeoSpatialIndexed
    private double[] location;

    private int rating;

    private Pub() {}

    public Pub(String name, int rating, double latitude, double longitude) {
        this.name = name;
        this.rating = rating;
        this.location = new double[2];
        location[0] = latitude;
        location[1] = longitude;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public double[] getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Pub{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location=" + Arrays.toString(location) +
                ", rating=" + rating +
                '}';
    }
}
