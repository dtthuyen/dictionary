package dictionary_commandline;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Dinh Thi Thanh Huyen 19020560
 */
public class DictionaryManagement {
    Scanner scan = new Scanner(System.in);
    Dictionary dic = new Dictionary();

    private Word insertWord(){
        Word newWord = new Word();
        System.out.print("Nhập từ: ");
        newWord.setWord_target(scan.nextLine());
        System.out.print("   Nhập nghĩa: ");
        newWord.setWord_explain(scan.nextLine());
        return newWord;
    }

    public void insertFromCommandline(){
        System.out.print("Số lượng từ muốn nhập thêm: ");
        int sizeAdd = Integer.parseInt(scan.nextLine());
        for (int i = 1; i < sizeAdd + 1; i++) {
            System.out.print(i + ". ");
            dic.Words.add(insertWord());
        }
    }

    /**
     * Use insert list Word from my file text
     * No parameter and return void
     * @throws FileNotFoundException, IOException
     */
    public void insertFromFile() throws IOException  {
        File file = new File("C:\\Users\\dtth2\\IdeaProjects\\Dictionary\\src\\main\\java\\dictionary_commandline\\dictionaries.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        //Iterator<String> it = dic.Words.iterator();
        while ((line = br.readLine()) != null) {
            Scanner input = new Scanner(line).useDelimiter("\t");    // Su dung phương thuc useDelimiter() de dinh dang nhap vao
            Word word = new Word();
            word.setWord_target(input.next());
            word.setWord_explain(input.next());
            dic.Words.add(word);
        }
    }

    /**
     * tra cuu tu dien.
     *
     */
    public void dictionaryLookup(){
        String a;
        System.out.print("\nNhập từ muốn tra: ");
        a = scan.nextLine();
        int k = 0;
        for (int i = 0; i < dic.Words.size(); i++) {
            if (dic.Words.get(i).getWord_target().equals(a)) {
                System.out.println(a + ": " + dic.Words.get(i).getWord_explain());
                k = 1;
            }
        }
        if (k == 0) {
            System.out.println("Không tìm thấy từ trên trong từ điển!");
        }
    }

    /**
     * Puts all the word
     * of current dictionary in File "dic.txt"
     * @throws FileNotFoundException, IOException
     */
    public void dictionaryExportToFile() throws IOException {
        File file = new File("C:\\Users\\dtth2\\IdeaProjects\\Dictionary\\src\\dictionary_commandline\\dic.txt");
        FileOutputStream f = new FileOutputStream(file);
        OutputStreamWriter fw = new OutputStreamWriter(f);
        try {
            for (Word i: dic.Words) {
                String line = i.getWord_target() + "\t" + i.getWord_explain();
                fw.write(line);
                fw.write(System.lineSeparator()) ;
            }
            fw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //Ham them mot tu tu command line
    public void addWord() {
        System.out.print("Nhập từ bạn muốn thêm theo định dạng từ_tiếng_anh \\t nghĩa_tiếng_việt: ");
        String stringWord = scan.nextLine();
        Scanner s = new Scanner(stringWord).useDelimiter("s*\ts*");
        String target = s.next();
        String explain = s.next();
        Word newWord = new Word(target, explain);
        boolean check = false;
        for (Word i: dic.Words) {
            if (i.getWord_target().equalsIgnoreCase(target)) {
                check = true;
                System.out.println("Từ này đã có trong từ điển!");
                break;
            }
        }
        if (!check) {
            dic.Words.add(newWord);
            System.out.println("Thêm từ thành công!");
        }
    }

    //Ham xoa tu command_line
    public void removeWord() {
        String target;
        System.out.print("Nhập từ bạn muốn xóa: ");
        target = scan.nextLine();
        boolean check = false;
        for (Word i: dic.Words) {
            if (i.getWord_target().equalsIgnoreCase(target)) {
                dic.Words.remove(i);
                System.out.println("Đã xóa thành công!");
                check = true;
            }
        }
        if (!check) {
            System.out.println("Không có từ này trong từ điển!");
        }
    }

    //Ham sua du lieu tu command_line
    public void editWord() {
        System.out.print("Nhập từ bạn muốn sửa: ");
        String word = scan.nextLine();
        boolean check = false;
        for (Word i: dic.Words) {
            if (i.getWord_target().equalsIgnoreCase(word)) {
                System.out.print("Bạn muốn sửa thành: ");
                String target = scan.nextLine();
                i.setWord_target(target);
                System.out.print("Bạn muốn thay đổi nghĩa của từ thành: ");
                String explain = scan.nextLine();
                i.setWord_explain(explain);
                check = true;
                break;
            }
        }
        if (!check) {
            System.out.println("Không có từ này trong từ điển!");
        }

    }

}
