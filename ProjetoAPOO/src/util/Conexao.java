package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:postgresql://aws-1-sa-east-1.pooler.supabase.com:5432/postgres";
    private static final String USER = "postgres.fyhqhgcscypnfqknuorp";
    private static final String PASSWORD = "Toxicocomputador10@";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC do PostgreSQL não encontrado!", e);
        }
    }
}
