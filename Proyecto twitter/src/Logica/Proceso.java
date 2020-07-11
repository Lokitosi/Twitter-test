package Logica;

import Vista.Vista;
import java.util.List;
import java.util.Properties;
import javax.swing.JOptionPane;
import twitter4j.Paging;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Proceso {
    private String tweet;
    private String user;
    private Vista ventana = new Vista(this);

        public Proceso(){
           ventana.setVisible(true);
           ventana.setLocationRelativeTo(null);
            
        }
        
    public void Hacerbusqueda() throws TwitterException{
        String usuario = null;
        
        int minlikes = 0;
        String tweet = null;
        //Inicialización
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("WtmWLNY5xInp5HJKoRzJyykus")
                .setOAuthConsumerSecret("TwJymWMo5N0olGDUkXFUUfGCPO1UtuXlEgx5VQach3TI9Rn1TT")
                .setOAuthAccessToken("2468632166-6w3MPMEi5icfRTJ1IzHJoCWDn325l4RGf24KDuA")
                .setOAuthAccessTokenSecret("FwfrQFAScvT1IivzVb30nsBsRBZbTwDVmswVpLAF1KPz7");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();

        //Extraer el timeline de un usuario.
        List<Status> statuses = twitter.getUserTimeline(user, new Paging(1 ,1000));
        System.out.println("Mostrando tweets del usuario : " + user);
        for (Status status : statuses) {
            System.out.println(status.getUser().getName() + ":"
                    + status.getText());
            System.out.println("Likes: " + status.getFavoriteCount());
            System.out.println("");
            if (status.getFavoriteCount() > minlikes) {
                tweet = status.getText();
                minlikes = status.getFavoriteCount();
                usuario = status.getUser().getName();

            }
        }
        String txt = "El tweet mas gustado fue :";
        txt += "\n" + usuario + ": " + tweet + "\n";
        txt += "con : " + minlikes + " likes";
        ventana.getjTextArea1().setText(txt);
  
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
   
}
