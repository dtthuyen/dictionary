package DictionaryApp;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static DictionaryApp.DictionaryApplication.dictionaryManagement;

public class HomeController implements Initializable {

    public static boolean isSearch = true;

    @FXML
    private AnchorPane view;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private BorderPane background;

    @FXML
    private JFXButton ButtonManageWord;

    @FXML
    private JFXButton reSearch;

    @FXML
    private JFXListView<Label> showAllWord;

    @FXML
    private AnchorPane topBar;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private TextArea textArea;

    @FXML
    private TextField inputBox;

    @FXML
    private void onClickManageWord(ActionEvent event) throws IOException {
        Parent addWord = FXMLLoader.load(getClass().getResource("ManageWord.fxml"));
        Scene addWord_scene = new Scene(addWord);
        Stage addWord_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        addWord_stage.setScene(addWord_scene);
        addWord_stage.show();
    }

    /*
    Call Navigation Drawer and control it
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reSearch.setVisible(false);
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
            searchOnKeyRelease();
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void onClickSpeakerButtonInput() {
        GoogleAPI gtts = new GoogleAPI();
        if(CheckNet.netIsAvailable()) {
            gtts.speak(inputBox.getText());
        } else {
            CheckNet.showAlertWithHeaderText("Please connect internet!!!");
        }
    }

    public void searchOnKeyRelease() {
        inputBox.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case ENTER:
                    break;
                default:
                    if (isSearch) {
                        isSearch = false;
                        try {
                            Platform.runLater(() -> {
                                        StringBuilder curEnWord = new StringBuilder(inputBox.getText().trim().toLowerCase().replace(" +", " ").replace("\n", ""));
                                        searchFirstSubWordAndUpdateListView(curEnWord.toString());
                                        isSearch = true;
                                    }
                            );
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
        });
    }

    public String convert(String s) {
        int k = 0;
        String t = "";
        for(int i = 0; i < s.length(); i++) {
            t += s.charAt(i);
            k++;
            if (k > 45 && s.charAt(i) == ' ') {
                t += "\n";
                k = 0;
            }
        }
        return t;
    }

    public void searchActionPerformed() {
        reSearch.setVisible(true);
        String word_search = inputBox.getText();
        showAllWord.setVisible(false);
        if (dictionaryManagement.dictionary.mapWords.get(word_search) != null) {
            textArea.setText(convert(dictionaryManagement.dictionary.mapWords.get(word_search)));
        }
        else {
            textArea.setText("\nKhông tìm thấy thông tin bạn cần tìm!\n"
                    + "Gợi ý: Sử dụng chức năng dịch online!");
        }
    }

    private void searchFirstSubWordAndUpdateListView(String searchEnW) {
        LinkedList<Word> result = dictionaryManagement.searchFirstSubWord(searchEnW);
        showAllWord.getItems().clear();
        result.forEach(word -> {
            Label label = new Label(word.getWordTarget());
            label.setOnMousePressed(event -> {
                try {
                    reSearch.setVisible(true);
                    showAllWord.setVisible(false);
                    inputBox.setText(convert(word.getWordTarget()));
                    textArea.setText(convert(word.getWordExplain()));
                } catch (Exception e) {
                    System.out.println();
                }
            });
            showAllWord.getItems().add(label);
        });
    }

    public void reSearchAction() {
        showAllWord.setVisible(true);
        inputBox.setText("");
    }

}
