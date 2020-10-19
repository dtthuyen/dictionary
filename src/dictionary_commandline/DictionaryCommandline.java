package dictionary_commandline;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Dinh Thi Thanh Huyen 19020560
 */
public class DictionaryCommandline {
    public void showAllWords(Dictionary dic) {
        if (dic.Words.size() == 0) {
            System.out.println("Không có từ nào trong từ điển!");
        } else {
            System.out.println("\nNo\t|Eng\t\t|Vie");
            for (int i = 0; i < dic.Words.size(); i++) {
                Word word = dic.Words.get(i);
                System.out.println(i + "\t| " + word.getWord_target() + "\t\t| " + word.getWord_explain());
            }
        }
    }

    DictionaryManagement Management = new DictionaryManagement();
    Scanner scan = new Scanner(System.in);

    public void dictionaryBasic() throws IOException {
        int c;
        System.out.println("\n_____ Dictionary Command Line ver 1.0 _____\n");
        System.out.println("1: Thêm từ vào từ điển");
        System.out.println("2: Hiển thị danh sách các từ trong từ điển");
        System.out.println("0: Quay lại menu");
        System.out.print("\nChọn chức năng: ");
        c = scan.nextInt();

        switch (c) {
            case 0:
                Main.test();
                break;
            case 1:
                Management.insertFromCommandline();
                break;
            case 2:
                showAllWords(Management.dic);
                if (Management.dic.Words.size() == 0) {
                    System.out.println("Bạn có muốn nhập thêm từ vào từ điển không?");
                    System.out.println("1: Có");
                    System.out.println("2: Không");
                    System.out.print("Chọn: ");
                    int k = scan.nextInt();
                    if (k == 1) {
                        Management.insertFromCommandline();
                    }
                }
                break;
            default:
                break;
        }
    }

    public void dictionaryAdvanced() throws IOException {
        System.out.println("\n_____ Dictionary Command Line ver 1.1 _____\n");
        System.out.println("1: Tra cứu từ");
        System.out.println("2: Lấy dữ liệu từ file");
        System.out.println("3: Hiển thị danh sách dữ liệu của từ điển");
        System.out.println("4: Thêm 1 từ vào từ điển");
        System.out.println("5: Xóa 1 từ khỏi từ điển");
        System.out.println("6: Sửa 1 từ ở từ điển");
        System.out.println("7: Tìm từ chứa từ bạn nhập");
        System.out.println("0: Quay lại menu");
        System.out.print("\nChọn chức năng: ");
        int c = scan.nextInt();

        switch (c) {
            case 0:
                Main.test();
                break;
            case 1:
                Management.dictionaryLookup();
                break;
            case 2:
                Management.insertFromFile();
                System.out.println("Lấy dữ liệu từ file vào từ điển thành công!");
                break;
            case 3:
                showAllWords(Management.dic);
                break;
            case 4:
                Management.addWord();
                break;
            case 5:
                Management.removeWord();
                break;
            case 6:
                Management.editWord();
                break;
            case 7:
                dictionarySeacher();
            default:
                break;
        }
    }

    public void dictionarySeacher() {
        System.out.print("Nhập từ bạn muốn tìm kiếm: ");
        String s;
        Scanner scan = new Scanner(System.in);
        s = scan.nextLine();
        System.out.println("Các từ bắt đầu bằng " + s + " là: ");
        Management.dic.Words.forEach((i) -> {
            int index = i.getWord_target().indexOf(s);
            if (index == 0) {
                System.out.println(i.getWord_target() + "\t| " + i.getWord_explain());
            }
        });
    }

}
