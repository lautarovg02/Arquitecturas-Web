package dao;

import javax.persistence.*;

/*
*Vamos a decirle que esta clase es una tabla con la palabara @entity*/
@Entity
public class Persona {
    //esta persona va a tener un id
    @Id
    private int id;
    //Marcamos las columnas de la BD
    /*
    *Le indicamos que no puede ser nulleable */
    @Column(nullable = false)
    private String nombre;
    /*
    * Podemos decirle que esta columna en la BD se llama años con Column(name = "nuevo nombre")
    */
    @Column(name="anios")
    private int edad;

    /*
    *Indicamos en la relacion que muchas personas puedan vivir en un mismo domicilio pero no puede vivir en dos lugares distitnos
     */
    @ManyToOne
    private Direccion domicilio;


    public Persona(int id, String nombre, int edad, Direccion domicilio) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.domicilio = domicilio;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Direccion getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Direccion domicilio) {
        this.domicilio = domicilio;
    }

    public Persona(){

    }

    @Override
    public String toString() {
        return "\nPersona{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", domicilio=" + domicilio +
                '}';
    }
}
