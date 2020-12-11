import repository.*;
import server.BankServer;
import java.io.IOException;
import java.sql.SQLException;

public class Bank {

    public static void main(String[] args) throws SQLException, IOException {

        new InitBase(DScreation.getDs()).init();

        new BankServer().startServer();
    }
}
