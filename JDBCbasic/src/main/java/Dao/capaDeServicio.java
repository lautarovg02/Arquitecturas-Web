package Dao;

import java.sql.SQLException;
import java.util.List;

public class capaDeServicio {
    public static void main(String[] args) throws SQLException {
        // crear la fábrica DAO requerida
        DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory. MYSQL_JDBC);

        assert mysqlFactory != null;
        PeopleDao peopleDao = mysqlFactory.getPeopleDAO();
        List<PeopleDao> list = mysqlFactory.listAllPeople();

        System.out.println(list);
//        System.out.println(pe);
//        // create a new customer
//        int newCustNo = peopleDao.insertPeople();
    }
}
