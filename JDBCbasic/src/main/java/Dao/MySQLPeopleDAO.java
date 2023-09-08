package Dao;

import javax.sql.RowSet;
import java.util.Collection;

public class MySQLPeopleDAO implements PeopleDao {

    private String nombre;
    private int edad;
    private String address;

    public MySQLPeopleDAO(String nombre, int edad, String dress) {
        this.nombre = nombre;
        this.address = dress;
        this.edad = edad;
    }

    public MySQLPeopleDAO() {

    }

    public String nombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String address() {
        return address;
    }



    public void setAddress(String address) {
        this.address = address;
    }

    public int edad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public int insertPeople() {
        return 0;
    }

    @Override
    public boolean deletePeople() {
        return false;
    }

    @Override
    public PeopleDao findPeople() {
        return null;
    }

    @Override
    public boolean updatePeople() {
        return false;
    }

    @Override
    public RowSet selectPeopleRS() {
        return null;
    }

    @Override
    public String toString() {
        return "\n{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public Collection selectPeople() {
        return null;
    }
}
