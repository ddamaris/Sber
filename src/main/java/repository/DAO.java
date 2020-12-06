package repository;

import java.sql.SQLException;
import java.util.List;

public interface DAO <T>{
    T get(Integer id);
    List<T> getAll(Integer id);
    void add(T t);
    void update(Integer id, String[] params);
    void delete(Integer id);
}
