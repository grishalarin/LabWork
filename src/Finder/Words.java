package Finder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Words implements Runnable{

    private List<String> resultList;
    private String[] words;
    private String source;

    public Words(List<String> resultList, String[] words, String source) {
        this.resultList = resultList;
        this.words = words;
        this.source = source;
    }

    @Override
    public void run() {
        parse(source);
    }

    public void parse(String sources){
        Matcher matcher;
        try(Scanner scanner = new Scanner(new File(sources))) {
            Pattern pattern = Pattern.compile("[;]");
            scanner.useDelimiter("[\n]");
            while (scanner.hasNext()){
                matcher= pattern.matcher(scanner.next());
                while (matcher.find()){
                    String sentence = matcher.group();
                    checkWordsInSentence(sentence,words);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveResultSentence(String sentence) {
        synchronized (resultList) {
            resultList.add("\n" + sentence.trim());
        }
    }

    private boolean checkWordInSentence(String sentence, String word) {
        sentence = sentence.toLowerCase();
        return sentence.contains(word);
    }

    private void checkWordsInSentence(String sentence, String[] words) {
        for (String word : words) {
            if (checkWordInSentence(sentence, word)) {
                saveResultSentence(sentence);
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "Words"+"source= "+ source +'\''+'}';
    }

}
