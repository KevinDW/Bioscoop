package be.bioscoop.queues;

import be.bioscoop.config.Database;
import be.bioscoop.dao.FilmDAO;
import be.bioscoop.dao.SocialDAO;
import be.bioscoop.models.Social;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import javax.jms.*;
import javax.jms.Connection;
import java.io.StringReader;
import java.sql.*;
import java.util.Iterator;

public class SocialReceiver
{
    public void receiveMessage() throws JMSException
    {
        // Connecteren naar ActiveMQ
        javax.jms.Connection amqConnection = new ActiveMQConnectionFactory("tcp://192.168.20.200:61616").createConnection();

        // Connectie starten
        amqConnection.start();

        // Start een sessie om met ActiveMQ te werken
        // -> false = geen transacties
        // -> true = transacties
        Session session = amqConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Maak een nieuwe queue aan (of krijg de reeds aangemaakte terug)
        Destination destination = session.createQueue("TEST.SENDRECEIVE");

        // Maak een consumer aan die berichten van de queue ontvangt
        MessageConsumer consumer = session.createConsumer(destination);

        try
        {
            Message message = consumer.receive(1000);
            TextMessage textMessage = (TextMessage) message;

            java.sql.Connection dbConnection = Database.connect();

            FilmDAO filmDAO = new FilmDAO(dbConnection);
            SocialDAO socialDAO = new SocialDAO(dbConnection);

            SAXBuilder sax = new SAXBuilder();
            Document doc = sax.build(new StringReader(textMessage.getText()));
            Element root = doc.getRootElement();

            for (Object object : root.getChildren())
            {
                Element element = (Element) object;
                Social social = new Social();

                social.setDatum(Date.valueOf(element.getChildText("Datum")));
                social.setType(element.getChildText("Type"));
                social.setBericht(element.getChildText("Bericht"));
                social.setFilm(filmDAO.get(Integer.parseInt(element.getChildText("Film"))));

                socialDAO.insert(social);
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }

        // Consumer sluiten
        consumer.close();

        // Sessie sluiten
        session.close();

        // Connectie sluiten
        amqConnection.close();
    }
}
