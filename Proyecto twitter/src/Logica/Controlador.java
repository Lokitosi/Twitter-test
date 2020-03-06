package Logica;

import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import twitter4j.Paging;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Controlador {

    public static void main(String arg[]) throws TwitterException, AddressException, MessagingException {
        String usuario = null;
        
        int minlikes = 0;
        String tweet = null;
        String nombre = JOptionPane.showInputDialog("ingrese el @ del usuario a buscar sin el @"
                + "");
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
        List<Status> statuses = twitter.getUserTimeline(nombre, new Paging(1 ,100));
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
        txt += "\n" + usuario + ": " + tweet + "\n";
        txt += "con : " + minlikes + " likes";
        JOptionPane.showMessageDialog(null, txt);
        System.out.println("El tweet mas gustado fue:");
        // mensajeria time
         Properties propiedad = new Properties();
       propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
        propiedad.setProperty("mail.smtp.starttls.enable", "true");
        propiedad.setProperty("mail.smtp.port", "587");
        propiedad.setProperty("mail.smtp.auth", "true");
        
        
        Session sesion = Session.getDefaultInstance(propiedad);
        String correoEnvia = "fred.starks.97@gmail.com";
        String contrasena = "fredstark123";
        String receptor = "alfiesparra@hotmail.com";
        String asunto = "Correo Desde Java";
        String mensaje=txt;
        
        MimeMessage mail = new MimeMessage(sesion);
        try{
            mail.setFrom(new InternetAddress (correoEnvia));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress (receptor));
            mail.setSubject(asunto);
            mail.setText(mensaje);
            
            Transport transportar = sesion.getTransport("smtp");
            transportar.connect(correoEnvia,contrasena);
            transportar.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));          
            transportar.close();
            
            JOptionPane.showMessageDialog(null, "Listo, revise su correo");
             } catch (AddressException ex) {
                 System.out.println("error");
        } catch (MessagingException ex) {
            System.out.println("error");
        }

    }
   
}
