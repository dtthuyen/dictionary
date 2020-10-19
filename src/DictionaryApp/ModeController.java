package DictionaryApp;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ModeController {

    @FXML
    private JFXButton ButtonHome;

    @FXML
    private JFXButton ButtonGoogle;

    @FXML
    private JFXButton ButtonGoogleTranslate;

    @FXML
    private VBox DrawerMenu;

    @FXML
    private void onClickButtonToHome(ActionEvent event) throws IOException {
        Parent toHomePage = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene toHomePage_scene = new Scene(toHomePage);
        Stage toHomePage_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        toHomePage_stage.setScene(toHomePage_scene);
        toHomePage_stage.show();
    }

    @FXML
    private void onClickButtonToGoogle(ActionEvent event) throws IOException {
        Parent toGgPage = FXMLLoader.load(getClass().getResource("GgTranslateEV.fxml"));
        Scene toGgPage_scene = new Scene(toGgPage);
        Stage toGgPage_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        toGgPage_stage.setScene(toGgPage_scene);
        toGgPage_stage.show();
    }

    @FXML
    private void onClickButtonToGoogleTranslate(ActionEvent event) throws IOException {
        Parent toGGPage = FXMLLoader.load(getClass().getResource("GoogleTranslateVE.fxml"));
        Scene toGGPage_scene = new Scene(toGGPage);
        Stage toGGPage_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        toGGPage_stage.setScene(toGGPage_scene);
        toGGPage_stage.show();
    }
}
