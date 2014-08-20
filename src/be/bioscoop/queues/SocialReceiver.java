package be.bioscoop.queues;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;

public class SocialReceiver
{
    public SocialReceiver() throws JMSException
    {
        ActiveMQConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory("tcp://192.168.20.200:61616");

        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue("TEST.SENDRECEIVE");



    }
}
