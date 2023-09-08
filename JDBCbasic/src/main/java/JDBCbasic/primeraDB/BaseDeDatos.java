package JDBCbasic.primeraDB;

import org.apache.derby.jdbc.EmbeddedDriver;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BaseDeDatos {
    public static void main(String[] args)   {
//        new EmbeddedDriver();
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";

        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();

        }catch (ClassNotFoundException e) {
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

        String uri = "jdbc:derby:MyDerbyDb;create=true";

        try {
            Connection conn = DriverManager.getConnection(uri);
            createTables(conn);
            addPerson(conn,1, "Josefina",19);
            addPerson(conn,2, "Felipe",13);
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    private static void addPerson(Connection conn, int id, String name, int age) throws SQLException {
        String insert = "INSERT INTO persona (id,nombre,edad) VALUES (?,?,?)";
        PreparedStatement ps = conn.prepareStatement(insert);
        ps.setInt(1,id);
        ps.setString(2,name);
        ps.setInt(3,age);
        ps.executeUpdate();
        ps.close();
        conn.commit();
        // Con este tipo de insert prevenimos inyecciones de sql malingnos
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
