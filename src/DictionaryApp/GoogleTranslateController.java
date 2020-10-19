package DictionaryApp;

import com.darkprograms.speech.translator.GoogleTranslate;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GoogleTranslateController implements Initializable {

    @FXML
    private JFXDrawer drawer;

    @FXML
    private TextArea outputWord;

    @FXML
    private TextField inputWord;

    @FXML
    private AnchorPane topBar;

    @FXML
    private JFXButton translate;

    @FXML
    private JFXHamburger hamburger;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            VBox box = FXMLLoader.load(getClass().getResource("Mode.fxml"));
            drawer.setSidePane(box);

            /*Change the Menu button: Menu <-> Arrow*/
            HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
            transition.setRate(-1);
            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                transition.setRate(transition.getRate() * -1);
                transition.play();

                if (drawer.isOpened()) {
                    drawer.close();
                } else {
                    drawer.open();
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String convert(String s) {
        int k = 0;
        String t = "";
        for(int i = 0; i < s.length(); i ++) {
            t += s.charAt(i);
            k++;
            if (k > 45 && s.charAt(i) == ' ') {
                t += "\n";
                k = 0;
            }
        }
        return t;
    }

    public void onClickedTranslateEV() {
        String viText = inputWord.getText();
        try {
            String enText = GoogleTranslate.translate("en-US", "vi", viText);
            outputWord.setText(convert(enText));
        } catch (IOException e) {
            CheckNet.showAlertWithHeaderText("Please connect internet!!!");
        }
    }

    public void onClickedTranslateVE() {
        String enText = inputWord.getText();
        try {
            String viText = GoogleTranslate.translate("vi", "en-US", enText);
            outputWord.setText(convert(viText));
        } catch (IOException e) {
            CheckNet.showAlertWithHeaderText("Please connect internet!!!");
        }
    }

    public void onClickSpeakerButtonInput() {
        GoogleAPI ggAPI = new GoogleAPI();
        if(CheckNet.netIsAvailable()) {
            ggAPI.speak(inputWord.getText());
        } else {
            CheckNet.showAlertWithHeaderText("Please connect internet!!!");
        }
    }

    public void onClickSpeakerButtonOutput() {
        GoogleAPI ggAPI = new GoogleAPI();
        if(CheckNet.netIsAvailable()) {
            ggAPI.speak(outputWord.getText());
        } else {
            CheckNet.showAlertWithHeaderText("Please connect internet!!!");
        }
    }

}
