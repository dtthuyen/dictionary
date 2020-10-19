package DictionaryApp;

import com.darkprograms.speech.synthesiser.SynthesiserV2;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.IOException;
import java.io.InputStream;

public class GoogleAPI {

    //Create a Synthesizer instance
    private SynthesiserV2 synthesizer = new SynthesiserV2("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");
    private String lang = "en-US";
    public InputStream is = null;

    public GoogleAPI() {
        synthesizer.setLanguage(lang);
    }

    public GoogleAPI(String language) {
        lang = language;
        synthesizer.setLanguage(lang);
    }

    public InputStream getIs() {
        return is;
    }

    public void speak(String text) {
        //Create a new Thread because JLayer is running on the current Thread and will make the application to lag
        Thread thread = new Thread(() -> {
            try {
                is = synthesizer.getMP3Data(text);
                Player player = new Player(is);
                player.play();
                System.out.println("Successfully got back synthesizer data");
            } catch (IOException | JavaLayerException e) {
                e.printStackTrace();
            }
        });

        //don't want the application to terminate before this Thread terminates
        thread.setDaemon(false);

        //Start the Thread
        thread.start();
    }
}