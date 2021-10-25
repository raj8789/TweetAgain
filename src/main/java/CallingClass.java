import twitter4j.TwitterException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
public class CallingClass {
    public static void main(String[] args) {
        RetrieveTweets lt=new RetrieveTweets();
        SendTweet st=new SendTweet();
        BufferedReader inp = new BufferedReader (new InputStreamReader(System.in));
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Your Choice \n 1.SendTweet \n 2.RetrieveTweets");
        int ch= 0;
        ch = sc.nextInt();
        if(ch==1)
        {
            System.out.println("Enter Your Tweet \n");
            String s= null;
            try {
                s = inp.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                st.sendTweet(s);
            } catch (TwitterException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                lt.latestTweet();
            } catch (TwitterException e) {
                e.printStackTrace();
            }
        }
    }
}

