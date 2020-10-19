package DictionaryApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 *
 * @author Dinh Thi Thanh Huyen 19020560
 */
public class DictionaryApplication extends Application {
    public static DictionaryManagement dictionaryManagement = new DictionaryManagement();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Dictionary");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException {

        dictionaryManagement.insertFromFile();

        launch(args);
    }
}
