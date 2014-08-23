package be.bioscoop.views;

import be.bioscoop.config.Database;
import be.bioscoop.dao.*;
import be.bioscoop.entities.*;
import be.bioscoop.queues.SocialReceiver;
import be.bioscoop.queues.SocialSender;
import org.jdom.JDOMException;

import javax.jms.JMSException;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Console
{
    public static void main(String[] args)
    {
        programmatiesVoorBepaaldeBioscoopTussenTweeDatums();
        socialeBerichtenOpQueue();
    }

    private static void programmatiesVoorBepaaldeBioscoopTussenTweeDatums()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Bioscoop: ");
        String bioscoop = scanner.nextLine();

        System.out.print("Begindatum (yyyy-mm-dd): ");
        Date beginDatum = Date.valueOf(scanner.nextLine());

        System.out.print("Einddatum (yyyy-mm-dd): ");
        Date eindDatum = Date.valueOf(scanner.nextLine());

        try
        {
            Connection connection = Database.connect();

            ProgrammatieDAO programmatieDAO = new ProgrammatieDAO(connection);
            List<Programmatie> programmaties = programmatieDAO.whereBioscoopAndDateBetween(bioscoop, beginDatum, eindDatum);

            for (Programmatie programmatie : programmaties)
            {
                System.out.println(programmatie);
            }

            Database.close();
        }
        catch (SQLException exception)
        {
            exception.getMessage();
        }
    }

    private static void socialeBerichtenOpQueue()
    {
        try
        {
            SocialSender socialSender = new SocialSender();
            socialSender.sendMessage();

            SocialReceiver socialReceiver = new SocialReceiver();
            socialReceiver.receiveMessage();
        }
        catch (JDOMException exception)
        {
            exception.getMessage();
        }
        catch (JMSException exception)
        {
            exception.getMessage();
        }
        catch (IOException exception)
        {
            exception.getMessage();
        }
        catch (SQLException exception)
        {
            exception.getMessage();
        }
    }
}
