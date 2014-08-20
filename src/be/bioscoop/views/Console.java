package be.bioscoop.views;

import be.bioscoop.config.Database;
import be.bioscoop.dao.ProgrammatieDAO;
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

            ProgrammatieDAO programmatieDAO = new ProgrammatieDAO(connection);
            programmatieDAO.programmatiesVoorBepaaldeBioscoopTussenTweeDatums(bioscoop, beginDatum, eindDatum);

            Database.close();
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
