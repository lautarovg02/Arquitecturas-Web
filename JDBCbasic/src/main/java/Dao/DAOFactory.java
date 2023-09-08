package Dao;

import java.sql.SQLException;
import java.util.List;

public abstract class DAOFactory {
    public static final int MYSQL_JDBC = 1;
//    public static final int DERBY_JDBC = 2;
    public static final int JPA_HIBERNATE = 3;

    public abstract PeopleDao getPeopleDAO();
    public static DAOFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case MYSQL_JDBC : return new MySqlJDBCDAOFactory();
//            case DERBY_JDBC: return new DerbyJDBCDAOFactory();
            case JPA_HIBERNATE:
            default: return null;
        }
    }

    public abstract List<PeopleDao> listAllPeople() throws SQLException;
}
