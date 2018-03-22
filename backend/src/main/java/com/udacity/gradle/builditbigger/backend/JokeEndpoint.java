package com.udacity.gradle.builditbigger.backend;

import com.example.android.libjokeprovider.JokeRepository;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;


/** An endpoint class we are exposing */
@Api(
        name = "jokeApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)

public class JokeEndpoint {

    @ApiMethod(name = "getJoke")
    public JokeBean getJoke(@Named("jokeType") String jokeType) {

        JokeRepository jokeRepository = new JokeRepository();

        //String joke = jokeRepository.getDailyJoke();
        String joke = jokeRepository.getJokeFromRepo(jokeType);
        JokeBean response = new JokeBean();
        response.setData(joke);

        return response;
    }

}
