package be.bioscoop.queues;

import be.bioscoop.config.Database;
import be.bioscoop.config.Queue;
import be.bioscoop.dao.FilmDAO;
import be.bioscoop.dao.SocialDAO;
import be.bioscoop.entities.Social;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import javax.jms.*;
import java.io.IOException;
import java.io.StringReader;
import java.sql.Date;
import java.sql.SQLException;

public class SocialReceiver
{
    public void receiveMessage() throws JMSException, JDOMException, IOException, SQLException
    {
        // Connecteren naar ActiveMQ
        javax.jms.Connection amqConnection = Queue.connect();

        // Connectie starten
        amqConnection.start();

        // Start een sessie om met ActiveMQ te werken
        // -> false = geen transacties
        // -> true = transacties
        Session session = amqConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Maak een nieuwe queue aan (of krijg de reeds aangemaakte terug)
        Destination destination = session.createQueue("SOCIAL.MESSAGES");

        // Maak een consumer aan die berichten van de queue ontvangt
        MessageConsumer consumer = session.createConsumer(destination);

        // Haal bericht binnen uit queue
        Message message = consumer.receive(1000);

        // Bericht toevoegen aan database
        addMessageToDatabase((TextMessage) message);

        // Sluit MessageConsumer, Session en Connection
        Queue.close(session, consumer);
    }

    private void addMessageToDatabase(TextMessage textMessage) throws SQLException, JDOMException, IOException, JMSException
    {
        java.sql.Connection dbConnection = Database.connect();

        FilmDAO filmDAO = new FilmDAO(dbConnection);
        SocialDAO socialDAO = new SocialDAO(dbConnection);

        String text = textMessage.getText();
        StringReader xml = new StringReader(text);

        SAXBuilder sax = new SAXBuilder();
        Document doc = sax.build(xml);
        Element root = doc.getRootElement();

        for (Object object : root.getChildren())
        {
            Element element = (Element) object;

            socialDAO.insert(
                new Social(
                    Date.valueOf(element.getChildText("Datum")),
                    element.getChildText("Type"),
                    element.getChildText("Bericht"),
                    filmDAO.find(Integer.parseInt(element.getChildText("Film")))
                )
            );
        }

        Database.close();
    }
}
