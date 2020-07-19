package org.example.api;

import org.example.model.Games;

public interface GameFetchListener {
    void onSuccess(Games games);

    void onFail(String errorMassage);
}
