package Finder;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;


public class WordFinder {

    private List<String> resultList = new ArrayList<>();
    private List<Thread> threads = new ArrayList<>();


    public void getOccurencies(String[] sources, String[] words, String res) {

        ThreadPool threadPool = new ThreadPool(3);
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Stream<String> sorted = Arrays.stream(sources);
        sorted.forEach(x-> {
           Words parser = new Words(resultList,words,x);
           threads.add(threadPool.runThread(parser));
        });

        System.out.println(resultList);
        save(res);

    }

    private void save(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            resultList.forEach(s -> {
                try {
                    writer.write(s);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

