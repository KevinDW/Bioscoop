package be.bioscoop.config;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.JMSException;

public final class Queue
{
    private static final String database = "tcp://192.168.20.200:61616";

    public static Connection connect() throws JMSException
    {
        return new ActiveMQConnectionFactory(database).createConnection();
    }
}
