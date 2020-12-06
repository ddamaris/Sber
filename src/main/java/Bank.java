import models.BankCard;
import org.h2.jdbcx.JdbcDataSource;
import repository.*;
import repository.InitBase;
import java.sql.SQLException;

public class Bank {
    public static void main(String[] args) throws SQLException, InterruptedException, ClassNotFoundException {

        JdbcDataSource ds = new JdbcDataSource();
        ds.setUrl("jdbc:h2:~/test");

        InitBase init = new InitBase(ds);
        init.init();
        DAO<BankCard> card = new DAO_Card(ds);
        System.out.println(card.getAll(1));
        card.add(new BankCard(0, 1, 1, "1111 1111 1111 2222", 234));
        System.out.println(card.getAll(1));
        System.out.println("Amount for cardId: 4 = " + card.get(4).getAmount());
        String[] params = {"432"};
        card.update(4, params);
        System.out.println("Now amount for cardId: 4 = " + card.get(4).getAmount());


    }
}
