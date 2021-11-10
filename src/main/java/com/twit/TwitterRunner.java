package com.twit;


import com.config.TWConfiguration;
import com.resource.TwitterController;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class TwitterRunner extends Application<TWConfiguration> {
    TWConfiguration twConfiguration;
    Environment environment;
    public TwitterRunner(TWConfiguration twConfiguration, Environment environment)
    {
            this.twConfiguration=twConfiguration;
            this.environment=environment;
    }
    public TwitterRunner(){}
    public static void main(String[] args) throws Exception {
        new TwitterRunner().run(args);
    }

    @Override
    public void run(TWConfiguration twConfiguration, Environment environment) throws Exception{
        environment.jersey().register(new TwitterController());
    }
}
