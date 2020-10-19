package DictionaryApp;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WordController implements Initializable {
    @FXML
    private JFXDrawer drawer;

    @FXML
    private Button ButtonSaveModify;

    @FXML
    private Button ButtonEditModify;

    @FXML
    private Button save;

    @FXML
    public TextArea showWordTarget;

    @FXML
    private JFXButton speakerIn;

    @FXML
    private Button ButtonDelete;

    @FXML
    private Button back;

    @FXML
    public TextArea showWordExplain;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private Label deleteSuccessfully;

    @FXML
    private Label addSuccessfully;

    @FXML
    private Label editSuccessfully;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

    @FXML
    private Label label4;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        back.setVisible(false);
        save.setVisible(false);
        label3.setVisible(false);
        label4.setVisible(false);
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
                }
                else {
                    drawer.open();
                }
            });
        }
        catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    Dictionary dictionary = DictionaryApplication.dictionaryManagement.getDictionary();

    public void onButtonAddFuncClicked() {
        editSuccessfully.setVisible(false);
        deleteSuccessfully.setVisible(false);
        String enWord = showWordTarget.getText();
        String viWord = showWordExplain.getText();
        if (enWord.length() == 0) {
            showWordExplain.setText("\n\nAdd fail!\nEnglish word is empty");
            return;
        }
        if (viWord.length() == 0) {
            showWordExplain.setText("Add fail!\nVietnamese word is empty");
            return;
        }

        int temp = 0;
        for (int i = 0; i < dictionary.getWordList().size(); i++) {
            if (dictionary.getWordList().get(i).getWordTarget().equals(enWord))
                temp++;
        }

        if (temp == 0) {
            dictionary.addWord(new Word(enWord, viWord));
            showWordTarget.setVisible(true);
            showWordExplain.setVisible(true);
            addSuccessfully.setVisible(true);
        } else if (temp != 0) {
            showWordExplain.setText(viWord + "\nAdd fail!\nThis English word Existed");
        }
    }

    int k;
    public void onButtonEditFuncClicked() {
        addSuccessfully.setVisible(false);
        deleteSuccessfully.setVisible(false);
        String enWord = showWordTarget.getText();
        if (enWord.length() == 0) {
            showWordExplain.setText("\n\nEdit fail!\nEnglish word is empty");
            return;
        }

        int temp = 0;
        for (int i = 0; i < dictionary.getWordList().size(); i++) {
            if (dictionary.getWordList().get(i).getWordTarget().equals(enWord))
                temp++;
        }
        k = temp;
        if (temp == 0) {
            showWordExplain.setText("Edit fail!\nThis English word don't exist");
            editSuccessfully.setVisible(false);
        } else if (temp != 0) {
            label1.setVisible(false);
            label2.setVisible(false);
            label3.setVisible(true);
            label4.setVisible(true);
            ButtonDelete.setVisible(false);
            ButtonSaveModify.setVisible(false);
            ButtonEditModify.setVisible(false);
            save.setVisible(true);
            back.setVisible(true);
            showWordTarget.setText("");
            showWordExplain.setText("");
        }
    }

    public void saveEdited() {
        if (showWordTarget.getText().length() == 0) {
            showWordExplain.setText("Edit Fail!\nEnglish edited word is empty");
            return;
        }
        if (showWordExplain.getText().length() == 0) {
            showWordExplain.setText("Edit Fail!\nVietnamese word is empty");
            return;
        }
        dictionary.removeWord(k);
        String enWord = showWordTarget.getText();
        String viWord = showWordExplain.getText();
        dictionary.addWord(new Word(enWord, viWord));
        editSuccessfully.setVisible(true);
    }

    public void backAction() {
        showWordTarget.setText("");
        showWordExplain.setText("");
        editSuccessfully.setVisible(false);
        back.setVisible(false);
        save.setVisible(false);
        label1.setVisible(true);
        label2.setVisible(true);
        label3.setVisible(false);
        label4.setVisible(false);
        ButtonDelete.setVisible(true);
        ButtonSaveModify.setVisible(true);
        ButtonEditModify.setVisible(true);
    }

    public void onButtonDeleteFuncClicked() {
        addSuccessfully.setVisible(false);
        editSuccessfully.setVisible(false);
        String enW = showWordTarget.getText();

        if (dictionary.removeWord(enW)) {
            deleteSuccessfully.setVisible(true);
            showWordTarget.setText("");
            showWordExplain.setText("");
        } else {
            showWordExplain.setText("Delete fail!\nThis English word don't exist");
            deleteSuccessfully.setVisible(false);
        }
    }

    public void onClickSpeakerButtonInput() {
        GoogleAPI gtts = new GoogleAPI();
        if(CheckNet.netIsAvailable()) {
            gtts.speak(showWordTarget.getText());
        } else {
            CheckNet.showAlertWithHeaderText("Please connect internet!!!");
        }
    }

}
