package repository;

import models.BankCard;
import org.h2.jdbcx.JdbcDataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO_Card implements DAO<BankCard> {

    private  JdbcDataSource dataSource = new JdbcDataSource();
    public DAO_Card(JdbcDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public BankCard get(Integer cardId) {

        final String SQL_FIND_BY_ID = "SELECT * FROM card WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setInt(1, cardId);
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
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                }
                catch (SQLException ignored) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignored) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignored) {}
            }
        }
    }

    @Override
    public List<BankCard> getAll(Integer ownerId) {

        final String SQL_FIND_ALL = "SELECT * FROM card WHERE card_owner = ?";
        List<BankCard> cards = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_FIND_ALL);
            statement.setInt(1, ownerId);
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
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                }
                catch (SQLException ignored) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignored) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignored) {}
            }
        }
    }

    @Override
    public void add(BankCard card) {

        final String SQL_ADD = "INSERT INTO card(card_owner, card_acc, card_number, amount) VALUES(?,?,?,?)";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_ADD);
            statement.setInt(1, card.getOwnerId());
            statement.setInt(2, card.getCardAccId());
            statement.setString(3, card.getNumber());
            statement.setInt(4, card.getAmount());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignored) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignored) {}
            }
        }
    }

    @Override
    public void delete(Integer cardId) {
        final String SQL_DELETE = "DELETE FROM card WHERE id=?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_DELETE);
            statement.setInt(1, cardId);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignored) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignored) {}
            }
        }
    }

    @Override
    public void update(Integer cardId, String[] params){
        final String SQL_UPDATE = "UPDATE card SET amount=? where id =?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_UPDATE);
            statement.setInt(1, Integer.parseInt(params[0]));
            statement.setInt(2, cardId);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignored) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignored) {}
            }
        }
    }
}
