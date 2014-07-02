package io.github.mgt6.geoSearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import io.github.mgt6.geoSearch.dl.PubRepository;
import io.github.mgt6.geoSearch.domain.Pub;

@ComponentScan
@EnableAutoConfiguration
public class Application implements CommandLineRunner {

    @Autowired
    private PubRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        repository.deleteAll();

        repository.save(new Pub("George Canning", 3, 51.4678685, -0.0860632));
        repository.save(new Pub("The Cherry Tree", -1, 51.461512, -0.078988));
        repository.save(new Pub("The Fox on the Hill", 3, 51.4651705, -0.0895804));
        repository.save(new Pub("The Flying Pig", 5, 51.461744, -0.070394));
        repository.save(new Pub("The East Dulwich Taven",4, 51.460463, -0.07513));

        System.out.println("Pubs found with findAll():");
        System.out.println("-------------------------------");
        for (Pub pub : repository.findAll()) {
            System.out.println(pub);
        }
        System.out.println();

        System.out.println("Pubs found with findByRatingGreaterThan(3):");
        System.out.println("-------------------------------");
        for (Pub pub : repository.findByRatingGreaterThan(3)) {
            System.out.println(pub);
        }
        System.out.println();

        System.out.println("Pubs found with findByRatingLessThan(0):");
        System.out.println("-------------------------------");
        for (Pub pub : repository.findByRatingLessThan(0)) {
            System.out.println(pub);
        }
        System.out.println();

        System.out.println("Pub found with findByFirstName('The Flying Pig'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByName("The Flying Pig"));

        System.out.println("Pubs found within 1K of '51.4634836,-0.0841914':");
        System.out.println("--------------------------------");
        for (GeoResult<Pub> pub : repository.findByLocationNear(new Point(51.4634836, -0.0841914), new Distance(1, Metrics.KILOMETERS))) {
            System.out.println(pub.getContent());
        }
        System.out.println();

    }

}
