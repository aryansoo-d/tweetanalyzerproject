package com.example.TweetSentimentAnalyzer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileNotFoundException;
import java.util.List;


@Controller
public class SentimentAnalysisController {

    private Analyzer tweetAnalyzer;
    private TwitterAPI twitterAPI;

    public SentimentAnalysisController() {
        // Initialize the tweetAnalyzer
        this.tweetAnalyzer = new Analyzer("src/main/resources/data1/positive-words.txt", "src/main/resources/data1/negative-words.txt");
        this.twitterAPI = new TwitterAPI();
    }

    @GetMapping("/")
    public String home() {
        return "index"; // This will return the "index.html" template.
    }
    @PostMapping("/analyze")
    public String analyzeTweets(@RequestParam String username, Model model) {
        // Retrieve tweets from the specified user using TwitterAPI.
        List<String> tweets = twitterAPI.retrieveTweets(username);

        // Analyze sentiments of each tweet and update sentiment counts in the Analyzer.
        for (String tweet : tweets) {
            tweetAnalyzer.analyze(tweet);
        }

        // Get sentiment counts from the Analyzer.
        int posCount = tweetAnalyzer.getPos();
        int negCount = tweetAnalyzer.getNeg();
        int neuCount = tweetAnalyzer.getNeu();

        // Calculate the total number of tweets.
        int totalTweets = posCount + negCount + neuCount;

        // Calculate the percentages of each sentiment category.
        double posPercentage = (double) posCount / totalTweets * 100;
        double negPercentage = (double) negCount / totalTweets * 100;
        double neuPercentage = (double) neuCount / totalTweets * 100;

        // Add the sentiment data to the model to be used in the template.
        model.addAttribute("username", username);
        model.addAttribute("posPercentage", posPercentage);
        model.addAttribute("negPercentage", negPercentage);
        model.addAttribute("neuPercentage", neuPercentage);

        return "result"; // This will return the "result.html" template.
    }


}