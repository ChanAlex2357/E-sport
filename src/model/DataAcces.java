package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataAcces {
    final static String driverClass = "org.postgresql.Driver";
    final static String urlPatern = "jdbc:postgresql://localhost:5432/team_manager";
    final static String userName = "jcaservlet";
    final static String password = "jca";

    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        Class.forName(driverClass);
        Connection result = DriverManager.getConnection(urlPatern, userName, password);
        result.setAutoCommit(false);
        return result;
    }

    public static void dispose(ResultSet resultSet , PreparedStatement pStatement , Connection connection) throws SQLException{
        resultSet.close();
        pStatement.close();
        connection.close();
    }
    
}
