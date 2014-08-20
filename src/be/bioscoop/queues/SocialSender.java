package be.bioscoop.queues;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class SocialSender
{
    public SocialSender()  throws JMSException
    {
        //Create a ConnectionFactory
        ActiveMQConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory("tcp://192.168.20.200:61616");

        //Create a Connection to ActiveMQ
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // Create a Session that allows you to work with activeMQ
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create the destination queue (or retrieve it, if it already exists)
        Destination destination = session.createQueue("TEST.SENDRECEIVE");

        // Create a MessageProducer for the Destination
        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
    }
}
