package com.twit;

import com.config.TWConfiguration;
import com.resource.TwitterController;
import com.service.TwitterImpl;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication(scanBasePackages = { "com.config","com.Model","com.resource","com.service" ,"com.ExceptionHandler"})
public class TwitterRunner extends Application<TWConfiguration> {
    TWConfiguration twConfiguration;
    Environment environment;
    static TwitterController twitterController;
    private static Logger logger= LoggerFactory.getLogger(TwitterRunner.class);
    public TwitterRunner(TWConfiguration twConfiguration, Environment environment)
    {
            this.twConfiguration=twConfiguration;
            this.environment=environment;
    }
    public TwitterRunner(){}
    public static void main(String[] args){
        logger.info("Main method started");
         ConfigurableApplicationContext context=SpringApplication.run(TwitterRunner.class,args);
         twitterController=context.getBean(TwitterController.class);
    }
    @Override
    public void run(TWConfiguration twConfiguration, Environment environment){
        environment.jersey().register(twitterController);
    }
}
