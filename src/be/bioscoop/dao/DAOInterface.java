package be.bioscoop.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAOInterface<Type>
{
    List<Type> all() throws SQLException;
    Type get(int id) throws SQLException;
    boolean insert(Type type) throws SQLException;
}
