package DictionaryApp;/*
 * Class: Create the Array List contain new Word
 * */

import java.util.*;

public class Dictionary {

    public ArrayList<Word> WordList = new ArrayList<>();
    public static HashMap<String, String> mapWords = new HashMap<String, String>();

    public ArrayList<Word> getWordList() {
        return WordList;
    }

    //Compare n first character of each WordTarget after get
    private static class WordComparator implements Comparator<Word> {
        public int n = 1;

        @Override
        public int compare(Word w1, Word w2) {
            return w1.getNFirstCharactersOfWordTarget(n).compareTo(w2.getNFirstCharactersOfWordTarget(n));
        }
    }

    public Dictionary() {
        mapWords = new HashMap<>();
    }

    public int size() {
        return WordList.size();
    }

    //add new word to DictionaryList with both target & explain
    public void addWord(Word word) {
        if (mapWords.get(word.getWordTarget()) == null) {
            WordList.add(word);
            mapWords.put(word.getWordTarget(), word.getWordExplain());
            sortWordList();
        }
    }

    //get word at locate n in the ArrayList<Word> WordList
    public Word getWordAt(int index) {
        if (index < 0) {
            throw new Error("Invalid value");
        }
        if (WordList.size() == 0) {
            throw new Error("Dictionary is empty");
        }
        if (WordList.size() <= index) {
            throw new Error("Index is invalid, greater than dictionary length");
        }
        return WordList.get(index);
    }

    /*
    sort all words in ArrayList<Word> WordList
     */
    public void sortWordList() {
        WordList.sort(Comparator.comparing(Word::getWordTarget));
    }

    public boolean removeWord(String enW) {
        if (mapWords.get(enW) != null) {
            for (int i = 0; i < WordList.size(); i++) {
                if (WordList.get(i).getWordTarget().equals(enW)) {
                    WordList.remove(i);
                    mapWords.remove(enW);
                }
            }
            return true;
        }
        return false;
    }

    public void removeWord(int i) {
        WordList.remove(WordList.get(i - 1));
    }

    /*
    search first n sub word
     */
    public LinkedList<Word> searchFirstSubWord(String sub) {
        WordComparator wordComparator = new WordComparator();
        wordComparator.n = sub.length();
        int retValue = Collections.binarySearch(WordList, new Word(sub, " "), wordComparator);
        LinkedList<Word> retList = new LinkedList<>();
        if (retValue >= 0) {
            retList.addFirst(getWordAt(retValue));
            int begin, end;
            begin = retValue - 1 >= 0 ? retValue - 1 : retValue;
            end = retValue + 1 < WordList.size() ? retValue + 1 : retValue;
            while (getWordAt(begin).getNFirstCharactersOfWordTarget(sub.length()).equals(sub) && begin >= 0
                    && begin < retValue) {
                retList.addFirst(getWordAt(begin));
                --begin;
                if (begin < 0) break;
            }
            while (getWordAt(end).getNFirstCharactersOfWordTarget(sub.length()).equals(sub) && end < WordList.size()
                    && retValue < end) {
                retList.addLast(getWordAt(end));
                ++end;
                if (end >= WordList.size()) break;
            }
            return retList;
        }
        return retList;
    }
}




