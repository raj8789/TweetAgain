package test.java.com;

import com.config.TWConfiguration;
import com.service.TwitterImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TwitterImplTest {
    TWConfiguration twConfiguration;
    ConfigurationBuilder configurationBuilder;
    TwitterFactory twitterFactory;
    TwitterImpl twitterImpl;
    @Before
    public void setUp()
    {
        configurationBuilder=new ConfigurationBuilder();
        twConfiguration= Mockito.mock(TWConfiguration.class);
        twitterFactory=new TwitterFactory(configurationBuilder.build());
        twitterImpl=new TwitterImpl(twConfiguration,configurationBuilder,twitterFactory);
    }
    @Test
    public void getTwitterObjectTestCase()
    {
        //when(twConfiguration.configurationBuilder()).thenReturn(configurationBuilder);
        Twitter twitterExpected=twitterImpl.getTwitterObject();
        Assert.assertEquals("twitter4j.TwitterImpl",twitterExpected.getClass().getName());
    }
}