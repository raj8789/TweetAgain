/**
 * This runner package includes TwitterRunner class.
 */
package com.twit;

import com.config.TWConfiguration;
import com.resource.TwitterController;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableScheduling
@SpringBootApplication(scanBasePackages = {"com.config",
        "com.Model",
        "com.resource",
        "com.service",
        "com.ExceptionHandler"})
public class TwitterRunner extends Application<TWConfiguration> {
    private static final Logger logger = LoggerFactory.getLogger(TwitterRunner.class);
    private static TwitterController twitterController;
    private TWConfiguration twConfiguration;
    private Environment environment;

    /**
     * Logger is used log message in this class.
     * displays message once successfully project runs and main method gets called.
     */
    public TwitterRunner(final TWConfiguration twConfiguration, final Environment environment) {
        this.twConfiguration = twConfiguration;
        this.environment = environment;
    }

    /**
     * constructor
     */
    public TwitterRunner() {
    }

    /**
     * main() used to call run().
     *
     * @param args arguments given to run().
     */
    public static void main(String[] args) {
        logger.info("Main method started");
        ConfigurableApplicationContext context = SpringApplication.run(TwitterRunner.class, args);
        twitterController = context.getBean(TwitterController.class);
    }

    /**
     * run() used to run the class and calls TwitterResources class.
     *
     * @param twConfiguration calls TWConfiguration class.
     * @param environment     sets environment to run project.
     */
    @Override
    public void run(final TWConfiguration twConfiguration, final Environment environment) {
        environment.jersey().register(twitterController);
    }
}
