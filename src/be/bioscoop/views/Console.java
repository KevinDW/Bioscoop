package be.bioscoop.views;

import be.bioscoop.config.Database;
import be.bioscoop.dao.*;
import be.bioscoop.entities.*;
import be.bioscoop.generators.SocialGenerator;
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
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        Console console = new Console();

        // console.programmatiesVoorBepaaldeBioscoopTussenTweeDatums();
        console.socialeBerichtenViaDAO();
        // console.socialeBerichtenOpQueue();
    }

    private void programmatiesVoorBepaaldeBioscoopTussenTweeDatums()
    {
        try
        {
            Connection connection = Database.connect();

            BioscoopDAO bioscoopDAO = new BioscoopDAO(connection);
            List<Bioscoop> bioscopen = bioscoopDAO.all();

            System.out.printf("%s\n", "Bioscopen:");
            System.out.printf("%s\n", "==========");

            for (Bioscoop bioscoop : bioscopen)
            {
                System.out.printf("%-2d | ", bioscoop.getId());
                System.out.printf("%-20s | ", bioscoop.getNaam());
                System.out.printf("%-20s | ", bioscoop.getStraat());
                System.out.printf("%-4s | ", bioscoop.getPostcode());
                System.out.printf("%-20s\n", bioscoop.getGemeente());
            }

            System.out.println();

            System.out.print("Bioscoop (ID uit lijst): ");
            int bioscoop = Integer.parseInt(scanner.nextLine());

            System.out.print("Begindatum (yyyy-mm-dd): ");
            Date beginDatum = Date.valueOf(scanner.nextLine());

            System.out.print("Einddatum (yyyy-mm-dd): ");
            Date eindDatum = Date.valueOf(scanner.nextLine());

            System.out.println();

            ProgrammatieDAO programmatieDAO = new ProgrammatieDAO(connection);
            List<Programmatie> programmaties = programmatieDAO.whereBioscoopAndDateBetween(bioscoop, beginDatum, eindDatum);

            System.out.printf("%s\n", "Programmaties:");
            System.out.printf("%s\n", "==============");

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

    private void socialeBerichtenViaDAO()
    {
        try
        {
            Connection connection = Database.connect();

            FilmDAO filmDAO = new FilmDAO(connection);
            List<Film> films = filmDAO.all();

            System.out.printf("%-2s | ", "ID");
            System.out.printf("%-40s | ", "Naam");
            System.out.printf("%-4s | ", "Jaar");
            System.out.printf("%-4s | ", "Duur");
            System.out.printf("%-12s | ", "Beoordeling");
            System.out.printf("%-12s | ", "Genre");
            System.out.printf("%-12s \n", "Restrictie");

            System.out.printf("%-2s | ", "");
            System.out.printf("%-40s | ", "");
            System.out.printf("%-4s | ", "");
            System.out.printf("%-4s | ", "");
            System.out.printf("%-12s | ", "");
            System.out.printf("%-12s | ", "");
            System.out.printf("%-12s \n", "");

            for (Film film : films)
            {
                System.out.printf("%-2d | ", film.getId());
                System.out.printf("%-40s | ", film.getNaam());
                System.out.printf("%-4d | ", film.getJaar());
                System.out.printf("%-4d | ", film.getDuur());
                System.out.printf("%-12.1f | ", film.getBeoordeling());
                System.out.printf("%-12s | ", film.getGenre().getNaam());
                System.out.printf("%-12s \n", film.getRestrictie().getNaam());
            }

            Database.close();
        }
        catch (SQLException exception)
        {
            exception.getMessage();
        }
    }

    private void socialeBerichtenOpQueue()
    {
        try
        {
            SocialGenerator socialGenerator = new SocialGenerator();
            String message = socialGenerator.createXml();

            SocialSender socialSender = new SocialSender();
            socialSender.sendMessage(message);

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
