package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static Dao.MySqlJDBCDAOFactory.createConnection;

public class DerbyJDBCDAOFactory extends DAOFactory {

    @Override
    public PeopleDao getPeopleDAO() {
        return (PeopleDao) new DerbyJDBCDAOFactory();
    }

    @Override
    public List<PeopleDao> listAllPeople() throws SQLException {
        Connection connection = createConnection();
        String select = "SELECT P.nombre, P.edad, D.address FROM `persona` P JOIN direccion D ON P.id_direccion = D.id;";
        PreparedStatement ps = connection.prepareStatement(select);
        connection.prepareStatement(select);
        ResultSet rs = ps.executeQuery();

        List<PeopleDao> peopleList = new ArrayList<>();

        while (rs.next()){
            PeopleDao peopleDao = new MySQLPeopleDAO(rs.getString(1),rs.getInt(2),rs.getString(3));
            peopleList.add(peopleDao);
        }
        connection.close();

        return peopleList;
    }
}
