package Persistencia;

import com.j256.ormlite.jdbc.JdbcConnectionSource;

import java.sql.SQLException;

public class SingletonConnection {
    private static JdbcConnectionSource connectionSource;
    public SingletonConnection() {
        try {
            String url = "jdbc:mysql://space.mysql.database.azure.com:3306/space";
            String username = "space";
            String password = "Cucharon123";
            connectionSource = new JdbcConnectionSource(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static JdbcConnectionSource getSingletonInstance() {
        if(connectionSource == null) {
            new SingletonConnection();
        }
        return  connectionSource;
    }
}
