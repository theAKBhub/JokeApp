package com.udacity.gradle.builditbigger.backend;

import com.example.android.libjokeprovider.JokeModel;
import com.example.android.libjokeprovider.JokeRepository;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;


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

    @ApiMethod(name = "tellJoke")
    public JokeBean tellJoke() {

        JokeRepository jokeRepository = new JokeRepository();
        JokeModel jokeModel = jokeRepository.getJokesList().get(0);

        String joke = jokeModel.getJoke();

        JokeBean response = new JokeBean();
        response.setData(joke);

        return response;
    }
}
