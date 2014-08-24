package be.bioscoop.queues;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class SocialSender
{
    public void sendMessage(String message) throws JMSException
    {
        // Connecteren naar ActiveMQ
        Connection connection = new ActiveMQConnectionFactory("tcp://192.168.20.200:61616").createConnection();

        // Connectie starten
        connection.start();

        // Start een sessie om met ActiveMQ te werken
        // -> false = geen transacties
        // -> true = transacties
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Maak een nieuwe queue aan (of krijg de reeds aangemaakte terug)
        Destination destination = session.createQueue("TEST.SENDRECEIVE");

        // Maak een producer aan die berichten naar de queue verzendt
        MessageProducer producer = session.createProducer(destination);

        // Non-Persistent: berichten niet door de queue naar disk worden geschreven
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        // Maak een textmessage aan met als inhoud de gegenereerde XML
        TextMessage textMessage = session.createTextMessage(message);

        // Bericht verzenden naar de queue
        producer.send(textMessage);

        // Sluit producer
        producer.close();

        // Sluit sessie
        session.close();

        // Sluit connectie
        connection.close();
    }
}
