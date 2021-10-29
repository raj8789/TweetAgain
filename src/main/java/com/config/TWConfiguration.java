package com.config;

import io.dropwizard.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class TWConfiguration extends Configuration {

    public static ConfigurationBuilder configurationBuilder()
    {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("Iq2STwqt6DsfrdAX5kZz37RVP")
                .setOAuthConsumerSecret("zhhpECdqZKhgukHibrQstKDPbXOKxEL6whLcw2fROR0U0TEoQ0")
                .setOAuthAccessToken("1451106875525636098-LrOSaFuEOKZDGi3QjzLeAPTZQeopZR")
                .setOAuthAccessTokenSecret("tRtzJ3BnJBJVumiX4BA15xGtwgfyiMTyMZRKCYYDZuOAF");
        return cb;
    }
}
