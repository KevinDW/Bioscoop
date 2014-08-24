package be.bioscoop.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAOInterface<Type>
{
    List<Type> all() throws SQLException;
    Type find(int id) throws SQLException;
    boolean insert(Type type) throws SQLException;
    boolean update(Type type) throws SQLException;
    boolean delete(Type type) throws SQLException;
}
