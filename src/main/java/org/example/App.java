package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.api.CloudApi;
import org.example.api.GameFetchListener;
import org.example.api.GamesApi;
import org.example.dao.*;
import org.example.intervalChecker.GamesIntervalCheckExecutor;
import org.example.intervalChecker.GamesIntervalCheckTask;
import org.example.intervalChecker.TimePeriod;
import org.example.model.*;
import org.example.sessionProvider.SessionProvider;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.IOException;
import java.util.List;

public class App extends Application {
    private static SessionFactory factory;

    public static void main(String[] args) {
        intervalChecking();
        launch(args);
    }

    public static void intervalChecking(){
        SessionProvider sessionProvider = SessionProvider.INSTANCE;
        LottoDAO daoLotto = new LottoDAO(sessionProvider.getSession());
        GamesIntervalCheckTask task = new GamesIntervalCheckTask(new GameFetchListener() {
            @Override
            public void onSuccess(Games games) {
                Lotto lottoFromApi = games.getLotto();
                Lotto lottoFromDb = daoLotto.get(games.getLotto().getNumLosowania());

                if (lottoFromDb == null) {
                    daoLotto.save(lottoFromApi);
                } else {
                    daoLotto.update(lottoFromDb);
                }
            }
            @Override
            public void onFail(String errorMassage) {
                System.out.println(errorMassage);
            }
        });




        GamesIntervalCheckExecutor executor = new GamesIntervalCheckExecutor();
        executor.checkInInterval(task, TimePeriod.NORMAL);

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.stopChecking();
        daoLotto.close();
        sessionProvider.closeFactory();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/main_scene_layout.fxml"));
        Scene mainScene = new Scene(root);

        stage.setScene(mainScene);
        stage.setTitle("MiToLotto");
        stage.show();
    }
}

