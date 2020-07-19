package org.example;

import org.example.api.GameFetchListener;
import org.example.dao.LottoDAO;
import org.example.intervalChecker.GamesIntervalCheckExecutor;
import org.example.intervalChecker.GamesIntervalCheckTask;
import org.example.intervalChecker.TimePeriod;
import org.example.model.Games;
import org.example.model.Lotto;
import org.example.sessionProvider.SessionProvider;


public class MyThread extends Thread {
    Thread myThread;

    @Override
    public void run() {
        intervalChecking();
        try {
            myThread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.stopChecking();
        daoLotto.close();
        sessionProvider.closeFactory();
    }
}
