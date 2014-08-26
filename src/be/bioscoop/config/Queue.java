package be.bioscoop.config;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public final class Queue
{
    private static Connection connection = null;
    private static final String database = "tcp://192.168.20.200:61616";

    public static Connection connect() throws JMSException
    {
        if (connection != null)
        {
            return connection;
        }

        connection = new ActiveMQConnectionFactory(database).createConnection();

        return connection;
    }

    public static void close(Session session, MessageProducer producer) throws JMSException
    {
        if (producer != null)
        {
            producer.close();
        }

        close(session);
    }

    public static void close(Session session, MessageConsumer consumer) throws JMSException
    {
        if (consumer != null)
        {
            consumer.close();
        }

        close(session);
    }

    public static void close(Session session) throws JMSException
    {
        if (session != null)
        {
            session.close();
        }

        close();
    }

    public static void close() throws JMSException
    {
        if (connection != null)
        {
            connection.close();
        }
    }
}
