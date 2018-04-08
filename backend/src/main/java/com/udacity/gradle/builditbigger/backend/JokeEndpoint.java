package com.udacity.gradle.builditbigger.backend;

import com.example.android.libjokeprovider.JokeRepository;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import java.util.ArrayList;


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

        ArrayList<String> jokeList = jokeRepository.getJokeList(jokeType);
        JokeBean response = new JokeBean();
        response.setList(jokeList);

        return response;
    }
}
