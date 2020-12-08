import com.fasterxml.jackson.databind.ObjectMapper;
import models.*;
import org.h2.jdbcx.JdbcDataSource;
import repository.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Bank {
    public static void main(String[] args) throws SQLException, InterruptedException, ClassNotFoundException {

        ObjectMapper objectMapper = new ObjectMapper();

        JdbcDataSource ds = new JdbcDataSource();
        ds.setUrl("jdbc:h2:~/test");

        InitBase init = new InitBase(ds);
        init.init();

        DAO<BankCard> card = new DAO_Card(ds);
        DAO<Account> account = new DAO_Account(ds);

        System.out.println(card.get(1));
        System.out.println(card.getAllForClientById(1, 1));
        account.add(new Account(0, 1, 55555, 100500));
        System.out.println(account.getAllForClientById(1,1));
        card.add(new BankCard(0, 1, 1, "1111 1111 1111 2222", 234));
        System.out.println(card.getAllForClientById(1, 1));
        System.out.println("Amount for cardId: 4 = " + card.get(4).getAmount());
        String[] params = {"432","1"};
        card.update(4, params);
        System.out.println("Now amount for cardId: 4 = " + card.get(4).getAmount());

        card.add(new BankCard(0, 3, 3, "3333 3333 3333 2222", 123));
        card.add(new BankCard(0, 3, 3, "3333 3333 3333 3333", 456));
        card.add(new BankCard(0, 3, 3, "3333 3333 3333 4444", 789));
        System.out.println(card.getAllForClientById(3,3));
    }
}
