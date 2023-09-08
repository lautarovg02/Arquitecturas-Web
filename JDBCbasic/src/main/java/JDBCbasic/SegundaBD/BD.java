package JDBCbasic.SegundaBD;

import org.apache.derby.jdbc.EmbeddedDriver;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class BD {
    public static void main(String[] args) {
//        new EmbeddedDriver();
        String driver = "com.mysql.cj.jdbc.Driver";

        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
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

        try {
            Connection conn = DriverManager.getConnection(uri, "root", "");
            conn.setAutoCommit(false);
//            createTables(conn);
            /*
            * AGREGANDO PERSONAS*/
//            addPerson(conn,3, "Lautaro",20);
//            addPerson(conn,4, "Yolanda",43);

            /*
             * AGREGANDO DIRECCIONES*/
//            addAdress(conn,1,"Rufino Fal", 223, "Olavarria");
//            addAdress(conn,2,"Manuel Leal", 1923, "Olavarria");
//            addAdress(conn,3,"Hirigoyen", 5223, "Olavarria");
//            addAdress(conn,4,"Necochea", 7423, "Olavarria");

            /*
             * CREANDO TABLA DIRECCION*/
//            createTableDireccion(conn);
            /*
             * MODIFICANDO TABLA PERSONAS*/
//            modifyTable(conn);

            /*
             * AGREGANDO DIRECCION A PERSONAS*/
//            updateAdress(conn,1,1);
//            updateAdress(conn,2,2);
//            updateAdress(conn,3,4);
//            updateAdress(conn,4,3);
            updateAdress(conn,1,3);

            /*
             * MOSTRANDO TABLA PERSONAS*/
            showTable(conn);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void updateAdress(Connection conn, int idPeople, int idAdress) throws SQLException {
        String update = "UPDATE persona SET id_direccion = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(update);
        ps.setInt(1, idAdress);
        ps.setInt(2,idPeople);
        ps.executeUpdate();
        ps.close();
        conn.commit();
    }

    private static void addAdress(Connection conn,int id, String adress, int number, String locality) throws SQLException {
        String insert =  "INSERT INTO direccion (id,address,locality,number) VALUES (?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(insert);
        ps.setInt(1, id);
        ps.setString(2, adress);
        ps.setString(3, locality);
        ps.setInt(4, number);
        ps.executeUpdate();
        ps.close();
        conn.commit();
    }

    private static void addPerson(Connection conn, int id, String name, int age) throws SQLException {
        String insert = "INSERT INTO persona (id,nombre,edad) VALUES (?,?,?)";
        PreparedStatement ps = conn.prepareStatement(insert);
        ps.setInt(1, id);
        ps.setString(2, name);
        ps.setInt(3, age);
        ps.executeUpdate();
        ps.close();
        conn.commit();
        // Con este tipo de insert prevenimos inyecciones de sql malingnos
    }

    private static void modifyTable(Connection conn) throws SQLException {
        String alterTable = "ALTER TABLE persona " +
                        " ADD COLUMN id_direccion INT, " +
                        "ADD CONSTRAINT ck_persona_direccion FOREIGN KEY (id_direccion) REFERENCES direccion (id) ";
        conn.prepareStatement(alterTable).execute();
        conn.commit();
    }

    private static void createTableDireccion(Connection conn) throws SQLException {
        String table = "CREATE TABLE direccion("
                + "id INT," +
                "address VARCHAR(200)," +
                "locality VARCHAR(200)," +
                "number INT," +
                " PRIMARY KEY(id))";

        conn.prepareStatement(table).execute();
        conn.commit();
    }

    private static void showTable(Connection conn) {
        try {
            String select = "SELECT P.nombre, P.edad, D.address FROM `persona` P JOIN direccion D ON P.id_direccion = D.id;";
            PreparedStatement ps = conn.prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1) + " , " + rs.getInt(2) + ", " + rs.getString(3) );
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private static void createTables(Connection conn) throws SQLException {
        String table = "CREATE TABLE persona("
                + "id INT," +
                "nombre VARCHAR(500)," +
                "edad INT," +
                "PRIMARY KEY(id))";

        conn.prepareStatement(table).execute();
        conn.commit();
    }


}
