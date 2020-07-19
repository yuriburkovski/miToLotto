package org.example.intervalChecker;


import org.example.api.GameApiException;
import org.example.api.GameFetchListener;
import org.example.api.GamesApi;
import org.example.model.Games;
import org.example.api.CloudApi;

import java.io.IOException;
import java.util.TimerTask;

public class GamesIntervalCheckTask extends TimerTask {
    private final GamesApi api = new CloudApi();

    private final GameFetchListener listener;

    public GamesIntervalCheckTask(GameFetchListener listener) {
        this.listener = listener;
    }

    @Override
    public void run() {
        try {
            Games games = api.getGames();
            listener.onSuccess(games);
        } catch (IOException | GameApiException e) {
            listener.onFail(e.getMessage());
        }
    }
}