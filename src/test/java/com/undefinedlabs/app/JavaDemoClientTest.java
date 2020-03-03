package com.undefinedlabs.app;

import com.undefinedlabs.app.model.Restaurant;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class JavaDemoClientTest {

    private static final String SAMPLE_NAME = "sampleName";
    private static final String SAMPLE_DESCRIPTION = "sampleDescription";
    private static final String ANOTHER_SAMPLE_NAME = "anotherSampleName";

    private JavaDemoClient sut;
    private List<Restaurant> toCleanUp;

    @Before
    public void setup() {
        sut = new JavaDemoClient();
        toCleanUp = new ArrayList<>();
    }

    @After
    public void cleanUp() {
        try {
            for(final Restaurant rest : toCleanUp) {
                sut.deleteRestaurant(rest.getId());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void should_create_restaurant() {
        //Given
        final Restaurant toCreate = new Restaurant(null, SAMPLE_NAME, SAMPLE_DESCRIPTION, null, null, null, null);

        //When
        final Restaurant result = sut.createRestaurant(toCreate);
        toCleanUp.add(result);

        //Then
        assertThat(result.getId()).isNotEmpty();
        assertThat(result.getName()).isEqualTo(SAMPLE_NAME);
        assertThat(result.getDescription()).isEqualTo(SAMPLE_DESCRIPTION);
        assertThat(result.getRating()).isNotNull();
        assertThat(result.getLatitude()).isNotEmpty();
        assertThat(result.getLongitude()).isNotEmpty();
    }

    @Test
    public void should_get_restaurant_by_id() {
        //Given
        final Restaurant toCreate = new Restaurant(null, SAMPLE_NAME, SAMPLE_DESCRIPTION, null, null, null, null);
        final Restaurant created = sut.createRestaurant(toCreate);
        toCleanUp.add(created);

        //When
        final Restaurant result = sut.getRestaurantById(created.getId());

        //Then
        assertThat(result).isEqualTo(created);
    }

    @Test
    public void should_get_restaurant_by_name() {
        //Given
        final Restaurant toCreate = new Restaurant(null, SAMPLE_NAME, SAMPLE_DESCRIPTION, null, null, null, null);
        final Restaurant created = sut.createRestaurant(toCreate);
        toCleanUp.add(created);

        //When
        final List<Restaurant> results = sut.getRestaurantsByName(SAMPLE_NAME);

        //Then
        assertThat(results.size()).isEqualTo(1);
        assertThat(results.get(0)).isEqualTo(created);
    }

    @Test
    public void should_update_restaurant() {
        //Given
        final Restaurant toCreate = new Restaurant(null, SAMPLE_NAME, SAMPLE_DESCRIPTION, null, null, null, null);
        final Restaurant created = sut.createRestaurant(toCreate);
        toCleanUp.add(created);

        //When
        final Restaurant updated = sut.updateRestaurant(created.getId(), new Restaurant(null, ANOTHER_SAMPLE_NAME, null, null, null, null, null));

        //Then
        assertThat(updated.getId()).isEqualTo(created.getId());
        assertThat(updated.getName()).isEqualTo(ANOTHER_SAMPLE_NAME);
    }

    @Test
    public void should_delete_restaurant() {
        //Given
        final Restaurant toCreate = new Restaurant(null, SAMPLE_NAME, SAMPLE_DESCRIPTION, null, null, null, null);
        final Restaurant created = sut.createRestaurant(toCreate);

        //When
        sut.deleteRestaurant(created.getId());

        //Then
    }

}