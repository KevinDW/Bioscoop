package be.bioscoop.config;

import java.sql.*;

public final class Database
{
    private static Connection connection = null;

    private static final String database = "jdbc:mysql://192.168.20.200:3306/bioscoop";
    private static final String username = "root";
    private static final String password = "root";

    public static Connection connect() throws SQLException
    {
        return DriverManager.getConnection(database, username, password);
    }

    public static void close(Statement statement, ResultSet resultSet) throws SQLException
    {
        if (resultSet != null)
        {
            resultSet.close();
        }

        close(statement);
    }

    public static void close(Statement statement) throws SQLException
    {
        if (statement != null)
        {
            statement.close();
        }

        close();
    }

    public static void close() throws SQLException
    {
        if (connection != null)
        {
            connection.close();
        }
    }
}
