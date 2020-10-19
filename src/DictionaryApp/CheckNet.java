package DictionaryApp;

import javafx.scene.control.Alert;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class CheckNet {
    public static Word extractWordFromString(String s) {
        s = s.trim().toLowerCase();
        int i = 0;
        StringBuilder enWBuilder = new StringBuilder();

        while(s.charAt(i) != ' ' && (i < s.length())) {
            enWBuilder.append(s.charAt(i));
            ++i;
        }
        int startTab = s.indexOf("    ");
        if(startTab >= 0 && startTab > i) {
            enWBuilder.append(s.substring(i, startTab));
            i = startTab + 1;

        }
        return new Word(enWBuilder.toString(),
                s.substring(i).trim().replaceAll(" +", " "));
    }

    public static boolean netIsAvailable() {
        try {
            final URL url = new URL("http://www.google.com");
            final URLConnection conn = url.openConnection();
            conn.connect();
            conn.getInputStream().close();
            return true;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            return false;
        }
    }

    public static void showAlertWithHeaderText(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error");
        alert.setHeaderText("Error connect Internet!!!");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
