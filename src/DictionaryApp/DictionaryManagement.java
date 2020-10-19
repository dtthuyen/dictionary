package DictionaryApp;

import javax.swing.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class DictionaryManagement {
    public Dictionary dictionary;
    public Dictionary getDictionary() {
        return dictionary;
    }
    private File file = new File("src\\DictionaryApp\\newdict.txt"); // or from dic.txt
    public static DefaultListModel<String> model = new DefaultListModel<>();

    public DictionaryManagement() {
        dictionary = new Dictionary();
    }

    /*
    Read word from file // New Dict.txt
     */
    public void insertFromFile() {
        try {
            String line;
            Scanner scan = new Scanner(new BufferedReader(new FileReader(file)));
            while (scan.hasNext()) {
                line = scan.nextLine();
                String target = "";
                String explain = "";
                /*String target = scan.nextLine();
                String explain = scan.nextLine();*/

               while (line.trim().length() != 0) {
                    if (line.indexOf("@") == 0) {
                        line = line.replace("@", "");
                        target = line;
                        line = scan.nextLine();
                    } else if (line.indexOf("^") == 0) {
                        line = line.replace("^", "");
                        explain = line;
                        line = scan.nextLine();
                    } else if (line.indexOf("$") == 0) {
                        line = line.replace("$", "");
                        explain = explain + "\n" + line;
                        line = scan.nextLine();
                    } else {
                        if (explain == "") {
                            explain = explain + line;
                        } else {
                            explain = explain + "\n" + line;
                        }
                        line = scan.nextLine();
                    }
                }
                dictionary.addWord(new Word(target, explain));
            }
            scan.close();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //Search First n sub words
    public LinkedList<Word> searchFirstSubWord(String sub) {
        return dictionary.searchFirstSubWord(sub);
    }

}
