package Finder;



public class Main {
    public static void main(String[] args) {

        WordFinder wordFinder = new WordFinder();
        String [] files = new String[1];
        for (int i = 0; i < 1; i++) {
            files[i] ="C://Example//file" + i + ".txt";
        }
        String[] words = {"spring","innopolis","java"};
        wordFinder.getOccurencies(files,words,"C://Example//result.txt");
    }
}
