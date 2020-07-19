package org.example.api;



import org.example.model.Games;

import java.io.IOException;

public interface GamesApi {
    Games getGames() throws IOException, GameApiException;
}
