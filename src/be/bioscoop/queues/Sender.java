package be.bioscoop.queues;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Sender
{
    public static void main(String[] args) throws JMSException
    {
        //Create a ConnectionFactory
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.20.200:61616");

        //Create a Connection to ActiveMQ
        Connection connection = connectionFactory.createConnection();
        connection.start();

        //Create a Session that allows you to work with activeMQ
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //Create the destination queue (or retrieve it, if it already exists)
        Destination destination = session.createQueue("TEST.SENDRECEIVE");

        //Create a MessageProducer for the Destination
        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        while (true)
        {
            try
            {
                System.out.println("Test");
                String message = bufferedReader.readLine();

                if (message.equalsIgnoreCase("exit"))
                {
                    break;
                }

                TextMessage textMessage = session.createTextMessage(message);
                producer.send(textMessage);
            }
            catch (Exception e)
            {
                e.getMessage();
            }
        }
    }
}
