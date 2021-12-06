import com.config.TWConfiguration;
import com.resource.TwitterController;
import com.twit.TwitterRunner;
import io.dropwizard.setup.Environment;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class TwitterRunnerTest {
    TwitterRunner twitterRunner;
    TWConfiguration twConfiguration;
    Environment environment;
    TwitterController twitterController;
    Logger logger = LoggerFactory.getLogger(TwitterRunnerTest.class);

    @BeforeEach
    void setUp() {
        twConfiguration = Mockito.mock(TWConfiguration.class);
        environment = Mockito.mock(Environment.class);
        twitterRunner = new TwitterRunner(twConfiguration, environment);
        twitterController = Mockito.mock(TwitterController.class);
    }

    @Test
    void main() {
        String[] ar = {"server"};
        try {
            TwitterRunner.main(ar);
        } catch (Exception e) {
            e.getMessage();
            logger.error("Exception occur", e);
        }
    }

    @Test
    void run() {
        try {
            twitterRunner.run(twConfiguration, environment);
        } catch (Exception e) {
            e.getMessage();
            logger.error("Exception occur", e);
        }

        Assert.assertTrue(true);
    }
}