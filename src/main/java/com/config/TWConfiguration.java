package com.config;

import io.dropwizard.Configuration;
import org.springframework.stereotype.Component;
import twitter4j.conf.ConfigurationBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@Component
public class TWConfiguration extends Configuration {
    /**
     * .
     * store file name
     */
    private final String filepath = "twitter4j.yml";
    /**
     * .
     * properties object creation
     */
    private final Properties properties = new Properties();
    /**
     * .
     * store accessTokenSecret
     */
    private String accessTokenSecret = "";
    /**
     * .
     * store consumerSecret
     */
    private String consumerSecret = "";
    /**
     * .
     * store consumerKey
     */
    private String consumerKey = "";
    /**
     * .
     * store accessToken
     */
    private String accessToken = "";
    /**
     * .
     * store fileInputStream object
     */
    private FileInputStream fileInputStream;

    {
        try {
            fileInputStream = new FileInputStream(filepath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        accessTokenSecret = properties.getProperty("accessTokenSecret");
        consumerSecret = properties.getProperty("consumerSecret");
        consumerKey = properties.getProperty("consumerKey");
        accessToken = properties.getProperty("accessToken");
    }

    /**
     * .
     *
     * @return configurationbuilder object
     */
    public ConfigurationBuilder configurationBuilder() {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);
        return configurationBuilder;
    }
}
