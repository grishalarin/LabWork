package Finder;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        WordFinder wordFinder = new WordFinder();
        Scanner scanner = new Scanner(System.in);
        scanner.hasNextLine();
        String[] files = {};
        for (int i = 0; i < 1; i++) {
            files[i] = "C://Example//file" + i + ".txt";
        }
        String[] words = {"spring", "innopolis", "java"};
        wordFinder.getOccurencies(files, words, "C://Example//result.txt");
    }
}
