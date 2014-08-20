package be.bioscoop.queues;

import org.apache.activemq.*;


import javax.jms.*;

public class SocialSender
{
    public SocialSender() {}

    public void sendMessage() throws Exception
    {
        // Create a connection to ActiveMQ
        Connection connection = new ActiveMQConnectionFactory("tcp://192.168.20.200:61616").createConnection();

        // Start connection
        connection.start();

        // Create a Session that allows you to work with activeMQ
        // -> false will create a non-transactional session object
        // -> true will use transactions
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create the destination queue (or retrieve it, if it already exists)
        Destination destination = session.createQueue("TEST.SENDRECEIVE");

        // Create a MessageProducer for the Destination
        MessageProducer producer = session.createProducer(destination);

        // Non-Persistant: berichten niet door de queue naar disk worden geschreven
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        TextMessage message = session.createTextMessage();
        message.setText("Dit is een test!");

        // Send message to queue
        producer.send(message);
    }
}
