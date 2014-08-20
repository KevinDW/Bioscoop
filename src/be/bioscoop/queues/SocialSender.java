package be.bioscoop.queues;

import org.apache.activemq.*;


import javax.jms.*;

public class SocialSender
{
    public SocialSender()
    {

    }

    public void sendMessage() throws Exception
    {
        //Create a ConnectionFactory
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.20.200:61616");

        //Create a Connection to ActiveMQ
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // Create a Session that allows you to work with activeMQ
        //False will create a non-transactional session object
        //True will use transactions
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create the destination queue (or retrieve it, if it already exists)
        // Name: TEST.SENDRECEIVE
        Destination destination = session.createQueue("TEST.SENDRECEIVE");

        // Create a MessageProducer for the Destination
        // Non-Persistant: berichten niet door de queue naar disk worden geschreven
        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        TextMessage message = session.createTextMessage();
        message.setText("Dit is een test!");
        producer.send(message);
    }
}
