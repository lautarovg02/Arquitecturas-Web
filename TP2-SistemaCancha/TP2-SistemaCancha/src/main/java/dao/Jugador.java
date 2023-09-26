package dao;

import javax.persistence.*;

@Entity
public class Jugador extends Persona {
    @ManyToOne
    private Posicion posicion;
    @ManyToOne
    private Equipo equipo;

    public Jugador(int id,String nombre, int edad, Posicion posicion) {
        super(id,nombre, edad);
        this.posicion = posicion;
    }

    public Jugador() {

    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "nombre='" + super.getNombre() + '\'' +
                "edad='" + super.getEdad() + '\'' +
                "posicion=" + posicion +
                ", equipo=" + equipo +
                "} " ;
    }
}
