import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


/**
 * Created by Kevin on 8/14/2014.
 */
public class Sender {
    public static void main(String[] args) throws JMSException {

        //Create a ConnectionFactory
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.20.120:61616");

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



    }
}
