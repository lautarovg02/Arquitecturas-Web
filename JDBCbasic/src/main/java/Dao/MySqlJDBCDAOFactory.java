package Dao;
// MySQL concrete DAO Factory implementation

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlJDBCDAOFactory extends DAOFactory {
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DBURL = "jdbc:mysql://localhost:3306/lau_MySql";

    // method to create DB connection
    public static Connection createConnection() throws SQLException {
        try {
            Class.forName(DRIVER).getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (InstantiationException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            System.exit(1);
        }

        String uri = "jdbc:mysql://localhost:3306/lau_MySql";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DBURL, "root", "");
            conn.setAutoCommit(false);
            /*
             * AGREGANDO PERSONAS*/
//            addPerson(conn,3, "Lautaro",20);
//            addPerson(conn,4, "Yolanda",43);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }


    public PeopleDao getPeopleDAO(){
        return new MySQLPeopleDAO();
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

// Other DAOs



