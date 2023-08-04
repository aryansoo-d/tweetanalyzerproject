package com.example.TweetSentimentAnalyzer;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
//import org.twitter4j.*;

import java.util.ArrayList;
import java.util.List;

public class TwitterAPI{
    Twitter twitter;
    public TwitterAPI(){
        String consumerKey ="AD4I5mQbuoCNTzoLJD5jO1EIs";
        String consumerSecret = "Bzp7rCCSL8gaGamta6vCZdLF6lrEBWEbekbKu2fm1bLzRcwel3";
        String accessToken = "1685040464217714688-7QsIK4628CBDfkmFmknQQc44TSh9pU";
        String accessTokenSecret = "vh7LcgiNvjmkl0JTRr1nPafy8kgFJjvXdzu6mToNAAcaW";
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);

        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();}




//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter the Twitter screen name: ");
//        String screenName = scanner.nextLine();
//        int count = 200;
       public List<String> retrieveTweets(String screenName)  {
           int count = 20;
           int pageCount = 1;
           List<String> tweetList = new ArrayList<>();
       try {
           List<Status> tweets = twitter.getUserTimeline(screenName, new Paging(1, count));
           for (Status tweet : tweets)
                tweetList.add(tweet.getText());
           }
       catch (TwitterException te) {
           te.printStackTrace();
       }
       return tweetList;
    }
}