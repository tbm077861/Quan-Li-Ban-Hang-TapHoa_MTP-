package quanLyCuaHangTienLoi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static Connection connection = null;
    
    public static Connection getConnection(String dbName) throws SQLException {
        if (connection != null && !connection.isClosed()) {
            return connection;
        }
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://NHAT-TRUONG:1433;" +
                         "databaseName=" + dbName + ";integratedSecurity=true;" +
                         "encrypt=true;trustServerCertificate=true";
            String userName = "sa";
            String password = "123456";
            
            connection = DriverManager.getConnection(url, userName, password);
            return connection;
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQL Server JDBC Driver not found.", e);
        } catch (SQLException e) {
            throw new SQLException("Error connecting to database: " + e.getMessage(), e);
        }
    }
    
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}