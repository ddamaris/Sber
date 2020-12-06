package repository;

import org.h2.tools.DeleteDbFiles;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InitBase {

    private static final String URL = "jdbc:h2:~/test";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";

    public static void init() throws SQLException, InterruptedException, ClassNotFoundException {

        DeleteDbFiles.execute("~", "test", true);
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test");
        Statement statement = conn.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS client (id SERIAL PRIMARY KEY,name TEXT NOT NULL)");
        statement.execute("CREATE TABLE IF NOT EXISTS account (id SERIAL PRIMARY KEY,acc_owner INTEGER REFERENCES client(id), acc_number INTEGER,amount INTEGER)");
        statement.execute("CREATE TABLE IF NOT EXISTS card (id SERIAL PRIMARY KEY, card_owner INTEGER REFERENCES client(id), card_acc INTEGER REFERENCES account(id), card_number VARCHAR(20),amount INTEGER)");

        statement.execute("INSERT INTO client(name) VALUES('John')");
        statement.execute   ("INSERT INTO client(name) VALUES('Smith')");
        statement.execute("INSERT INTO client(name) VALUES('Mary')");

        statement.execute("INSERT INTO account(acc_owner, acc_number, amount) VALUES('1', '1111', '1000')");
        statement.execute("INSERT INTO account(acc_owner, acc_number, amount) VALUES('2', '2222', '2000')");
        statement.execute("INSERT INTO account(acc_owner, acc_number, amount) VALUES('3', '3333', '3000')");

        statement.execute("INSERT INTO card(card_owner, card_acc, card_number, amount) VALUES('1', '1', '1111 1111 1111 1111', '1000')");
        statement.execute("INSERT INTO card(card_owner, card_acc, card_number, amount) VALUES('2', '2', '2222 2222 2222 2222', '2000')");
        statement.execute("INSERT INTO card(card_owner, card_acc, card_number, amount) VALUES('3', '3', '3333 3333 3333 3333', '3000')");
/*
        ResultSet resultSet = statement.executeQuery("select id, name from client");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt("id"));
            System.out.println(resultSet.getString("name"));
        }*/
        conn.close();
    }
}
