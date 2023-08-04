package com.example.TweetSentimentAnalyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Analyzer {
    private List<String> positiveWords;
    private List<String> negativeWords;
    private int pos = 0;
    private int neg = 0;
    private int neu = 0;



    public Analyzer(String positives, String negatives){
        positiveWords = new ArrayList<>(); // contains a list of postive words
        negativeWords = new ArrayList<>();
        try{
            readWordsFromFile(positives, positiveWords);
            readWordsFromFile(negatives, negativeWords);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getPos(){
       return this.pos;
    }
    public int getNeg(){
       return this.neu;
    }
    public int getNeu(){
       return this.neu;
    }

    public int analyze(String text){
        String[] words = text.split(" ");
        int score = 0;
        for(String word: words){
            if(positiveWords.contains(word.toLowerCase())){
                score++;
                pos++;
            }
            else if (negativeWords.contains(word.toLowerCase())){
                score--;
                neg++;

            }
            else{
                neu++;
            }
        }
        return score;
    }

    private void readWordsFromFile(String filename, List<String> wordList) throws FileNotFoundException {
        File file = new File(filename);
        Scanner s = new Scanner(file);
        while (s.hasNextLine()) {
            String line = s.nextLine().trim();
            if(!line.startsWith(";")){
                wordList.add(line);
            }
        }
    }
}