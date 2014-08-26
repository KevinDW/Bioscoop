package be.bioscoop.queues;

import be.bioscoop.config.Queue;

import javax.jms.*;

public class SocialSender
{
    public void sendMessage(String message) throws JMSException
    {
        // Connecteren naar ActiveMQ
        Connection connection = Queue.connect();

        // Connectie starten
        connection.start();

        // Start een sessie om met ActiveMQ te werken
        // -> false = geen transacties
        // -> true = transacties
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Maak een nieuwe queue aan (of krijg de reeds aangemaakte terug)
        Destination destination = session.createQueue("SOCIAL.MESSAGES");

        // Maak een producer aan die berichten naar de queue verzendt
        MessageProducer producer = session.createProducer(destination);

        // Non-Persistent: berichten niet door de queue naar disk worden geschreven
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        // Maak een textmessage aan met als inhoud de gegenereerde XML
        TextMessage textMessage = session.createTextMessage(message);

        // Bericht verzenden naar de queue
        producer.send(textMessage);

        // Sluit MessageProducer, Session en Connection
        be.bioscoop.config.Queue.close(session, producer);
    }
}
