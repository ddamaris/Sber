package repository;

import models.BankCard;
import org.h2.jdbcx.JdbcDataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO_Card implements DAO<BankCard> {

    private final JdbcDataSource dataSource;
    private final String table = "card";

    public DAO_Card(JdbcDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public BankCard get(Integer id) throws SQLException{

        final String SQL_FIND_BY_ID = String.format("SELECT * FROM %s WHERE id = ?", table);
        ResultSet resultSet = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)){

            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Integer card = resultSet.getInt("id");
                Integer cardOwner = resultSet.getInt("card_owner");
                Integer cardAcc = resultSet.getInt("card_acc");
                String cardNumber = resultSet.getString("card_number");
                Integer amount = resultSet.getInt("amount");
                return new BankCard(card, cardOwner, cardAcc, cardNumber, amount);
            }
            return null;
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                }
                catch (SQLException ignored) {
                }
            }
        }
    }

    @Override
    public List<BankCard> getAllForClientById(Integer clientId, Integer objectId) throws SQLException{

        final String SQL_FIND_ALL = String.format("SELECT * FROM %s WHERE card_owner = ? AND card_acc= ?", table);
        List<BankCard> cards = new ArrayList<>();
        ResultSet resultSet = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL);) {
            statement.setInt(1, clientId);
            statement.setInt(2, objectId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer cardId = resultSet.getInt("id");
                Integer cardOwner = resultSet.getInt("card_owner");
                Integer cardAcc = resultSet.getInt("card_acc");
                String cardNumber = resultSet.getString("card_number");
                Integer amount = resultSet.getInt("amount");
                cards.add(new BankCard(cardId, cardOwner, cardAcc, cardNumber, amount));
            }
            return cards;
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                }
                catch (SQLException ignored) {
                }
            }
        }
    }

    @Override
    public void add(BankCard card) throws SQLException{

        final String SQL_ADD = "INSERT INTO card(card_owner, card_acc, card_number, amount) VALUES(?,?,?,?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD)){
            statement.setInt(1, card.getOwnerId());
            statement.setInt(2, card.getCardAccId());
            statement.setString(3, card.getNumber());
            statement.setInt(4, card.getAmount());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        final String SQL_DELETE = String.format("DELETE FROM %s WHERE id=?", table);

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE)){
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    @Override
    public void update(Integer cardId, String[] params) throws SQLException{
        final String SQL_CARD_UPDATE = "UPDATE card SET amount=? WHERE id =?";
        final String SQL_ACCOUNT = "SELECT * FROM account WHERE id =?";
        final String SQL_ACCOUNT_UPDATE = "UPDATE account SET amount=? WHERE id =?";
        ResultSet resultSet = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement1 = connection.prepareStatement(SQL_CARD_UPDATE);
             PreparedStatement statement2 = connection.prepareStatement(SQL_ACCOUNT);
             PreparedStatement statement3 = connection.prepareStatement(SQL_ACCOUNT_UPDATE)){

            statement1.setInt(1, Integer.parseInt(params[0]));
            statement1.setInt(2, cardId);
            statement1.executeUpdate();

            statement2.setInt(1, Integer.parseInt(params[1]));
            resultSet = statement2.executeQuery();

            if (resultSet.next()) {
                int accAmount = resultSet.getInt("amount");
                int accId = resultSet.getInt("id");
                accAmount += Integer.parseInt(params[0]);

                statement3.setInt(1, accAmount);
                statement3.setInt(2, accId);
                statement3.executeUpdate();
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                }
                catch (SQLException ignored) {
                }
            }
        }
    }
}
