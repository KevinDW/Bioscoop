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
        socialeBerichtenViaDAO();
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
                System.out.printf("%s | ", programmatie.getDatum());
                System.out.printf("%s | ", programmatie.getBeginUur());
                System.out.printf("%d | ", programmatie.getZaal().getZaalNr());
                System.out.printf("%s \n", programmatie.getFilm().getNaam());
            }

            Database.close();
        }
        catch (SQLException exception)
        {
            exception.getMessage();
        }
    }

    private static void socialeBerichtenViaDAO()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Datum: ");
        Date datum = Date.valueOf(scanner.nextLine());

        System.out.print("Sociaal netwerk: ");
        String netwerk = scanner.nextLine();

        System.out.print("Film: ");
        int film = Integer.parseInt(scanner.nextLine());

        System.out.print("Bericht: ");
        String bericht = scanner.nextLine();

        try
        {
            Connection connection = Database.connect();

            FilmDAO filmDAO = new FilmDAO(connection);
            SocialDAO socialDAO = new SocialDAO(connection);

            socialDAO.insert(
                new Social(datum, netwerk, bericht, filmDAO.find(film))
            );

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
