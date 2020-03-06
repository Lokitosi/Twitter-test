package Logica;

import java.util.List;
import javax.swing.JOptionPane;
import twitter4j.Paging;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Controlador {

    public static void main(String arg[]) throws TwitterException {
        String usuario = null;
        int likes;
        int minlikes = 0;
        String tweet = null;
        String nombre = JOptionPane.showInputDialog("ingrese el usuario a buscar");
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
        List<Status> statuses = twitter.getUserTimeline(nombre , new Paging(1, 500));
        System.out.println("Mostrando tweets del usuario : " + nombre);
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
        String txt = "el tweet mas gustado fue :";
        txt += "\n"+ usuario + ": "+ tweet +"\n"; 
        txt += "con : "+ minlikes + " likes";
        JOptionPane.showMessageDialog(null,txt);
            System.out.println("El tweet mas gustado fue:");
            System.out.println(usuario + ":" + tweet);

        
    }
}
