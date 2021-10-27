package com.twit;

import com.config.MessageQueueClient;
import com.config.TWConfiguration;
import com.resource.TwitterWorks;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class TwitterRunner extends Application<TWConfiguration> {
    public static void main(String[] args) throws Exception {
       new TwitterRunner().run(args);
    }
    @Override
    public void run(TWConfiguration twConfiguration, Environment environment) throws Exception {
        System.out.println("running");
        MessageQueueClient messageQueue = twConfiguration.getMessageQueueFactory().build(environment);
        environment.jersey().register(new TwitterWorks());
    }
}
