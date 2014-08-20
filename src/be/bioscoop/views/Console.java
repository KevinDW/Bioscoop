package be.bioscoop.views;

import be.bioscoop.config.Database;
import be.bioscoop.queues.SocialReceiver;
import be.bioscoop.queues.SocialSender;

import java.sql.*;
import java.util.Scanner;

public class Console
{
    public static void main(String[] args)
    {
        programmatiesVoorBepaaldeBioscoop();
        socialeBerichtenOpQueue();
    }

    private static void programmatiesVoorBepaaldeBioscoop()
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

            PreparedStatement statement = connection.prepareStatement(
                "SELECT zaal.zaalNr, film.naam " +
                "FROM programmatie " +
                "INNER JOIN film ON programmatie.filmId = film.id " +
                "INNER JOIN zaal ON programmatie.zaalId = zaal.id " +
                "INNER JOIN bioscoop ON zaal.bioscoopId = bioscoop.id " +
                "WHERE bioscoop.naam = ? AND programmatie.datum BETWEEN ? AND ?"
            );

            statement.setString(1, bioscoop);
            statement.setDate(2, beginDatum);
            statement.setDate(3, eindDatum);

            ResultSet resultSet = statement.executeQuery();

            System.out.printf("Bioscoop: %s\n\n", bioscoop);

            while (resultSet.next())
            {
                System.out.printf("%d | ", resultSet.getInt("zaal.zaalNr"));
                System.out.printf("%s", resultSet.getString("film.naam"));
                System.out.println();
            }

            Database.close(statement, resultSet);
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
    }

    private static void socialeBerichtenOpQueue()
    {
        try
        {
            SocialSender socialSender = new SocialSender();
            SocialReceiver socialReceiver = new SocialReceiver();

            socialSender.sendMessage();
            socialReceiver.receiveMessage();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
