package com.service;

import com.config.TWConfiguration;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterImpl {
    TWConfiguration twConfiguration;
    ConfigurationBuilder configurationBuilder;
    TwitterFactory twitterFactory;
    public TwitterImpl(TWConfiguration twConfiguration,ConfigurationBuilder configurationBuilder,TwitterFactory twitterFactory)
    {
        this.twConfiguration=twConfiguration;
        this.configurationBuilder=configurationBuilder;
        this.twitterFactory=twitterFactory;
    }
    public TwitterImpl()
    {
    }
    public Twitter getTwitterObject()
    {
        TWConfiguration twConfiguration=new TWConfiguration();
        ConfigurationBuilder configurationBuilder = twConfiguration.configurationBuilder();
        TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build());
        Twitter twitter = twitterFactory.getInstance();
        return twitter;
    }
}
