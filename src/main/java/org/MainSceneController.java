package org;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.example.api.CloudApi;
import org.example.dao.LottoDAO;
import org.example.model.Games;
import org.example.model.Lotto;
import org.example.sessionProvider.SessionProvider;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static org.example.App.intervalChecking;

public class MainSceneController implements Initializable {

    @FXML
    Button loadButton;

    @FXML
    Text title;

    @FXML
    TextArea showResult;

    @FXML
    ChoiceBox <String> choiceLotto;

    private LottoDAO dao;
    ObservableList list = FXCollections.observableArrayList();

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        SessionProvider sessionProvider = SessionProvider.INSTANCE;
//        dao = new LottoDAO(sessionProvider.getSession());

        choiceBoxData();

        loadButton.setOnAction(event ->
                {
                    try {
                        showData();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

        );

    }

    private void showData () throws IOException {
        CloudApi api = new CloudApi();
        String temp = choiceLotto.getValue();
        if (temp == null){
            showResult.setText("Nie wybrano żadnej loterii!");
        }else{
            switch (temp){
                case "Lotto":
                    showResult.setText(api.getGames().getLotto().toString());
                    break;
                case "LottoPlus":
                    showResult.setText(api.getGames().getLottoPlus().toString());
                    break;
                case "Mini":
                    showResult.setText(api.getGames().getMini().toString());
                    break;
                case "EkstraPensja":
                    showResult.setText(api.getGames().getEkstraPensja().toString());
                    break;
                case "Joker":
                    showResult.setText(api.getGames().getJoker().toString());
                    break;
                default:
                    showResult.setText("Nie wybrano żadnej loterii!");
            }
        }
    }

    private void choiceBoxData() {
        list.removeAll(list);
        String lotto = "Lotto";
        String lottoPlus = "LottoPlus";
        String mini = "Mini";
        String ekstraPensja = "EkstraPensja";
        String joker = "Joker";
        list.addAll(lotto, lottoPlus, mini, ekstraPensja, joker);
        choiceLotto.getItems().addAll(list);
    }

//    public void doMagic(MouseEvent mouseEvent) {
//        Lotto lotto = dao.get(Integer.parseInt(choiceLotto.getAccessibleText()));
//        showResult.setText(lotto.toString());
//
//    }

}