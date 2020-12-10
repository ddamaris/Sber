import com.fasterxml.jackson.databind.ObjectMapper;
import models.*;
import repository.*;
import server.BankServer;
import services.GetCard;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;

public class Bank {

    public static void main(String[] args) throws SQLException, IOException {

        new InitBase(DScreation.getDs()).init();

        new BankServer().startServer();
    }
}
