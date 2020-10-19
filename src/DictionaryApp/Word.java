package DictionaryApp;

public class Word {
    private String wordTarget;
    private String wordExplain;

    public String getWordTarget() {
        return this.wordTarget;
    }

    public void setWordTarget(String word_target) {
        this.wordTarget = word_target;
    }

    public String getWordExplain() {
        return this.wordExplain;
    }

    public void setWordExplain(String word_explain) {
        this.wordExplain = word_explain;
    }

    public Word()
    {
        this.wordExplain = null;
        this.wordTarget = null;
    }

    public Word(String word_target, String word_explain)
    {
        this.wordTarget = word_target;
        this.wordExplain = word_explain;
    }


    /*
    get n digit first of a word (To sort)
     */
    public String getNFirstCharactersOfWordTarget(int n) {
        if(n >= 1) {
            if(wordTarget.length() >= n) {
                return wordTarget.substring(0, n);  //get n digit first
            }

            else {
                StringBuilder s = new StringBuilder();
                s.append(wordTarget);
                int diff = n - wordTarget.length();
                while(diff > 0) {
                    s.append(" ");
                    --diff;
                }
                return  s.toString();
            }
        }

        else {
            return "";
//            throw new Error("number element must >= 1");
        }
    }
}



