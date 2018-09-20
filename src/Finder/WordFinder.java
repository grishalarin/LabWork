package Finder;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class WordFinder {

    private List<String> resultList = new ArrayList<>();
    private List<Thread> threads = new ArrayList<>();


    public void getOccurencies(String[] sources, String[] words, String res){

        ThreadPool threadPool = new ThreadPool(3);
        for (Thread thread: threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (String source: sources){
            Words parser = new Words(resultList,words,source);
            threads.add(threadPool.runThread(parser));
        }
        System.out.println(resultList);
        save(res);

    }

    private void save(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String s : resultList) {
                writer.write(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
