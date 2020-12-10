package repository;

import models.Client;

import java.sql.SQLException;
import java.util.List;

public interface DAO <T>{
    T get(Integer id) throws SQLException;
    List<T> getAllForClientById(Integer clientId, Integer objectId) throws SQLException;
    void add(T t) throws SQLException;
    void update(Integer id, String[] params) throws SQLException;
    void delete(Integer id) throws SQLException;
}
