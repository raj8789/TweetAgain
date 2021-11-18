package com.twit;

import com.config.TWConfiguration;
import com.resource.TwitterController;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TwitterRunner extends Application<TWConfiguration> {
    TWConfiguration twConfiguration;
    Environment environment;
    private static Logger logger= LoggerFactory.getLogger(TwitterRunner.class);
    public TwitterRunner(TWConfiguration twConfiguration, Environment environment)
    {
            this.twConfiguration=twConfiguration;
            this.environment=environment;
    }
    public TwitterRunner(){}
    public static void main(String[] args) throws Exception {
        logger.info("Main method started");
        new TwitterRunner().run(args);
    }

    @Override
    public void run(TWConfiguration twConfiguration, Environment environment) throws Exception{
        environment.jersey().register(new TwitterController());
    }
}
