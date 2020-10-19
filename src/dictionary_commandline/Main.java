package dictionary_commandline;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Dinh Thi Thanh Huyen 19020560
 */
public class Main {
    static DictionaryCommandline newCml = new DictionaryCommandline();

    public static void test() throws IOException {
        int c;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("\nMenu:");
            System.out.println("1: Dictionary CommandLine cơ bản: dữ liệu trong từ điển do bạn nhập.");
            System.out.println("2: Dictionary CommandLine cải tiến: dữ liệu lấy từ file.");
            System.out.println("0: Thoát");
            System.out.print("\nChọn chức năng: ");
            c = scan.nextInt();

            switch (c) {
                case 0:
                    System.out.println("Cảm ơn bạn đã sử dụng Dictionary Command Line!");
                    break;
                case 1:
                    newCml.dictionaryBasic();
                    break;
                case 2:
                    newCml.dictionaryAdvanced();
                    break;
                default:
                    break;
            }
        } while (c != 0);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("_______________ Dictionary Command Line ver 1.2 _______________");
        test();
    }

}

