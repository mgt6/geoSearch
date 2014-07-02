package io.github.mgt6.geoSearch.dl;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import io.github.mgt6.geoSearch.domain.Pub;

import java.util.List;

public interface PubRepository extends MongoRepository<Pub, String> {

    Pub findByName(String name);

    List<Pub> findByRatingGreaterThan(int rating);

    List<Pub> findByRatingLessThan(int rating);

    GeoResults<Pub> findByLocationNear(Point location, Distance distance);

}
