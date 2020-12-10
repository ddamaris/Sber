package repository;

import org.h2.jdbcx.JdbcDataSource;

public class DScreation {

    public static JdbcDataSource getDs() {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setUrl("jdbc:h2:~/test");
        return ds;
    }
}
